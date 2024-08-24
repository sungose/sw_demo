import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


abstract class AbstractButtonFunction {
    public abstract void ExecuteFunction(JMenuItem src);
}

class LiteratureFunction extends AbstractButtonFunction {
    public void ExecuteFunction(JMenuItem src) {
        System.out.println("按钮名：" + src.getText());
    }
}

class CookingFunction extends AbstractButtonFunction {
    public void ExecuteFunction(JMenuItem src) {
        System.out.println("按钮名：" + src.getText());
    }
}

class FootballFunction extends AbstractButtonFunction {
    public void ExecuteFunction(JMenuItem src) {
        System.out.println("按钮名：" + src.getText());
    }
}

class BasketballFunction extends AbstractButtonFunction {
    public void ExecuteFunction(JMenuItem src) {
        System.out.println("按钮名：" + src.getText());
    }
}


class MapMenuItem extends JMenuItem implements ActionListener {
    String buttonFuctionClassName;

    MapMenuItem(String buttonFunctionClassName) {
        super();
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    MapMenuItem(String text, Icon icon, String buttonFunctionClassName) {
        super(text, icon);
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    MapMenuItem(String text, String buttonFunctionClassName) {
        super(text);
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    MapMenuItem(Icon icon, String buttonFunctionClassName) {
        super(icon);
        addActionListener(this);
        this.buttonFuctionClassName = buttonFunctionClassName;
    }

    public void actionPerformed(ActionEvent event) {
        AbstractButtonFunction curButtonFunction;
        try {
            Class cs = Class.forName(buttonFuctionClassName);
            curButtonFunction = (AbstractButtonFunction) cs.newInstance();
            curButtonFunction.ExecuteFunction(this);
        } catch (Exception e) {
            System.out.println("按钮的功能类:(" + buttonFuctionClassName + ")没定义!");
        }
    }
}


public class Window_Menu extends JFrame {
    JMenuBar menubar;
    JMenu menu, subMenu;
    MapMenuItem itemLiterature, itemCooking;

    public Window_Menu() {

    }

    public Window_Menu(String s, int x, int y, int w, int h) {
        init(s);
        setLocation(x, y);
        setSize(w, h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    void init(String s) {
        setTitle(s);             // 设置窗口的标题
        menubar = new JMenuBar();
        menu = new JMenu("菜单");
        subMenu = new JMenu("体育话题");
        itemLiterature = new MapMenuItem("文学话题", new ImageIcon("a.gif"), "LiteratureFunction");
        itemCooking = new MapMenuItem("烹饪话题", new ImageIcon("b.gif"), "CookingFunction");
        itemLiterature.setAccelerator(KeyStroke.getKeyStroke('A'));
        itemCooking.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menu.add(itemLiterature);
        menu.addSeparator();   // 在菜单之间增加分隔线
        menu.add(itemCooking);
        menu.add(subMenu);
        subMenu.add(new MapMenuItem("足球", new ImageIcon("c.gif"), "FootballFunction"));
        subMenu.add(new MapMenuItem("篮球", new ImageIcon("d.gif"), "BasketballFunction"));
        menubar.add(menu);
        setJMenuBar(menubar);
    }
}
