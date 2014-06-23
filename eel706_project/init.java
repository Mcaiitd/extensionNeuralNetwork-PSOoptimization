/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;

/**
 *
 * @author saini
 */
import java.util.Random;
import java.util.ArrayList;

public class init {
    
int SWARM_SIZE = 10;
    int DIMENSION = 8;
    int MAX_ITERATION = 10;
    double C1 = 2.0;
    double C2 = 2.0;
    double W_UP = 1.0;
    double W_LO = 0.0;
    
    swarm swarm1 = new swarm(SWARM_SIZE,DIMENSION);

private void initializeSwarm(cluster C,int clusterid, data[] input,int switc) {
     



 for (int i = 0; i < SWARM_SIZE; i++) {
Particle p = new Particle(DIMENSION);
 p.C=C;
 p.clusterid = clusterid;
 p.input=input;
 p.switc=switc;
 Random generator = new Random();

 double posX[]=new double[DIMENSION];
 
 for(int feature=0; feature<DIMENSION;feature++)
 {
     posX[feature]= generator.nextInt(5);    
    
 }
 
 double velX[]=new double[DIMENSION];
 
 for(int feature=0; feature<DIMENSION;feature++)
 {
     velX[feature]= 0;
     
    
 }
 Position newpos = new Position(DIMENSION);
 Velocity newvel = new Velocity(DIMENSION);
 newpos.setX(posX);
 newvel.setX(velX);
 p.setLocation((newpos));
 p.setVelocity((newvel));
 for(int k=0;k<5;k++)
                    {
                        //System.out.print(" "+posX[k]);
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }

 //for(int k=0;k<5;k++)
                    {
                        //System.out.print(" "+x[k]);
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
 /*for(int k=0;k<5;k++)
                    {
                        System.out.println("BEFORE ADDING "+p.getLocation().getX()[k]+" "+newpos.get_feature(k)+" "+posX[k]);
                    
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }*/
  swarm1.add(p);
  /*for(int k=0;k<5;k++)
                    {
                        System.out.println("AFTER ADDING "+swarm1.get(i).getLocation().get_feature(k)+""+p.getLocation().getX()[k]+" "+newpos.get_feature(k)+" "+posX[k]);
                    
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }*/
  


 }
 
   //for(int k=0;k<5;k++)
                    {
                        //System.out.print(" "+swarm1.get(5).getLocation().get_feature(k));
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
   //System.out.println();
 //System.out.println("SWARMSIZE"+swarm1.swarm_particles.size());
 }

public double[] execute(cluster C,int clusterid, data[] input,int switc) {
 Random generator = new Random();
 initializeSwarm(C,clusterid,input,switc);

 //evolutionaryStateEstimation();

 int t = 0;
 double w;

 while (t < MAX_ITERATION) {
 // calculate corresponding f(i,t) corresponding to location x(i,t)
 swarm1.calculateAllFitness();


 // update pBest
 if (t == 0) {
 for (int i = 0; i < SWARM_SIZE; i++) {
 swarm1.pBest[i] = swarm1.fitnessList[i];
 //System.out.println(swarm1.pBestLoc.length);
 swarm1.pBestLoc[i]=swarm1.get(i).getLocation();
 }
 } else {
 for (int i = 0; i < SWARM_SIZE; i++) 
 {
     
    if (swarm1.fitnessList[i] < swarm1.pBest[i])
    {
        swarm1.pBest[i] = swarm1.fitnessList[i];
        swarm1.pBestLoc[i]= swarm.get(i).getLocation();
    }
 }
 }

 int bestIndex = swarm1.getBestParticle();
 // update gBest
 if (t == 0 || swarm1.fitnessList[bestIndex] < swarm1.gBest) {
 swarm1.gBest = swarm1.fitnessList[bestIndex];
 swarm1.gBestLoc = swarm1.get(bestIndex).getLocation();
 }

 w = W_UP - (((double) t) / MAX_ITERATION) * (W_UP - W_LO);

 for (int i = 0; i < SWARM_SIZE; i++) {
 // update particle Velocity
    Particle p= swarm1.get(i);
 double r1 = generator.nextDouble();
 double r2 = generator.nextDouble();
 Position lx = swarm1.get(i).getLocation();
 Velocity vx = swarm1.get(i).getVelocity();
 Position[] pBestX = swarm1.pBestLoc;
 Position gBestXLoc = swarm1.gBestLoc;

 Velocity newVelX = new Velocity(DIMENSION);
 Position newPosX = new Position(DIMENSION);
 double[] newvel = new double[DIMENSION];
 for(int feature=0;feature<DIMENSION;feature++)
 {
    newvel[feature]=w * (lx.get_feature(feature)) + (r1 * C1) * (swarm1.pBestLoc[i].get_feature(feature) - lx.get_feature(feature)) + (r2 * C2) * (gBestXLoc.get_feature(feature) - lx.get_feature(feature));
 }
 newVelX.setX(newvel);
 p.setVelocity(newVelX);

 // update particle Location
 double newpos[]= new double[DIMENSION];
 for(int feature=0;feature<DIMENSION;feature++)
 {
       newpos[feature]  = lx.get_feature(feature) + newvel[feature];
 }
 newPosX.setX(newpos);
 p.setLocation(newPosX);
 
 swarm1.set(p, i);
 
 
 
 
 }

 t++;
 
 for(int k=0;k<5;k++)
                    {
                        //System.out.print("globalbestlocation-"+swarm1.gBestLoc.getX()[k]);
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
 
 }
 return swarm1.gBestLoc.getX();
 
 }


	

}
