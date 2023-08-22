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
                if(ms%7 == 0){

                    //addBall(500,250, 3+Math.random()*10);
                    addRandomBalls(ms);

                    count++;
                }
                int substeps = 8;
                for (int i = 1; i <= substeps; i++) {
                    Solver.update(0.01/substeps);
                }
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
        circleList.clear();
        for(Ball ball:Solver.ballList) {
            g2d.setColor(ball.color);
            Ellipse2D circle = (new Ellipse2D.Double(ball.getX(),ball.getY(),ball.radius*2,ball.radius*2));
            g2d.fill(circle);
        }
        //for(Ellipse2D.Double circle:circleList){}
        g2d.dispose();

        Toolkit.getDefaultToolkit().sync();
    }
    public void addBall(double x, double y, double radius){
        Solver.ballList.add(new Ball(new Vec(x,y),radius));
    }
    public void addRandomBalls(double time){

        time = Math.sin(time/100);
        System.out.println(time);


        double radius = 5+Math.random()*10;
        double x = w/2.0-radius;
        double y = h/4.0;
        Solver.ballList.add(new Ball(new Vec(x,y),radius,new Color((int) (200-Math.abs(time)*150), 50, (int) (50+Math.abs(time)*150)),new Vec(time,Math.abs(time))));
    }
}

