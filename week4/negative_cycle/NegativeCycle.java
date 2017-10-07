import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        boolean relaxFlag=true;
        int[] distance=new int[adj.length];
        distance[0]=0;
        int source=0;
        int dest=0;
        int weight=0;
        for(int i=1;i<distance.length;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        ArrayList<Integer> adjList=null;
        ArrayList<Integer> costList=null;
        int node=0;
        int nodeCost=0;
        for(int k=0;k<adj.length;k++){
        if(!relaxFlag){
                return 0;
            }
        //relaxation
        relaxFlag=false;
        for(int i=0;i<adj.length;i++){
            adjList=adj[i];
            costList=cost[i];
            for(int j=0;j<adjList.size();j++){
                 dest=adjList.get(j);
                 nodeCost=costList.get(j);
                 if(distance[i]==Integer.MAX_VALUE){
                  weight=0;  
                }else{
                  weight=distance[i];
                }
                 if(distance[dest]>weight+nodeCost){
                    distance[dest]=weight+nodeCost;
                    relaxFlag=true;
                }
            }
        }
    }
        if(relaxFlag){
            return 1;
        }else{
            return 0;
        }
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

