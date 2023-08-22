import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

public class tes {
    public static void main(String[] args) {
        Vec v1 = new Vec(10,20);
        Vec v2 = new Vec(20,30);
        Vec v3 = new Vec(30,40);
        Vec v4 = Vec.sum(v2,v3);
        System.out.println("x:"+v4.getX()+" y:"+v4.getY());
    }
}
