import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here

        int[] parity=new int[adj.length];
        Queue<Integer> queue=new LinkedList();

        queue.add(0);
        parity[0]=1;

        int u=0;
        int v=0;
        ArrayList<Integer> adjList=null;

        while(!queue.isEmpty()){
            u=queue.remove();
            adjList=adj[u];
            for(Integer temp:adjList){
                v=temp;       
                if(parity[v]==0){
                    queue.add(v);
                    parity[v]=(parity[u]==1)?-1:1;
                }else if(parity[u]==parity[v]){
                    return 0;
                }
            }
        }
        return 1;
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
        System.out.println(bipartite(adj));
    }
}

