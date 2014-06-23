/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;
import java.util.ArrayList;

/**
 *
 * @author saini
 */
public  class swarm {
    
   // public static ArrayList swarm_particles;
   public static double[] pBest;
    public static double fitnessList[];
    public static Position[] pBestLoc;
    public static double gBest;
    public static Position gBestLoc;
    public static Particle[] swarmparticles;
    public static int counter;
    
    swarm(int size,int dimension)
    {
       //swarm_particles = new ArrayList(size);
        swarmparticles = new Particle[size];
        pBest = new double[size];
        fitnessList = new double[size];
        pBestLoc =  new Position[size];       
        gBest=0;        
        counter=0;
    }

    public static void add(Particle p)
    {
       /* System.out.print("\n Adding at "+counter);
        for(int i = 0;i<counter;i++)
        {
            for(int k=0;k<5;k++)
                    {
                        System.out.print("AFTER ADDING "+get(i).getLocation().get_feature(k));
                    
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
  
        }*/
        swarmparticles[counter]=p;
        counter++;
       /* System.out.print("\n Added at "+counter);
        for(int i = 0;i<counter;i++)
        {
            for(int k=0;k<5;k++)
                    {
                        System.out.print("AFTER ADDING "+get(i).getLocation().get_feature(k));
                    
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
  
        }*/
    }
    
    public static Particle get(int i)
    {
        
        return swarmparticles[i];
    }
    public void set(Particle p,int i)
    {
        swarmparticles[i]=p;
    }
    public static void calculateAllFitness()
    {
        int i=10;
        //int i= swarm_particles.size();
        //System.out.println(i);
        for(int j=0;j<i;j++)
        {
             
            
            swarmparticles[j].calculateFitness();
            
            fitnessList[j]=swarmparticles[j].fitness;
            
           // System.out.println("fitness-"+fitnessList[j]);
            
        }//System.out.println();
    }
    public static int getBestParticle()
    {
        int best_index=0;
        double min=fitnessList[0];
        for(int i=1;i<fitnessList.length;i++)
        {
            if(fitnessList[i]<min)
            {
                min=fitnessList[i];
                best_index=i;
            }
        }
        return best_index;
    }
    
}
