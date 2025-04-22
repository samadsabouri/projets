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

public class PinnablePoint extends Point {

    private boolean pinned; // once pinned cannot move.

    public PinnablePoint(double x, double y) {
        super(x, y);
        this.pinned = false;
    }

    public void pin() { pinned = true; } // once pinned, no way to unpin.

    @Override
    public Point move(double dx, double dy) {
        if(!pinned) {
            super.move(dx, dy);
        }
        // do nothing if pinned.
        return this;
    }


    @Override
    public String toString() {
        return super.toString() + " - "+ (pinned? "pinned": "not pinned");
    }

    public static void main(String[] args) {
        Point p = new PinnablePoint(0, 0);
        System.out.println(p);
        p.move(1, 1);
        PinnablePoint pp = (PinnablePoint)p; // we know it is a PinnablePoint
        pp.pin();
        p.move(1,1);
        System.out.println(p);
    }
}
