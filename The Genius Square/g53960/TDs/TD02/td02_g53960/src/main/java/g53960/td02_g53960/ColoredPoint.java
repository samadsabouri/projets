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
public class ColoredPoint extends Point {
    private int color;  // A 32-bit integer of the form: 0xRRGGBBAA
                        // where AA represents the alpha value

    public ColoredPoint(double x, double y, int color) {
        //System.out.println("test"); il va pas fonctionner car super(x,y); il doit etre en premiere ligne
        super(x, y);
        this.color = color;
    }

    public int getColor() { return color; }

    @Override
    public String toString() {
        return super.toString() + " - "+ String.format("%08X", color);
    }
}
