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

    //������ʾ��ʼ�����ͼƬ
    public boolean flag = false; //���������Ƿ�Ҫ���ذ�ť��false��������
    //IPDialog dialog;

    public AndroidPPT(String title) {

        super(title);


        this.setUndecorated(true);  //�ޱ�������

        ImageIcon image = new ImageIcon("exam.jpg");     //������������ʾһ��ͼƬ
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


//        _load.setFocusable(false);						//ȥ����ť�ı߿�
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

        _p1.setVisible(false);		//��ʼ�ұߵ����Ϊ���ɼ�
        _p1.setLayout(new GridLayout(9,1));

        _p3 = new JPanel();    //���p3������Ϊ������ͼƬ�봰����ߵı߾࣬���е������Ҳ����ʵ�ֶ԰�ť������

        _p3.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {    //������¼����������������Ӧ

            public void mouseClicked(MouseEvent e) {
                if(flag == false){    //���°�ť������
                    flag = true;
                    _p1.setVisible(false);
                }else{					//���°�ť�ͳ���
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
        this.addMouseListener(moveWindowListener);    //���ԶԴ�������϶�
        this.addMouseMotionListener(moveWindowListener);

        this.setFocusable(true);   //�趨�������ڴ��ڣ���仰����Ҫ����Ȼ�����¼�����������

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
        //	this.add(svgCanvas, "Center"); 						//�Ȱ�svgCanvas���ǵ�
        this.add(_p1, "East");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerWindow();
        this.setVisible(true);

    }

    /**
     * ���ھ���
     */
    protected void centerWindow() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - getWidth()) / 2,
                (screenHeight - getHeight()) / 2);
    }

    /**
     * ��ȡ·��
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
     * ��ť�¼�����
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

        _exit.addActionListener(new ActionListener(){    //���������ť֮����˳�
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


