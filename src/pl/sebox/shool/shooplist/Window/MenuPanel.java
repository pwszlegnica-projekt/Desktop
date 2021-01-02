package pl.sebox.shool.shooplist.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class MenuPanel extends JPanel {
    public MenuPanel(){
        super(null);
        setPreferredSize(new Dimension(20,400));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawOval(0, 0, getWidth(), getHeight());

        class Arrow extends Path2D.Double {

            public Arrow() {
                moveTo(0, 10);
                lineTo(36, 10);
                moveTo(36 - 16, 0);
                lineTo(36, 10);
                moveTo(36 - 16, 20);
                lineTo(36, 10);
            }

        }
        Arrow arrow = new Arrow();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));

        double x = (getWidth() - arrow.getBounds().getWidth()) / 2d;
        double y = (getHeight() - arrow.getBounds().getHeight()) / 2d;

        AffineTransform at = new AffineTransform();
        at.translate(x, y);
//        at.rotate(180, arrow.getBounds().getWidth() / 2d, arrow.getBounds().getHeight() / 2d);
        at.rotate(Math.toRadians(180), arrow.getBounds().getWidth() / 2d, arrow.getBounds().getHeight() / 2d);
        g2d.setTransform(at);

        g2d.draw(arrow);
        g2d.dispose();
    }

}
