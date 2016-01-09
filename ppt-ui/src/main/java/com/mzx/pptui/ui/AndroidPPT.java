package com.mzx.pptui.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AndroidPPT extends JFrame {
    protected MyButton _pre, _next, _big, _small, _load, _first, _last, _broadcastIP, _exit;
    JLabel _label;

    protected JPanel _p1, _p2, _p3;
    protected FileDialog _fileDialog = new FileDialog(this);

    public int _currentPage, _pages;
    public String _Path;
    public String _fileName;

    private String _ipString;
    private String _subnetString;
    private String _broadcastAddress;
    String _sub = "255.255.255.0";

    //用来显示初始界面的图片
    public boolean flag = false; //用来控制是否要隐藏按钮，false代表不隐藏
    //IPDialog dialog;

    public AndroidPPT(String title) {

        super(title);


        this.setUndecorated(true);  //无标题修饰

        ImageIcon image = new ImageIcon("exam.jpg");     //先在主界面显示一张图片
        _label = new JLabel();
        _label.setIcon(image);
        this.add(_label, "Center");
        _label.setVisible(true);

        layoutFrame();
        ButtonEvent();
        windowsListerner();

    }

    protected void layoutFrame() {
        this.setSize(850, 550);
        _broadcastIP = new MyButton("broadcast");
        // broadcastIP = new JComboBox();
        _load = new MyButton("load");
        _big = new MyButton("big");
        _small = new MyButton("small");
        _pre = new MyButton("Prior");
        _next = new MyButton("Next");
        _first = new MyButton("First");
        _last = new MyButton("Last");
        _exit = new MyButton("Exit");


//        _load.setFocusable(false);						//去掉按钮的边框
//        _load.setBorderPainted(false);
//        _broadcastIP.setFocusable(false);
//        _broadcastIP.setBorderPainted(false);
//        _big.setFocusable(false);
//        _big.setBorderPainted(false);
//        _small.setFocusable(false);
//        _small.setBorderPainted(false);
//        _pre.setFocusable(false);
//        _pre.setBorderPainted(false);
//        _next.setFocusable(false);
//        _next.setBorderPainted(false);
//        _first.setFocusable(false);
//        _first.setBorderPainted(false);
//        _last.setFocusable(false);
//        _last.setBorderPainted(false);
//        _exit.setFocusable(false);
//        _exit.setBorderPainted(false);



        _p1 = new JPanel();

        _p1.setVisible(false);		//开始右边的面板为不可见
        _p1.setLayout(new GridLayout(9,1));

        _p3 = new JPanel();    //面板p3作用是为了拉开图片与窗体左边的边距，还有点击这里也可以实现对按钮的隐藏

        _p3.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {    //鼠标点击事件，在左侧的面板上响应

            public void mouseClicked(MouseEvent e) {
                if(flag == false){    //按下按钮就隐藏
                    flag = true;
                    _p1.setVisible(false);
                }else{					//按下按钮就出现
                    flag = false;
                    _p1.setVisible(true);
                }

            }

        });


        MouseAdapter moveWindowListener = new MouseAdapter() {

            Point lastPoint = null;

            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getLocationOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point point = e.getLocationOnScreen();
                int offsetX = point.x - lastPoint.x;
                int offsetY = point.y - lastPoint.y;
                Rectangle bounds = AndroidPPT.this.getBounds();
                bounds.x += offsetX;
                bounds.y += offsetY;
                AndroidPPT.this.setBounds(bounds);
                lastPoint = point;
            }
        };
        this.addMouseListener(moveWindowListener);    //可以对窗体进行拖动
        this.addMouseMotionListener(moveWindowListener);

        this.setFocusable(true);   //设定本焦点在窗口，这句话很重要，不然键盘事件将不起作用

        _p1.add(_broadcastIP);
        _p1.add(_load);
        _p1.add(_big);
        _p1.add(_small);
        _p1.add(_pre);
        _p1.add(_next);
        _p1.add(_first);
        _p1.add(_last);
        _p1.add(_exit);

        this.add(_p3, "West");
        //	this.add(svgCanvas, "Center"); 						//先把svgCanvas覆盖掉
        this.add(_p1, "East");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerWindow();
        this.setVisible(true);

    }

    /**
     * 窗口居中
     */
    protected void centerWindow() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - getWidth()) / 2,
                (screenHeight - getHeight()) / 2);
    }

    /**
     * 获取路径
     */
    private void getPath() {
        FileDialog d = new FileDialog(this);
        d.setVisible(true);
        String s = d.getDirectory();
        _fileName = d.getFile();
        System.out.println("1111111" + _fileName);
        System.out.println(s + _fileName);
        _Path = s + _fileName;
    }

    /**
     * 按钮事件处理
     */
    protected void ButtonEvent() {
        _pre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }

        });

        _first.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }

        });

        _last.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }

        });

        _next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        _exit.addActionListener(new ActionListener(){    //按了这个按钮之后会退出
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        _load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }

        });

        _big.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        _small.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        _broadcastIP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void windowsListerner() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // System.out.println("dfsfaaaa");
                changeSize();
            }
        });

    }

    protected void changeSize() {


    }


    /**
     * show PPT
     */
    protected void showPPT() {



    }

}


