import java.util.ArrayList;
import java.util.List;

public class Solver {
    public static List<Ball> ballList = new ArrayList<>();
    Vec gravity = new Vec(0,-9.8);

    public static void update(double dt){
        for(Ball ball:ballList) {

            ball.updatePos(0.05);
        }
    }
}
