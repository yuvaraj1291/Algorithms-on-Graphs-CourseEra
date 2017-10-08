import java.util.Scanner;
import java.util.*;
public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        class Edge implements Comparable<Edge>{
            double distance;
            int source;
            int dest;
            @Override
            public int compareTo(Edge e){
                double diff= this.distance - e.distance;
                if(diff>0.0){
                    return 1;
                }else if(diff<0.0){
                    return -1;
                }else{
                    return 0;
                }
            }

        }
        ArrayList<Edge> edgeList=new ArrayList<Edge>();
        Edge edge=null;
        double distance;
        for(int i=0;i<x.length;i++){
            for(int j=i+1;j<x.length;j++){
                distance=computeDistance(x[i],y[i],x[j],y[j]);
                edge=new Edge();
                edge.distance=distance;
                edge.source=i;
                edge.dest=j;
                edgeList.add(edge);
            }
        }
        Collections.sort(edgeList);

        List<HashSet<Integer>> setList=new LinkedList<HashSet<Integer>>();

        int count=x.length-1;
        int source,dest;
        int sourceIndex=0;
        int destIndex=0;    
        boolean sourceFlag;
        boolean destFlag;
        boolean sameSet;
        HashSet<Integer> set=null;
        double result=0.;
        int index=0;
        double min=99999999999.0;
        double weight;
        int edgeCount=0;
        for(int i=0;i<edgeList.size();i++){
            if(count==0){
                break;
            }
            sourceFlag=false;
            destFlag=false;
            source=edgeList.get(i).source;
            dest=edgeList.get(i).dest;
            sameSet=false;
            index=0;
            weight=edgeList.get(i).distance;
            for(HashSet<Integer> hashSet:setList){
                if(hashSet.contains(source) && hashSet.contains(dest)){
                    sameSet=true;
                    break;
                }
                if(hashSet.contains(source)){
                    sourceIndex=index;
                    sourceFlag=true;
                }
                if(hashSet.contains(dest)){
                    destIndex=index;
                    destFlag=true;
                }
                if(sourceFlag && destFlag){
                    break;
                }
                index++;
            }

            if(!sameSet){
                if(count<k){
                   // System.out.println("weights "+weight+" min "+min);
                    if(min>weight){
                     //   System.out.println("min val setting "+min);
                        min=weight;
                    }
                }
                if(sourceFlag && destFlag){
                    set=setList.get(sourceIndex);
                    set.addAll(setList.get(destIndex));
                    setList.remove(destIndex);
                }else if(sourceFlag){
                    set=setList.get(sourceIndex);
                    set.add(dest);
                }else if(destFlag){
                    set=setList.get(destIndex);
                    set.add(source);
                }else{
                    set=new HashSet<Integer>();
                    set.add(source);
                    set.add(dest);
                    setList.add(set);
                }
                count--;
                edgeCount++;
            }
        }
      /*  for(HashSet<Integer> hashSet:setList){
                System.out.println();
                for(Integer val:hashSet){
                    System.out.print(val+"\t");
                }
            }*/
            /*System.out.println("Edege count "+edgeCount);
             System.out.println("min val sending "+min);
             System.out.println();*/
        return min;
    }

    public static double computeDistance(int x1,int y1,int x2,int y2){

        double xdiff=Math.pow((x1-x2),2);
        double ydiff=Math.pow((y1-y2),2);
        double total=Math.sqrt(xdiff+ydiff);
        return total;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

