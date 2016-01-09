package com.mzx.pptui.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zison on 2016/1/6.
 */
public class MyButton extends JButton{

    private static final long serialVersionUID = 1965063150601339314L;

    public MyButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false); // 这一句非常重要, 否则父类还会绘制按钮的区域.
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(0, 0, width, height, 50, 50);

        super.paintComponent(g); // 最后调用这个方法, 让父类绘制在按钮上绘制文字.
    }
}
