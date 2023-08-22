import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.ArrayList;



public class Core extends JComponent {
    int ms = 0;
    int w = 1280;
    int h = 720;
    int count = 0;

    public List<Ellipse2D.Double> circleList = new ArrayList<>();

    public Core(){
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ms++;
                System.out.println(count);
                if(ms%100 == 10){
                    addBall(400,300, 5+Math.random()*40);
                   count++;
                }
                Solver.update(0.1);
                repaint();

            }
        });
        timer.start();
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(0,0,w,h);
        g2d.setColor(Color.WHITE);
        g2d.fillOval((w/2)-300,(h/2)-300,600,600);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(Integer.toString(ms),5,15);
        g2d.setColor(Color.RED);
        circleList.clear();
        for(Ball ball:Solver.ballList) {
            circleList.add(new Ellipse2D.Double(ball.getX(),ball.getY(),ball.radius*2,ball.radius*2));
        }
        for(Ellipse2D.Double circle:circleList){
            g2d.fill(circle);
        }
        g2d.dispose();

        Toolkit.getDefaultToolkit().sync();
    }
    public void addBall(double x, double y, double radius){
        Solver.ballList.add(new Ball(new Vec(x,y),radius));
    }
}

