package com.mzx.pptui.ui;

import com.mzx.pptcommon.exception.PPTshowException;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.main.Main;
import com.mzx.pptui.utility.NetWorkOption;
import com.mzx.pptui.utility.PPTOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AndroidPPT extends JFrame {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected MyButton2 _pre, _next, _big, _small, _load, _first, _last, _broadcastIP, _exit;
    JLabel _label;

    protected JPanel _p1, _p2, _p3;
    protected FileDialog _fileDialog = new FileDialog(this);

    public int _currentPage, _pages;
    public String _Path;
    public String _fileName;

    private String _ipString;

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
        _broadcastIP = new MyButton2("broadcast");
        // broadcastIP = new JComboBox();
        _load = new MyButton2("load");
        _big = new MyButton2("big");
        _small = new MyButton2("small");
        _pre = new MyButton2("Prior");
        _next = new MyButton2("Next");
        _first = new MyButton2("First");
        _last = new MyButton2("Last");
        _exit = new MyButton2("Exit");

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
        logger.info("fileName: " + _fileName);
        logger.info(s + _fileName);
        _Path = s + _fileName;
    }

    /**
     * ��ť�¼�����
     */
    protected void ButtonEvent() {
        _pre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                byte[] bytes = PPTOption.swichPage(--_currentPage);
                if(bytes != null) {
                    ImageIcon ico = new ImageIcon(bytes);
                    _label.setIcon(ico);
                }
                else {
                    logger.info("���һҳ��");
                    _currentPage++;
                }
            }

        });

        _first.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageIcon ico = new ImageIcon(PPTOption.swichPage(0));
                _label.setIcon(ico);
                _currentPage = 0;
            }

        });

        _last.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GlobalApplication globalApplication = (GlobalApplication) Main.getBean("globalApplication");
                ImageIcon ico = new ImageIcon(PPTOption.swichPage(globalApplication.getLen() - 1));
                _label.setIcon(ico);
                _currentPage = globalApplication.getLen() - 1;
            }

        });

        _next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                byte[] bytes = PPTOption.swichPage(++_currentPage);
                if(bytes != null) {
                    ImageIcon ico = new ImageIcon(bytes);
                    _label.setIcon(ico);
                }
                else {
                    logger.info("���һҳ��");
                    _currentPage--;
                }
            }
        });

        _exit.addActionListener(new ActionListener() {    //���������ť֮����˳�
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        _load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getPath();
                _currentPage = 0;
                showPPT(_Path);
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

                IPDialog dialog = new IPDialog(AndroidPPT.this, "��ȡIP", true);

                if ((_ipString = dialog.getIp()) == null) {
                    return;
                }

                NetWorkOption.broadcastIP(_ipString);
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
    protected void showPPT(String path) {

        byte[] bytes = null;
        try {
            bytes = PPTOption.load(path);
        } catch (Exception ex) {
            if(ex instanceof PPTshowException) {
                return;
            }
        }
        ImageIcon ico = new ImageIcon(bytes);
        _label.setIcon(ico);

    }


}


