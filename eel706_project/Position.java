/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;

/**
 *
 * @author saini
 */
public class Position {
 public double x[];
 
 Position(int dimension)
 {
     x = new double[dimension];
 }
 

 public Position(double a[])  {
 x = a;
 }

 public double[] getX() {
 return x;
 }

 public void setX(double a[]) {
    x = a;
 }
 public double get_feature(int feature)
 {
     return x[feature];
 }

 
 
}


