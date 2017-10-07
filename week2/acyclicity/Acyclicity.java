import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Acyclicity {



    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
        
        Stack<Integer> st=new Stack<Integer>();

        boolean[] visited=new boolean[adj.length];

        int start;

        int out;

        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                start=i;
                out=dfs(visited,st,adj,start);
                if(out==1){
                    return 1;
                }
            }
        }

        return 0;
    }

    public static int dfs(boolean[] visited,Stack<Integer> st,ArrayList<Integer>[] adj,int start){
        
        ArrayList<Integer> adjList=null;

        while(!visited[start] || !st.isEmpty()){
            if(st.contains(new Integer(start))){
                return 1;
            }
            if(!visited[start]){
                 st.add(new Integer(start));
                 visited[start]=true;
            }else{
                 start=st.pop();
            }

            adjList=adj[start];
            
            if(!adjList.isEmpty()){
                start=adjList.get(0);
                adjList.remove(0);
            }else{
                if(!st.isEmpty()){
                    st.pop();
                }
            }
        }
        return 0;
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
        }
        System.out.println(acyclic(adj));
    }
}

