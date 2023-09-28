/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g53960.td02_g53960;

/**
 *
 * @author Samad
 */
public class TestPoints {

    public static void main(String[] args) {
        ColoredPoint p = new ColoredPoint(2, 4, 0xFF0000FF);
        //Point p = new ColoredPoint(2, 4, 0xFF0000FF); error de comp On ne trouve pas la méthode getColor()

        p.move(1, 2);
        System.out.println(p);
        System.out.println("x: " + p.getX());
        System.out.println("color : " + String.format("%08X", p.getColor()));
        //ColoredPoint p2 = new Point(2, 4); error de comp Un objet de type ColoredPoint a besoin de 3 attributs
      
    }
}
