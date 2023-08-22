
//A vector class I created with operation functionality

public class Vec {
    double x;
    double y;
    public Vec(){
        x=0;
        y=0;
    }
    public Vec(double x,double y){
        this.x=x;
        this.y=y;
    }
    public void update(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double getMagnitude(){
        return Math.sqrt(x*x+y*y);
    }
    public Vec multiply(double k){
        x=k*x;
        y=k*y;
        return new Vec(x,y);
    }
    public void changeMagnitude(double target){
        double magRatio = target/Math.sqrt(x*x+y*y);
        multiply(magRatio);
    }
    public static double distanceBetween(Vec v1, Vec v2){
        double xDist = Math.abs(v1.getX()-v2.getX());
        double yDist = Math.abs(v1.getY()-v2.getY());
        return Math.sqrt(xDist*xDist+yDist*yDist);
    }
    public double getY() {return y;}

    public double getX() {
        return x;
    }

    public void setZero(){
        x=0;
        y=0;
    }
    public void subtract(Vec... args){
        for(Vec arg:args){
            x -= arg.getX();
            y -= arg.getY();
        }
    }
    public static Vec subtractR(Vec v1,Vec v2){
        return new Vec(v1.getX()-v2.getX(),v1.getY()-v2.getY());
    }
    public static Vec sum(Vec... args){
        double thisx = 0;
        double thisy = 0;
        for(Vec arg:args){
            thisx += arg.getX();
            thisy += arg.getY();
        }
        return new Vec(thisx,thisy);
    }
    public void printInfo(){
        System.out.printf("x: %.2f y: %.2f%n",x,y);
    }

}
