/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;

/**
 *
 * @author saini
 */
public class cluster {
    double[] centre;
        double[] uweight;
        double[] lweight;
        int pattern;
        cluster(int param)
		{
		centre=new double[param];
		uweight=new double[param];
		lweight=new double[param];
		pattern=0;
		}
    
}
