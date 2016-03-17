package com.mzx.pptui.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IPDialog extends JDialog implements ActionListener {

    JComboBox comboBox;
    JButton ok;
    JButton cancle;

    String ip;

    public IPDialog(JFrame owner, String name, boolean model) {
        super(owner, name, model);
        iniView();
    }

    private void iniView() {
        this.setTitle("ѡ�����ھ�����IP");
        this.setBounds(200, 200, 300, 100);
        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        this.setUndecorated(true);				//���öԻ���δ��װ��   ���ô����ޱ�Ե
        contentPane.setBackground(Color.lightGray);
        JLabel label = new JLabel("select IP add:");
        contentPane.add(label);

        comboBox = new JComboBox();

        comboBox.setMaximumSize(new Dimension(300, 30));		//�̶��������˵��Ŀ�Ⱥ͸߶�
        comboBox.setMinimumSize(new Dimension(100, 30));
        comboBox.setPreferredSize(new Dimension(200,30));

        ok = new MyButton2("OK");
        ok.setEnabled(true);
        ok.addActionListener(this);
        cancle = new MyButton2("Cancle");
        cancle.addActionListener(this);

        ok.setFocusable(false);						//ȥ����ť�ı߿�
        ok.setBorderPainted(false);
        cancle.setFocusable(false);
        cancle.setBorderPainted(false);

        ok.setLayout(null);
        cancle.setLayout(null);
        ok.setBounds(50, 70, 40, 20);
        cancle.setBounds(200, 70, 40, 20);

        String[] ips = getAllLocalHostIP();
        for (int i = 0; i < ips.length; i++) {
            comboBox.addItem(ips[i]);
        }

        ComboBoxEvent();
        contentPane.add(comboBox);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1, 4));
        jp.setBackground(Color.LIGHT_GRAY);   //����jp �ı�����ɫ

        jp.add(new JLabel());

        jp.add(ok);
        jp.add(new JLabel());
        jp.add(cancle);
        contentPane.add(jp);
        centerWindow();

        this.setVisible(true);
    }

    public String getIp() {
        return ip;
    }

    private void ComboBoxEvent() {
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                ip = ((JComboBox) e.getSource()).getSelectedItem().toString();
                ok.setEnabled(true);
                System.out.println(ip);
            }
        });
    }

    private void centerWindow() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2);
    }

    private String getLocalHostName() {
        String hostName;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
        } catch (Exception ex) {
            hostName = "";
        }
        return hostName;
    }

    private String[] getAllLocalHostIP() {
        String[] ret = null;
        try {
            String hostName = getLocalHostName();
            if (hostName.length() > 0) {
                InetAddress[] addrs = InetAddress.getAllByName(hostName);
                if (addrs.length > 0) {
                    ret = new String[addrs.length];
                    System.out.println(addrs.length);
                    for (int i = 0; i < addrs.length; i++) {
                        ret[i] = addrs[i].getHostAddress();

                    }
                }
            }

        } catch (Exception ex) {
            ret = null;
        }
        return ret;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();
        if (button.getText().toString().equals("OK")) {
            this.dispose();
        }

        else {
            ip = null;
            this.dispose();
        }
    }


}

/****
 * ������Բ�εİ�ť	
 * @author Administrator
 *
 */

class MyButton2 extends JButton {
    private static final long serialVersionUID = 1965063150601339314L;

    public MyButton2(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false); // ��һ��ǳ���Ҫ, �����໹����ư�ť������.
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(0, 0, width, height, 20, 20);

        super.paintComponent(g); // �������������, �ø�������ڰ�ť�ϻ�������.
    }

}
