/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel706;
import java.io.*;
import java.lang.*;
import java.lang.Math;

import java.math.BigDecimal;
 

/**
 *
 * @author saini
 */

public class Eel706 {
    public double[][] norm(double a[][])
    {
        double[][] norm = new double[a.length][a[0].length];
        double[] E;
        double[] S;
        E=mean(a);
        S=stdev(a);
        int j;
        for(int i=0;i<a.length;i++)
        {
            for(j=0;j<a[0].length;j++)
            {
                norm[i][j]=(a[i][j]-E[j])/S[j];
            }
                       
        }
        return norm;
        
        
    }
    
    
   public double[] mean(double a[][])
   {
       double[] mean = new double[a.length];
       double sum;
       int j;
       for(j=0;j<a[0].length;j++)
       {
           sum=0;
        for(int i=0;i<a.length;i++)
        {
            
              sum=sum+a[i][j];
        }
        mean[j]=sum/a.length;
                       
        }
       return mean;
   }
   
    public double[] stdev (double[][] a)
    {
        double[] mean=mean(a);
        double[] S=new double[a[0].length];
        double sum=0;
        for(int j=0;j<a[0].length;j++)
        {
            for(int i=0;i<a.length;i++)
            {
                sum=sum+(Math.pow(a[i][j]-mean[j],2));
            }
            S[j]=Math.sqrt(sum)/a.length;
        }
        return S;
    }
    
    
     public static class InvalidException extends IOException
    {
        public InvalidException(String m)
        {
            super(m);
        }
    }
    
