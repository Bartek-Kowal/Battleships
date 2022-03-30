package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragShipPanel extends JPanel {

    ImageIcon five_masted_image = new ImageIcon("Five-masted.png");
    final int five_masted_WIDTH = five_masted_image.getIconWidth();
    final int five_masted_HEIGHT = five_masted_image.getIconHeight();
    Point five_masted_imageCorner;
    Point five_masted_prevPT;

    ImageIcon four_masted_image = new ImageIcon("Four-masted.png");
    final int four_masted_WIDTH = four_masted_image.getIconWidth();
    final int four_masted_HEIGHT = four_masted_image.getIconHeight();
    Point four_masted_imageCorner;
    Point four_masted_prevPT;

    ImageIcon three_masted_image = new ImageIcon("Three-masted.png");
    final int three_masted_WIDTH = three_masted_image.getIconWidth();
    final int three_masted_HEIGHT = three_masted_image.getIconHeight();
    Point three_masted_imageCorner;
    Point three_masted_prevPT;

    ImageIcon two_masted_image = new ImageIcon("Two-masted.png");
    final int two_masted_WIDTH = two_masted_image.getIconWidth();
    final int two_masted_HEIGHT = two_masted_image.getIconHeight();
    Point two_masted_imageCorner;
    Point two_masted_prevPT;

    public DragShipPanel()
    {
        five_masted_imageCorner = new Point(500, 0);
        four_masted_imageCorner = new Point(500, 100);
        three_masted_imageCorner = new Point(500, 200);
        two_masted_imageCorner = new Point(500, 300);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        five_masted_image.paintIcon(this,g,(int)five_masted_imageCorner.getX(),(int)five_masted_imageCorner.getY());
        four_masted_image.paintIcon(this,g,(int)four_masted_imageCorner.getX(),(int)four_masted_imageCorner.getY());
        three_masted_image.paintIcon(this,g,(int)three_masted_imageCorner.getX(),(int)three_masted_imageCorner.getY());
        two_masted_image.paintIcon(this,g,(int)two_masted_imageCorner.getX(),(int)two_masted_imageCorner.getY());
    }

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            five_masted_prevPT = e.getPoint();
            four_masted_prevPT = e.getPoint();
            three_masted_prevPT = e.getPoint();
            two_masted_prevPT = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {


            Point five_masted_currentPt = e.getPoint();
            five_masted_imageCorner.translate(

                    (int) (five_masted_currentPt.getX() - five_masted_prevPT.getX()),
                    (int) (five_masted_currentPt.getY() - five_masted_prevPT.getY())
            );
            five_masted_prevPT = five_masted_currentPt;
            repaint();

        }
    }
}
