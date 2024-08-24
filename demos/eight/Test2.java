package com.echo.demos.eight;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;


/**
 * 测试
 */

public class Test2 extends JFrame implements TreeModelListener {

    DefaultMutableTreeNode root =new DefaultMutableTreeNode("今天要买的东西");
    //使用DefaultMutableTreeNode创建4个枝节点
    DefaultMutableTreeNode node1 =new DefaultMutableTreeNode("蔬菜");
    DefaultMutableTreeNode node2 =new DefaultMutableTreeNode("水果");
    DefaultMutableTreeNode node3 =new DefaultMutableTreeNode("礼品");
    DefaultMutableTreeNode node4 =new DefaultMutableTreeNode("家用小物品");

    //定义按钮，完成操作
    JButton addSiblingBtn = new JButton("添加兄弟结点");
    JButton addChildBtn = new JButton("添加子结点");
    JButton deleteBtn = new JButton("删除结点");
    JButton editBtn = new JButton("编辑当前结点");


    public Test2(){
        setTitle("购物清单");

        JPanel panel = new JPanel();    //创建中间容器

//        setContentPane(panel);  //添加中间容器

        //将4个枝节点添加到根节点
        root.add(node1);
        root.add(node2);
        root.add(node3);
        root.add(node4);

        //使用其创建叶节点，并且将叶子节点添加到不同的枝节点上
        DefaultMutableTreeNode baicai =new DefaultMutableTreeNode("白菜");
        node1.add(baicai);
        DefaultMutableTreeNode dasuan = new DefaultMutableTreeNode("大蒜");
        node1.add(dasuan);
        DefaultMutableTreeNode tudou = new DefaultMutableTreeNode("土豆");
        node1.add(tudou);

        DefaultMutableTreeNode pingguo = new DefaultMutableTreeNode("苹果");
        node2.add(pingguo);
        DefaultMutableTreeNode xiangjiao = new DefaultMutableTreeNode("香蕉");
        node2.add(xiangjiao);
        DefaultMutableTreeNode xigua = new DefaultMutableTreeNode("西瓜");
        node2.add(xigua);

        DefaultMutableTreeNode  lipin = new DefaultMutableTreeNode("礼品");
        node3.add(lipin);
        DefaultMutableTreeNode  maotai = new DefaultMutableTreeNode("茅台酒");
        node3.add(maotai);
        DefaultMutableTreeNode  maipian = new DefaultMutableTreeNode("营养麦片");
        node3.add(maipian);
        DefaultMutableTreeNode   shipin = new DefaultMutableTreeNode("保健食品");
        node3.add(shipin);

        DefaultMutableTreeNode  weijing = new DefaultMutableTreeNode("味精");
        node4.add(weijing);
        DefaultMutableTreeNode  jiangyou = new DefaultMutableTreeNode("酱油");
        node4.add(jiangyou);
        DefaultMutableTreeNode   xijiejing = new DefaultMutableTreeNode("洗洁精");
        node4.add(xijiejing);
        DefaultMutableTreeNode   baoxiandai = new DefaultMutableTreeNode("保鲜袋");
        node4.add(baoxiandai);

        JTree tree = new JTree(root);   //创建一个系统默认的树组件
        tree.setEditable(true);     //设置JTree为可编辑

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        addSiblingBtn.addActionListener(e -> {
            //添加兄弟结点的逻辑
//                    1， 获取当前选中节点
            // DefaultMutableTreeNode这个是一个实现类
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
            //如果结点为空，则直接返回
            if (selectedNode == null){
                return;
            }
//                    2，获取该选中结点的父结点
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
            //如果父结点为空，则直接返回
            if (parent==null){
                return;
            }
            //                3，创建一个新结点
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新结点");
            //                4，把新结点通过父结点进行添加
            //获取选中结点的索引
            int selectedIndex = parent.getIndex(selectedNode);
            //在选中位置插入新结点
            model.insertNodeInto(newNode,parent,selectedIndex);

            //                5，显示新结点
            //获取从根结点到新结点的所有结点,这个函数是返回从根节点到newNode新节点的路径
            TreeNode[] pathToRoot = model.getPathToRoot(newNode);

            //使用指定的结点数组创建TreePath
            TreePath treePath = new TreePath(pathToRoot);

            //显示指定的treePath
            tree.scrollPathToVisible(treePath); //将视野移动到这个新节点
            //6，重绘tree
            tree.updateUI();
//                label.setText("新增节点成功");

        });

        /**
         * 添加子节点
         */

        addChildBtn.addActionListener(e -> {    //兰姆达表达式写法，会更方便点
            //为选中节点添加子节点

            //1，获取选中结点
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode==null){
                return ;
            }

            //2，创建新结点
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新结点");

            //3，把新节点添加到当前节点中

            //model.insertNodeInto(newNode,selectedNode,selectedNode.getChildCount());使用TreeModel的方法添加，不需要手动刷新UI
            selectedNode.add(newNode);//使用TreeNode的方法添加，需要手动刷新UI

            //4，显示新节点

            //显示新结点

            TreeNode[] pathToRoot = model.getPathToRoot(newNode);
            TreePath treePath = new TreePath(pathToRoot);
            tree.scrollPathToVisible(treePath);

            //5，重绘ui
            //手动刷新UI
            tree.updateUI();
//            label.setText("新增子节点成功");
        });


