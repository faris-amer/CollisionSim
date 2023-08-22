import jdk.nashorn.internal.objects.NativeObject;

import java.awt.*;

public class Ball {
    Vec currentPos;
    Vec oldPos;
    Vec a = new Vec(0,0);
    Vec gravity = new Vec(0,9.8);
    Vec center = new Vec(640,360);
    Vec currentCenter = new Vec();
    Vec v = new Vec();
    Vec initialVelocity = new Vec();
    Color color = Color.RED;
    double radius;

    //Ball with a position vector and a radius 

    public Ball(Vec currentPos,double radius){
        this.currentPos=currentPos;
        this.oldPos = currentPos;
        this.radius=radius;
    }
    public Ball(Vec currentPos,double radius, Color color){
        this.currentPos=currentPos;
        this.oldPos = currentPos;
        this.radius=radius;
        this.color = color;
    }
    public Ball(Vec currentPos,double radius, Color color, Vec v){
        this.currentPos=currentPos;
        this.oldPos = currentPos;
        this.radius=radius;
        this.color = color;
        this.initialVelocity = v;
    }


    //method to update the position of the ball, works as such
    // take 'velocity' by finding difference in last position to current position
    // accelerate (set acceleration)
    // finds new position by adding current position, velocity, and acceleration*timeframe

    public void updatePos(double dt){
        v = Vec.subtractR(currentPos, oldPos);
        accelerate(gravity);
        oldPos = currentPos;
        currentPos = Vec.sum(currentPos,v,a.multiply(dt),initialVelocity);
        doCollision();
        a.setZero();
        initialVelocity.setZero();
    }

    public void accelerate(Vec acc){
        a = Vec.sum(a,acc);
    }

    public void doCollision(){
        updateCenter();
        Vec to_obj = Vec.subtractR(currentCenter,center);
        double distance = Vec.distanceBetween(currentCenter,center);
        if(distance>300-radius){
            Vec u = to_obj.multiply(1/distance);
            currentCenter = Vec.sum(center,u.multiply(300-radius));
            currentPos.update(currentCenter.getX()-radius,currentCenter.getY()-radius);
        }
    }
    //takes a target ball, checks if it's within radius, then acts accordingly.
    public void doObjectCollision(Ball targetBall){
        updateCenter();
        targetBall.updateCenter();
        //find distance between
        double distance = Vec.distanceBetween(currentCenter,targetBall.currentCenter);
        //checking if balls are in range
        if (distance < radius+targetBall.radius){

            Vec AtoB = Vec.subtractR(currentCenter,targetBall.currentCenter);
            Vec BtoA = Vec.subtractR(targetBall.currentCenter,currentCenter);
            double difference = radius+targetBall.radius-distance;
            //AtoB.changeMagnitude(difference/(radius/(radius+ targetBall.radius)*4));
            //BtoA.changeMagnitude(difference/(targetBall.radius/(radius+ targetBall.radius)*4));
            AtoB.changeMagnitude(difference/2);
            BtoA.changeMagnitude(difference/2);
            currentPos.update(currentPos.getX()+AtoB.getX(),currentPos.getY()+ AtoB.getY());
            targetBall.currentPos.update(targetBall.currentPos.getX()+BtoA.getX(),targetBall.currentPos.getY()+ BtoA.getY());

        }
    }
    public double getX(){
        return currentPos.getX();
    }
    public double getY(){return currentPos.getY();}

    public void updateCenter(){
        currentCenter.update(currentPos.getX()+radius,currentPos.getY()+radius);
    }
    public Vec getCenterPosition(Ball b1){
        return currentPos;
    }

    public void printInfo(){
        System.out.println("X:"+currentPos.getX()+" Y:"+currentPos.getY()+" vX:"+v.getX()+" vY:"+v.getY()+" dist:"+Vec.distanceBetween(currentCenter,center));
    }
}
