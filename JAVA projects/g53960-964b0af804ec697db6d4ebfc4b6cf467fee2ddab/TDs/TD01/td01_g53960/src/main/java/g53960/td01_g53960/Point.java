/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.td01_g53960;

public class Point {
    
    private double x=10;
    private double y=10;
    
    public Point() {
        this(0,0);
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
}

class TestPoint {
    public static void main(String args[]) {
        Point p = new Point();
        System.out.println(p);
        p.move(2,2);
        System.out.println(p);
    }
}
