/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;

/**
 *
 * @author saini
 */
public class fitnessfunction {
    
    double[] weight;
    fitnessfunction()
    {
        
    }
    
    
    public void set(double[] x)
    {
        weight=x;
        
    }
    
    
         public  double extension_dist_cluster(cluster C,int clusterid, data[] input,int switc)
                          
      {
          double[] center = C.centre;
          data[] cluster_entries =extract_cluster_items(input,C,clusterid);
          double ex_dist=0;
          int feature_dimension;
          feature_dimension=cluster_entries[0].feature.length;
          int j=0;
          for(int i=0;i<cluster_entries.length;i++)
          {
              for(j=0;j<feature_dimension;j++)
              {
                  if(switc==0)
                  {
                      
                      center[j]=(C.uweight[j]+weight[j])/2;
                      if(Math.abs(weight[j]-C.uweight[j]) < 0.001){ex_dist = 1000;}
                      else { ex_dist=ex_dist+(Math.abs(cluster_entries[i].feature[j]-center[j])+((weight[j]-C.uweight[j])/2))/Math.abs(weight[j]-C.uweight[j]) +1;}
                  }
                  
                  if(switc==1)
                  {
                      center[j]=(weight[j]+C.lweight[j])/2;
                      if(Math.abs(C.lweight[j]-weight[j])<0.001){ex_dist=1000;}
                      else{ex_dist=ex_dist+(Math.abs(cluster_entries[i].feature[j]-center[j])+((C.lweight[j]-weight[j])/2))/Math.abs(C.lweight[j]-weight[j]) +1;}
                      
                  }
                      
              }
          }
          return ex_dist;
      }
         public  double euclidean_dist(cluster C,int clusterid, data[] input,int switc)
                          
      {
          double[] center = C.centre;
          data[] cluster_entries =extract_cluster_items(input,C,clusterid);
          //System.out.println(C.pattern);
          double ex_dist=0;
          int feature_dimension;
          feature_dimension=cluster_entries[0].feature.length;
          int j=0;
          for(int i=0;i<cluster_entries.length;i++)
          {
              for(j=0;j<feature_dimension;j++)
              {
                  if(switc==0)
                  {
                      
                      center[j]=(C.uweight[j]+weight[j])/2;
                     
                        ex_dist=ex_dist+(Math.abs(cluster_entries[i].feature[j]-center[j]));
                  }
                  
                  if(switc==1)
                  {
                      center[j]=(weight[j]+C.lweight[j])/2;
                      ex_dist=ex_dist+(Math.abs(cluster_entries[i].feature[j]-center[j]));
                  }
                      
              }
          }
          return ex_dist;
      }
         
         public data[] extract_cluster_items(data[] input, cluster C,int clusterid)
         {
             data[] cluster_items = new data[C.pattern];
             int k=0;
             for(int i=0;i<input.length;i++)
             {
                 if(input[i].clusterid==clusterid)
                 {
                     cluster_items[k]=input[i];
                     k++;
                 }
             }
             return cluster_items;
         }
}



      
    

