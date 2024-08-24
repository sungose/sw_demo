package com.echo.demos.six;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NavigationFormDemo{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DemoWindow19 dw = new DemoWindow19("带有更新功能的表记录 导航窗体");
		dw.pack();
		dw.setBounds(
				dw.getToolkit().getScreenSize().width / 2 - dw.getWidth() / 2,
				dw.getToolkit().getScreenSize().height / 2 - dw.getHeight() / 2,
				dw.getWidth(), dw.getHeight());
		dw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dw.setVisible(true);
	}

}

class DemoWindow19 extends JFrame implements ActionListener {
	//=================================================
//                Access本地数据库设置参数：路径及库，表名
//=================================================
	DBNavigation trans = new DBNavigation("e:/ck/ckgl.mdb", "clggb");

	JPanel[] jps = null;
	JPanel controlPanel = new JPanel();
	JPanel operatePanel = new JPanel();

	JTextField[] jtfs = null;

	JButton jbFirst = new JButton("第一条");
	JButton jbPrevious = new JButton("上一条");
	JButton jbNext = new JButton("下一条");
	JButton jbLast = new JButton("最后一条");
	JButton jbInsert = new JButton("插入记录");
	JButton jbUpdate = new JButton("更新记录");
	JButton jbDelete = new JButton("删除记录");
	JButton jbCancel = new JButton("取消更新");

	public DemoWindow19(String title) {
		super(title);

		setLayout(new GridLayout(trans.getColumnCount() + 3, 1));

		add(new JLabel("数据库：" + trans.getDatabase() + "|表：" + trans.getTable(),
				JLabel.CENTER));

		jps = new JPanel[trans.getColumnCount()];
		jtfs = new JTextField[trans.getColumnCount()];

		for (int i = 0; i < trans.getColumnCount(); i++) {
			jps[i] = new JPanel();
			jps[i].add(new JLabel(trans.getColumnNames()[i]));
			jtfs[i] = new JTextField(20);
			jps[i].add(jtfs[i]);
			add(jps[i]);
		}
		controlPanel.add(jbFirst);
		controlPanel.add(jbPrevious);
		controlPanel.add(jbNext);
		controlPanel.add(jbLast);
		add(controlPanel);

		operatePanel.add(jbInsert);
		operatePanel.add(jbUpdate);
		operatePanel.add(jbDelete);
		operatePanel.add(jbCancel);
		add(operatePanel);

		jbFirst.addActionListener(this);
		jbPrevious.addActionListener(this);
		jbNext.addActionListener(this);
		jbLast.addActionListener(this);
		jbInsert.addActionListener(this);
		jbUpdate.addActionListener(this);
		jbDelete.addActionListener(this);
		jbCancel.addActionListener(this);

		fill(trans.getFirstRecord());
	}

