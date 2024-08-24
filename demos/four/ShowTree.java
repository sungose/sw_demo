package com.echo.demos.four;

import com.echo.services.ClggbDao;
import com.echo.utils.ToolUtils;

import java.sql.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ShowTree {
    public static void main(String[] args) {
        Tree_View myTree = new Tree_View();
    }
}


class Tree_View extends JFrame implements ActionListener, MouseListener {
    //mysql驱动
    String DBDriver = "com.mysql.cj.jdbc.Driver";
    //access驱动
//	String DBDriver = "com.hxtt.sql.access.AccessDriver";
//odbc连接
//      String connectionStr="jdbc:odbc:CKconn";
//access-jdbc直连
//		String connectionStr = "jdbc:access:////e:/tree/ck.mdb";
//mysql-jdbc		
//		String connectionStr = "jdbc:mysql://localhost:3306/cangku?useSSL=true&serverTimezone=CST";
    String connectionStr = "jdbc:mysql://localhost:3306/cangku";
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    ClggbDao clggb = null;
    JTree myTree;
    JScrollPane leftPane;

    JPopupMenu popMenu;// 右键菜单
    JMenuItem addItem;// 添加
    JMenuItem editItem;// 修改

    JMenuItem deleteItem;//删除

    Container contentPane;


    // 目录树的窗口方法
    public Tree_View() {
        super("材料目录树");
        clggb = new ClggbDao();
        ToolUtils.getCenter(this,900,600);
        if (linkDatabase())
            createTree();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  closeDatabase();
                              }
                          }
        );
    }

