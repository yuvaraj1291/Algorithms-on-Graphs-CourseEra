import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Queue;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        Queue<Integer> queue=new LinkedList<Integer>();

        queue.add(s);

        int[] distance=new int[adj.length];

        for(int i=0;i<adj.length;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        distance[s]=0;
        int u=0;
        int v=0;
        ArrayList<Integer> adjList=null;
        while(!queue.isEmpty()){
            u=queue.remove();
            adjList=adj[u];
            for(Integer temp:adjList){
                v=temp;
                if(distance[v]==(int)Integer.MAX_VALUE){
                    queue.add(v);
                    distance[v]=1+distance[u];
                }
            }
        }
        if(distance[t]!=(int)Integer.MAX_VALUE){
            return (int)distance[t];
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

