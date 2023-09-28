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
import java.util.*;

public class TestPolymorphism {

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new ColoredPoint(2, 4, 0xFF0000FF));
        points.add(new PinnablePoint(1, 1));
        moveAndPrintPoints(points);
    }

    static void moveAndPrintPoints(List<Point> list) {
      if(list.isEmpty()) {
        return; // base case
      }

      Point p = list.remove(0);
      p.move(1,1);
      System.out.println(p);

      moveAndPrintPoints(list); // recursion
    }
}

