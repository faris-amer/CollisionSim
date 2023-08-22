public class Ball {
    Vec currentPos;
    Vec oldPos;
    Vec a = new Vec(0,0);
    Vec gravity = new Vec(0,9.8);
    Vec center = new Vec(640,360);
    Vec currentCenter = new Vec();
    Vec v = new Vec();
    double radius;

    //Ball with a position vector and a radius 

    public Ball(Vec currentPos,double radius){
        this.currentPos=currentPos;
        this.oldPos = currentPos;
        this.radius=radius;
    }

    //method to update the position of the ball, works as such
    // take 'velocity' by finding difference in last position to current position
    // accelerate (set acceleration)
    // finds new position by adding current position, velocity, and acceleration*timeframe

    public void updatePos(double dt){
        v = Vec.subtractR(currentPos,oldPos);
        accelerate(gravity);
        oldPos = currentPos;
        currentPos = Vec.sum(currentPos,v,a.multiply(dt));
        doCollision();
        a.setZero();
    }

    public void accelerate(Vec acc){
        a = Vec.sum(a,acc);
    }

    public void doCollision(){
        currentCenter.update(currentPos.getX()+radius,currentPos.getY()+radius);
        Vec to_obj = Vec.subtractR(currentCenter,center);
        double distance = Vec.distanceBetween(currentCenter,center);
        if(distance>300-radius){
            Vec u = to_obj.multiply(1/distance);
            currentCenter = Vec.sum(center,u.multiply(300-radius));
            currentPos.update(currentCenter.getX()-radius,currentCenter.getY()-radius);
        }
    }
    public double getX(){
        return currentPos.getX();
    }
    public double getY(){return currentPos.getY();}

    public Vec getCenterPosition(Ball b1){
        return currentPos;
    }

    public void printInfo(){
        System.out.println("X:"+currentPos.getX()+" Y:"+currentPos.getY()+" vX:"+v.getX()+" vY:"+v.getY()+" dist:"+Vec.distanceBetween(currentCenter,center));
    }
}