//    数据库的连接方法
    public boolean linkDatabase() {
        try {
            Class.forName(DBDriver);     //加载驱动器
            con = DriverManager.getConnection(connectionStr, "sun", "031217");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); //创建Statement对象
            System.err.println("数据库已连接！");
        }
        //捕获加载驱动程序异常
        catch (ClassNotFoundException cnfex) {
            System.err.println("装载 JDBC/ODBC 驱动程序失败。");
            return (false);
        }
        //捕获连接数据库异常
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
            System.err.println("无法连接数据库");
            return (false);
        }
        return (true);
    }


    //创建目录树的方法
    public void createTree() {
        DefaultMutableTreeNode[] aNode = new DefaultMutableTreeNode[7];
        String curHH = "", curMC;
        int dotCount = 0;
        try {
            rs = stmt.executeQuery("select * from clggb order by hh"); //查询表
//				rs.first();
            while (rs.next()) {      //显示所有记录
                curHH = rs.getString("hh").trim();
                curMC = rs.getString("mc").trim();
                // ToDo 没看懂
                dotCount = curHH.length() / 2;
                System.out.println(dotCount + "=" + curHH + ":" + curMC + "\n");
                if (dotCount == 0)
                    aNode[0] = new DefaultMutableTreeNode(curHH + ":" + curMC);
                else {
                    aNode[dotCount] = new DefaultMutableTreeNode(curHH + ":" + curMC);
                    aNode[dotCount - 1].add(aNode[dotCount]);
                }

            }
        } catch (Exception e) {
            System.err.println("数据库出错");
            e.printStackTrace();
            System.exit(1);  // terminate program
        }
        Container c = getContentPane();
        myTree = new JTree(aNode[0]);
        myTree.setEditable(true);
        myTree.getSelectionModel().setSelectionMode(javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION);
        myTree.addMouseListener(this);


        // myTree.setCellEditor(new DefaultTreeCellEditor(myTree, new DefaultTreeCellRenderer()));
        myTree.getCellEditor().addCellEditorListener(
                new MyTreeCellEditorListener(myTree));

        leftPane = new JScrollPane();//myTree
        leftPane.setViewportView(myTree);
        c.add(leftPane);
        addPopMenu();

//			c.add(new JScrollPane(myTree));
//    MyDefaultTreeCellRenderer x = new MyDefaultTreeCellRenderer();
//    tree.setCellRenderer(x)       //用于设置结点图标
    }
    private void addPopMenu() {
        // 使用JPopupMenu 弹出菜单
        popMenu = new JPopupMenu();
        addItem = new JMenuItem("添加");
        addItem.addActionListener(this);

        editItem = new JMenuItem("修改");
        editItem.addActionListener(this);

        deleteItem = new JMenuItem("删除");
        deleteItem.addActionListener(this);

        popMenu.add(addItem);
        popMenu.add(editItem);
        popMenu.add(deleteItem);
    }

    // 关闭数据库连接
    public void closeDatabase() {
        try {
            if (stmt != null) stmt.close(); //关闭语句
            if (con != null) con.close(); //关闭连接
        } catch (Exception e) {
            System.err.println("数据库出错");
            System.exit(1);  // terminate program
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) myTree.getLastSelectedPathComponent();

        // 判断点击的是哪个按钮
        if (e.getSource() == addItem) {
//            System.out.println("myTree.getSelectionPath().toString() = " + myTree.getSelectionPath().getPath().toString());
            String name = myTree.getSelectionPath().getLastPathComponent().toString();
            String[] split = name.split(":");
            String code = split[0];
            String newhh = code + ToolUtils.randomnumber();
            String n = newhh+":新增子节点";
            ((DefaultTreeModel) myTree.getModel()).insertNodeInto(new DefaultMutableTreeNode(n), node, node.getChildCount());
            System.out.println(newhh);
            try {
                clggb.insertClggb(n);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            myTree.expandPath(myTree.getSelectionPath());
        } else if (e.getSource() == editItem) {
            // System.out.println("myTree.getSelectionPath().toString() = " + myTree.getSelectionPath().toString());

            // System.out.println(myTree.getSelectionPath().getLastPathComponent());

            System.out.println("node.getUserObject() = " + node.getUserObject());
            myTree.startEditingAtPath(myTree.getSelectionPath());
        } else if (e.getSource() == deleteItem) {
            String name = node.getUserObject().toString();
            String[] split = name.split(":");
            String code = split[0];
            int delectOption = JOptionPane.showConfirmDialog(contentPane,"是否要删除该数据记录","提示" ,JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(delectOption == JOptionPane.OK_OPTION){
                if(clggb.deleteClggb(code) > 0){
                    JOptionPane.showMessageDialog(null,"删除成功！");
                    ((DefaultTreeModel) myTree.getModel()).removeNodeFromParent(node);
                }else {
                    JOptionPane.showMessageDialog(null,"删除失败！");
                }
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        TreePath path = myTree.getPathForLocation(e.getX(), e.getY());

        if (path == null) {
            return;
        }

        myTree.setSelectionPath(path);
        if (e.getButton() == 3) {
            popMenu.show(myTree, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class MyTreeCellEditorListener implements CellEditorListener {

    private JTree tree;
    ClggbDao clggb = new ClggbDao();

    public MyTreeCellEditorListener(JTree myTree) {
        this.tree = myTree;
    }

    public void editingStopped(ChangeEvent e) {
        Object selectnode = tree.getLastSelectedPathComponent();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectnode;
        CellEditor cellEditor = (CellEditor) e.getSource();
        String newName = (String) cellEditor.getCellEditorValue();
        System.out.println("newName = " + newName);
        // split将hh和mc由‘：’分开
        String[] split = newName.split(":");
        String code = split[0];
        String name = split[1];
        try {
            clggb.updateClggb(code,name);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        // TOdo 完成数据库的同步修改

//        点击事件发生后
//        先连接数据库
//        然后需要对数据货号hh和mc进行修改
//        将结果传递给数据库

        node.setUserObject(newName);

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.nodeStructureChanged(node);
    }

    public void editingCanceled(ChangeEvent e) {
        editingStopped(e);
    }
}

/*设置结点图标
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {
 public MyDefaultTreeCellRenderer() {
 }
 public Component getTreeCellRendererComponent(JTree tree, Object value,
   boolean sel, boolean expanded, boolean leaf, int row,
   boolean hasFocus) {
  super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
    row, hasFocus);
  Icon test = new ImageIcon("./images/test.jpg");
  Icon test1 = new ImageIcon("./images/test1.jpg");
  Icon test2 = new ImageIcon("./images/test2.jpg");
  Icon test3 = new ImageIcon("./images/test3.jpg");  if (leaf) {
   DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
   String str = value.toString();
   if (str.equals("自由飞翔")) {
    this.setIcon(test1);
   } else if (str.equals("我行我素")) {
    this.setIcon(test2);
   } else {
    this.setIcon(test3);
   }  } else {
   if (expanded) {
    this.setIcon(test);
   } else {
    this.setIcon(test);
   }
  }
  this.setText(value.toString());
  return this;
 }
}
*/
