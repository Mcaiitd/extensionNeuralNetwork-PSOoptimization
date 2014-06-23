/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;

/**
 *
 * @author saini
 */
public class Particle {
    
    cluster C;
    int clusterid;
    data[] input;
    int switc;
    
 private Position location;
 private Velocity velocity;
  double fitness;
  Particle(int dimension)
  {
      location =  new Position(dimension);
      velocity =  new Velocity(dimension);
      
  }

 public double getFitness() {
 calculateFitness();
 return fitness;
 }

 public void calculateFitness() {
 double x[] = this.location.getX();
  for(int k=0;k<5;k++)
                    {
                        //System.out.print(" "+x[k]);
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
 
 fitnessfunction fitnes=new fitnessfunction();
 fitnes.set(x);

 fitness=fitnes.extension_dist_cluster(C, clusterid, input, switc);
 
 }

 public Position getLocation() {
 return location;
 }

 public void setLocation(Position location) {
 this.location = location;
 }

 public Velocity getVelocity() {
 return velocity;
 }

 public void setVelocity(Velocity velocity) {
 this.velocity = velocity;
 }

    
}

