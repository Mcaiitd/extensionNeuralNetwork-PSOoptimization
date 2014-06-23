/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;

/**
 *
 * @author saini
 */

public class Velocity {
 public  double x[];
 
 Velocity(int dimension)
 {
     x = new double[dimension];
 }
 

 public Velocity(double[] a) {
 x = a;
 
 }

 public double[] getX() {
 return x;
 }

 public void setX(double a[]) {
 x = a;
 }

 
}