    public static int min(double array[], int ecc)
    {
        double min=array[0];
        int minindex=0;
        for (int i=1;i<ecc;i++)
        {
            if(array[i]<min)
            {
                min=array[i];
                minindex=i;
            }
        }
        return minindex;
    }   
	public static double convert(String s)
	{
        String m="";
        String n="";
        int z= s.length();
        for(int i=0;i<z; i++)
        {
            if (s.charAt(i)=='e')
            {
                m=s.substring(0,i);
                n=s.substring(i+1,z);
            }
        }
        BigDecimal x= new BigDecimal(m);
        Float y= Float.parseFloat(n);
        BigDecimal ten=  new BigDecimal(10);
        BigDecimal b= new BigDecimal(1);
        for(int j=0; j<y;j++)
            b=b.multiply(ten);
        if(y<0)
        {
            for(float mg=y;mg<0;mg++)
                {
                    b=b.divide(ten);


            }
        }

        BigDecimal zf=x.multiply(b);
        
        double answer= zf.doubleValue();
       
		return answer;
    }
    
	
	public static void neural_learn(data[] sample,double DP,int param,int samples)
    {
        
        int ecc=1;      
        double parameter[]= new double[param];
		for(int r=0;r<=param-1;r++)
		{
			parameter[r]=DP;
		}
        cluster clusters[]=new cluster[50];
		for(int cl=0;cl<50;cl++)
		{
			clusters[cl]=new cluster(param);
        }
        for(int h=0;h<=param-1;h++)
        {
            clusters[0].centre[h]=sample[0].feature[h];
			clusters[0].uweight[h]=clusters[0].centre[h]+parameter[h];
            clusters[0].lweight[h]=clusters[0].centre[h]-parameter[h];
        }       
        clusters[0].pattern=1;
        sample[0].clusterid=0;
        for(int iter=0;iter<=5;iter++)    //assuming 1 iterations
        {
            System.out.println("ITERATION NUMBER"+iter);
        for(int i=1;i<sample.length;i++)   //Read next input pattern
        {
            double extension_distance[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			for(int j=1;j<=ecc;j++)         //for p=1,2,...pth cluster
            {
                for(int k=0;k<=param-1;k++)       //number of feature..for summation over j
                    extension_distance[j-1]+=((Math.abs(sample[i].feature[k]-clusters[j-1].centre[k])-(Math.abs(clusters[j-1].uweight[k]-clusters[j-1].lweight[k])/2))/Math.abs((clusters[j-1].uweight[k]-clusters[j-1].lweight[k])/2))+1; 
           
               // System.out.print("--p"+extension_distance[j-1]);
                        }
               //         System.out.println();
            
                        
            int index=min(extension_distance,ecc);
           // System.out.println("this is it--"+extension_distance[index]+"--");
            if(extension_distance[index]>param)         //remember to check this out...EDs>n or EDs>DP
            {
                //System.out.println("joker");
                ecc++;
			    clusters[ecc-1].pattern=1;
			    for(int h=0;h<=param-1;h++)
                {
                    clusters[ecc-1].centre[h]=sample[i].feature[h];
					clusters[ecc-1].uweight[h]=clusters[ecc-1].centre[h]+parameter[h];
                    clusters[ecc-1].lweight[h]=clusters[ecc-1].centre[h]-parameter[h];
                }   
                sample[i].clusterid=ecc-1;
			}	
			else
            {
                int prev_clusterid=sample[i].clusterid;
                if(sample[i].clusterid==-1 || sample[i].clusterid!=index)
		{
                     //System.out.println("entered");
                    clusters[index].pattern+=1;
                    sample[i].clusterid=index;
                  //  System.out.println();
                  /*  System.out.println("Clusters before PSO");
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].lweight[ite]+ " ");
                    }
                    System.out.println();
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].uweight[ite]+ " ");
                    }
                    System.out.println();*/

                    init pso= new init();
                    clusters[index].lweight=pso.execute(clusters[index], index,sample,0);
                    pso= new init();
                    clusters[index].uweight=pso.execute(clusters[index], index,sample,1);
                  /*                      System.out.println("Clusters after PSO ");

                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].lweight[ite]+ " ");
                    }
                    System.out.println();
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].uweight[ite]+ " ");
                    }*/
                    
                    for(int k=0;k<5;k++)
                    {
                        //System.out.print("-"+clusters[index].lweight[k]);
                        //System.out.println();
                        //System.out.print("-"+clusters[index].uweight[k]);
                        //System.out.println();
                        
                    }
		    for(int h=0;h<=param-1;h++)
                    {
                    //clusters[index].lweight[h]+=(sample[i].feature[h]-clusters[index].centre[h])/(clusters[index].pattern+1);
                    //clusters[index].uweight[h]+=(sample[i].feature[h]-clusters[index].centre[h])/(clusters[index].pattern+1);
                    
                        clusters[index].centre[h]=(clusters[index].uweight[h]+clusters[index].lweight[h])/2;
    
                    }   
                    sample[i].clusterid=prev_clusterid;
                }
                 if(sample[i].clusterid!=-1 && sample[i].clusterid!=index)
                {
                    clusters[sample[i].clusterid].pattern-=1;
                    sample[i].clusterid=index;
                    init pso= new init();
                  /*  System.out.println();
                    System.out.println("Clusters before PSO existing");
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].lweight[ite]+ " ");
                    }
                    System.out.println();
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].uweight[ite]+ " ");
                    }
                    System.out.println();*/

                    clusters[prev_clusterid].lweight=pso.execute(clusters[prev_clusterid], prev_clusterid,sample,0);
                    pso= new init();
                    clusters[prev_clusterid].uweight=pso.execute(clusters[prev_clusterid], prev_clusterid,sample,1);
                   /* System.out.println("Clusters after PSO existing");
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].lweight[ite]+ " ");
                    }
                    System.out.println();
                    for(int ite=0;ite<param;ite++)
                    {
                        System.out.print(clusters[index].uweight[ite]+ " ");
                    }
                    System.out.println();*/

                    for(int h=0;h<=param-1;h++)
                    {
                        //clusters[sample[i].clusterid].lweight[h]-=(sample[i].feature[h]-clusters[sample[i].clusterid].centre[h])/clusters[sample[i].clusterid].pattern;
                       // clusters[sample[i].clusterid].uweight[h]-=(sample[i].feature[h]-clusters[sample[i].clusterid].centre[h])/clusters[sample[i].clusterid].pattern;
                        clusters[prev_clusterid].centre[h]=(clusters[prev_clusterid].uweight[h]+clusters[prev_clusterid].lweight[h])/2;
                    }
					
                }
                sample[i].clusterid=index;
                }
			}
        }
        for(int y=0;y<ecc;y++)
        {
			System.out.println("Cluster " + y +" "+ clusters[y].pattern);
        }   
		for(int z=0;z<samples;z++)
        {
            System.out.println("Point "+ (z+1)+" "+sample[z].clusterid); 
        }
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
         BufferedReader charac=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader k=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader k1=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader k2=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\n Enter the distance parameter : ");
        double DP = Double.parseDouble(k.readLine());
		System.out.print("\nEnter the name of file : ");
        InputStreamReader input=new InputStreamReader(System.in);
		BufferedReader reader=new BufferedReader(input);
        String s2="";
        s2=reader.readLine();
        System.out.print("\n Enter the number of features : ");
		int param = 8;//Integer.parseInt(k1.readLine());
		System.out.print("\n Enter the number of samples in dataset : ");
		int samples = 4177;//Integer.parseInt(k2.readLine());
        FileReader f=new FileReader(s2);
        BufferedReader in=new BufferedReader(f);
		String str="";
        int line_number=0;
        data sample[]=new data[samples];
           for(int i=0;i<=samples-1;i++)
           {
               sample[i] = new data(param);
           }
		  while((str=in.readLine())!=null)
        {
            int counter=0;// number of commas
			int start_next=0;
            for(int i=0;i<str.length();i++)
            {
                if (str.charAt(i)==',')         
                {
                    counter++;
                    double number1=0;
					String test="";
                    for(int p=start_next;p<=i-1;p++)
                    {
                        test+=str.charAt(p);
						if(Character.isDigit((str.charAt(p))))
                        {
							number1= number1*10+((int)(str.charAt(p))-48); 
						}
						
					}
					//number1=convert(test);
                    sample[line_number].feature[counter-1]=(number1)/10;
                    start_next=i+1;
                }   
                
            }
            line_number++;  
            
        }
		neural_learn(sample, DP*10,param,samples);
	
		f.close();
	}
    }