	public void fill(String[] values) {
		for (int i = 0; i < trans.getColumnCount(); i++) {
			jtfs[i].setText(values[i]);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbFirst) {
			fill(trans.getFirstRecord());
		} else if (e.getSource() == jbPrevious) {
			try {
				fill(trans.getPreviousRecord());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jbNext) {
			try {
				fill(trans.getNextRecord());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jbLast) {
			try {
				fill(trans.getLastRecord());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jbInsert) {
			trans.insertRecord(getAllStrings());
			trans.load();
			try {
				fill(trans.getLastRecord());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jbUpdate) {
			trans.updateRecord(getAllStrings());
			trans.load();
			fill(trans.getAbsoluteRecord(trans.getCurrentRow()));
		} else if (e.getSource() == jbDelete) {
			trans.deleteRecord(getAllStrings());
			trans.load();
			fill(trans.getAbsoluteRecord(trans.getCurrentRow()));
		} else if (e.getSource() == jbCancel) {
			fill(trans.getAbsoluteRecord(trans.getCurrentRow()));
		}
	}

	public String[] getAllStrings() {
		String[] content = new String[trans.getColumnCount()];
		for (int i = 0; i < trans.getColumnCount(); i++) {
			content[i] = jtfs[i].getText();
		}
		return content;
	}
}

class DBNavigation{
	private String database;
	private String table;

	private Connection con = null;
	private Statement stm = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	private int rowCount = 0;
	private int columnCount = 0;

	private String[] columnNames = null;
	private String[] result = null;

	private int currentRow = 0;

	public DBNavigation(String database, String table) {
		this.database = database;
		this.table = table;

		init();
		load();
	}
//odbc驱动
//      String DBDriver="sun.jdbc.odbc.JdbcOdbcDriver";
//      String uri="jdbc:odbc:CKconn";
//access驱动
//      String DBDriver="com.hxtt.sql.access.AccessDriver";
//      String uri="jdbc:access:////f:/tree/ck.mdb";
//mysql驱动
//      String DBDriver="com.mysql.jdbc.Driver"
//      String uri="jdbc:mysql://localhost:3306/students?useSSL=true"

	public void init() {
		//access驱动
		String DBDriver="com.hxtt.sql.access.AccessDriver";
		String uri="jdbc:access:////";
		try {
			Class.forName(DBDriver);
			con = DriverManager.getConnection(uri+database,"admin","");
			stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void load() {
		try {
			rs = stm.executeQuery("select count(*) from " + table);
			rs.next();
			rowCount = rs.getInt(1);

			rs = stm.executeQuery("select * from " + table);
			rsmd = rs.getMetaData();
			columnCount = rsmd.getColumnCount();

			columnNames = new String[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}

			result = new String[columnCount];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String getDatabase() {
		return database;
	}

	public String getTable() {
		return table;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public String[] getFirstRecord() {
		try {
			rs.first();
			for (int i = 1; i <= columnCount; i++) {
				result[i - 1] = rs.getString(i);
			}
			currentRow = rs.getRow();
			System.out.println(rs.getRow());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String[] getPreviousRecord() throws SQLException {
		rs.previous();
		if (rs.isBeforeFirst()) {
			rs.first();
		}
		for (int i = 1; i <= columnCount; i++) {
			result[i - 1] = rs.getString(i);
		}
		currentRow = rs.getRow();
		System.out.println(rs.getRow());
		return result;
	}

	public String[] getNextRecord() throws SQLException {
		rs.next();
		if (rs.isAfterLast()) {
			rs.last();
		}
		for (int i = 1; i <= columnCount; i++) {
			result[i - 1] = rs.getString(i);
		}
		currentRow = rs.getRow();
		System.out.println(rs.getRow());
		return result;
	}

	public String[] getLastRecord() throws SQLException {
		rs.last();
		for (int i = 1; i <= columnCount; i++) {
			result[i - 1] = rs.getString(i);
		}
		currentRow = rs.getRow();
		System.out.println(rs.getRow());
		return result;
	}

	public void insertRecord(String[] values) {
		String sql = "";

		try {
			for (int i = 0; i < values.length; i++) {
				if (rsmd.getColumnType(i + 1) == Types.VARCHAR
						|| rsmd.getColumnType(i + 1) == Types.TIMESTAMP) {
					sql += "'" + values[i] + "'";
				} else {
					sql += values[i];
				}
				if (i < values.length - 1)
					sql += ",";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Statement insertStm = con.createStatement();
			insertStm.execute("insert into " + table + " values(" + sql + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateRecord(String[] values) {
		String sql = "";

		String whereSQL = "";
		try {
			for (int i = 0; i < values.length; i++) {
				Vector keyFields = getKeyFields();

				if (rsmd.getColumnType(i + 1) == Types.VARCHAR
						|| rsmd.getColumnType(i + 1) == Types.TIMESTAMP) {
					sql += rsmd.getColumnName(i + 1) + "='" + values[i] + "'";
					if (keyFields.contains(rsmd.getColumnName(i + 1)))
						whereSQL += rsmd.getColumnName(i + 1) + "='"
								+ values[i] + "'";
				} else {
					sql += rsmd.getColumnName(i + 1) + "=" + values[i] + "";
					if (keyFields.contains(rsmd.getColumnName(i + 1)))
						whereSQL += rsmd.getColumnName(i + 1) + "=" + values[i];
				}
				if (i < values.length - 1)
					sql += ",";
			}
			Statement updateStm = con.createStatement();
			updateStm.executeUpdate("update " + table + " set " + sql
					+ " where " + whereSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 获取主键字段的函数
	private Vector getKeyFields() {
		// 主键字段可能包含多个字段，所以使用向量存储
		Vector key = new Vector();
		try {
			// 创建数据库元数据类变量
			DatabaseMetaData dmd = con.getMetaData();

			// 得到指定表的主键信息
			ResultSet keys = dmd.getPrimaryKeys(null, null, table);

			// 记录数与主键字段数相等，结果集的第4个字段为字段名称
			while (keys.next()) {
				key.add(keys.getString(4));
				System.out.println("keys.getString(4)=" + keys.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}

	public String[] getAbsoluteRecord(int number) {
		try {
			rs.beforeFirst();
			while (rs.next())
				if (rs.getRow() == number)
					break;
			if (rs.isAfterLast())
				rs.last();
			for (int i = 1; i <= columnCount; i++) {
				result[i - 1] = rs.getString(i);
			}
			currentRow = number;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void deleteRecord(String[] values) {
		String whereSQL = "";
		try {
			Vector keyFields = getKeyFields();

			for (int i = 0; i < values.length; i++) {
				if (keyFields.contains(rsmd.getColumnName(i + 1))) {
					if (rsmd.getColumnType(i + 1) == Types.VARCHAR
							|| rsmd.getColumnType(i + 1) == Types.TIMESTAMP) {
						whereSQL += rsmd.getColumnName(i + 1) + "='"
								+ values[i] + "'";
					} else {
						whereSQL += rsmd.getColumnName(i + 1) + "=" + values[i];
					}
				}
			}
			Statement deleteStm = con.createStatement();
			deleteStm.executeUpdate("delete from " + table + " where "
					+ whereSQL);
		} catch (Exception e) {

		}
	}

	public int getCurrentRow() {
		return currentRow;
	}
}