        /**
         * 删除节点
         */

        deleteBtn.addActionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode!=null && selectedNode.getParent()!=null){
                //删的时候要找到其父节点才能删，所以说，最后的那个根节点，不能删
                model.removeNodeFromParent(selectedNode);
            }
//            label.setText("删除节点成功");
        });

        /**
         * 编辑节点
         */
        //实现编辑结点的监听器
        editBtn.addActionListener(e -> {
            //获取当前选中节点的路径
            TreePath selectionPath = tree.getSelectionPath();
            //判断路径不为空，则设置最后一个节点可以编辑
            if (selectionPath!=null){
                //编辑选中结点
                tree.startEditingAtPath(selectionPath);
            }

        });

        //panel用来写按钮
        panel.add(addSiblingBtn);
        panel.add(addChildBtn);
        panel.add(deleteBtn);
        panel.add(editBtn);
        panel.setLayout(new FlowLayout());
        /**
         * 创建一个面板,用来装树和按钮
         *创建一个分隔栏
         *
         */
        JPanel panel1 = new JPanel();
//        panel1.add(panel,BorderLayout.NORTH);
//
//        panel1.add(new JScrollPane(tree),BorderLayout.EAST);
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(new JScrollPane(tree));
        panel1.add(panel);
//        add(panel, BorderLayout.NORTH);
//        add(new JScrollPane(tree),BorderLayout.CENTER);

        JSplitPane js = new JSplitPane();


        js.setOneTouchExpandable(true);
        js.setContinuousLayout(true);
        js.setPreferredSize(new Dimension(300,400));
        js.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        js.setDividerSize(1);
        js.setDividerLocation(50);

        js.setLeftComponent(panel1);  //左边内容
//      JPanel panel2 = new JPanel();
        JButton jButton = new JButton();

        //-------------------
        JTabbedPane tabbedPane= new JTabbedPane(); //创建一个选项卡容器
        setContentPane(tabbedPane);  //将之添加到顶层容器内

        JPanel P1=new JPanel();
        ClrkJPanel clrkJPanel = new ClrkJPanel();
        JPanel P2=new JPanel();
        JPanel P3=new JPanel();

        Label a1 = new Label("内容1");

        Button b2 = new Button("内容2");
        Label a3 = new Label("内容3");

        P1.add(a1);
        P2.add(b2);
        P3.add(a3);



        //添加选项卡容器，并且设置其中每一个选项卡的标签以及是否可启用
        tabbedPane.addTab("选项卡1",clrkJPanel);
        // 设置 index 位置的选项卡是否可用
//        tabbedPane.setEnabledAt(0,true);
        // 设置 index 位置的选项卡的标题
//        tabbedPane.setTitleAt(0,"个人收入状况");

        tabbedPane.addTab("选项卡2",P2);
//        tabbedPane.setEnabledAt(1,true);
//        tabbedPane.setTitleAt(1,"工资");

        tabbedPane.addTab("选项卡3",P3);
//        tabbedPane.setEnabledAt(2,true);
//        tabbedPane.setTitleAt(2,"奖金");

        // 设置默认选中的选项卡
        tabbedPane.setSelectedIndex(1);
//      setSize(500,300);
        tabbedPane.setPreferredSize(new Dimension(500,200));


        //------------------



        js.setRightComponent(tabbedPane); //右边内容

        //将js放到顶层容器中
        setContentPane(js);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public void setJPanel(){

    }

    public static void main(String[] args) {
        new Test2();
    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {

    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {

    }
}

