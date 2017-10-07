import java.util.*;

public class Dijkstra {

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        
        int n=adj.length;
        
        int[] prev=new int[n];
        int[] dist=new int[n];
        boolean[] visted=new boolean[n];

        for(int i=0;i<n;i++){
            dist[i]=Integer.MAX_VALUE;
        }

        dist[s]=0;
        prev[s]=s;
        ArrayList<Integer> adjList=null;
        int index;
        ArrayList<Integer> costList=null;
        int adjNode,adjNodeCost;
        while(true){
            index=findMinimum(visted,dist);

            if(index!=Integer.MAX_VALUE){
                visted[index]=true;
                adjList=adj[index];
                costList=cost[index];

                for(int i=0;i<adjList.size();i++){
                    adjNode=adjList.get(i);
                    adjNodeCost=costList.get(i);
                    if(dist[adjNode]>dist[index]+adjNodeCost){
                        dist[adjNode]=dist[index]+adjNodeCost;
                        prev[adjNode]=index;
                    }
                }

            }else{
                break;
            }
        
        }
       // System.out.println("Cost to traverse "+dist[t]);
        if(dist[t]==Integer.MAX_VALUE){
            return -1;
        }else{
            return dist[t];
        }
    }

    public static int findMinimum(boolean[] visted, int[] distance){
        int min=Integer.MAX_VALUE;
        int minIndex=Integer.MAX_VALUE;
        for(int i=0;i<visted.length;i++){
            if(!visted[i]){
                if(distance[i]<min){
                    min=distance[i];
                    minIndex=i;
                }
            }
        }
        return minIndex;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

