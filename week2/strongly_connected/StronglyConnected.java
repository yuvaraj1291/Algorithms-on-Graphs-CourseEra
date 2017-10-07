import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here

        Stack<Integer> exploredStack=new Stack();
        Stack<Integer> stack=new Stack();
        boolean[] visited=new boolean[adj.length];

        //Running DFS in the Graph
        for(int i=0;i<adj.length;i++){
            if(!visited[i]){
                dfs(adj,exploredStack,stack,i,visited);
            }
        }
        ArrayList<Integer>[] revAdj = (ArrayList<Integer>[])new ArrayList[adj.length];
        //reversing the edges
         ArrayList<Integer> adjList=null;
         ArrayList<Integer> revAdjList=null;
        for(int i=0;i<adj.length;i++){
            adjList=adj[i];
            for(Integer edge:adjList){
                revAdjList=revAdj[(int)edge];
                if(revAdjList==null){
                    revAdjList=new ArrayList();
                    revAdjList.add((int)i);
                }else{
                    revAdjList.add((int)i);
                }
                revAdj[(int)edge]=revAdjList;
            }
        }
    
        int v=0;
        visited=new boolean[adj.length];
        boolean popFlag=true;
        int count=0;
        while(!exploredStack.isEmpty()){
            if(popFlag){
                v=exploredStack.pop();
            }
            if(popFlag && !visited[v]){
                    count++;
                    //System.out.println();
                }
            popFlag=true;
            if(!visited[v]){
                //System.out.print((v+1)+"\t");
                visited[v]=true;
                revAdjList=revAdj[v];
                while(revAdjList!=null && !revAdjList.isEmpty()){
                    v=revAdjList.get(0);
                    revAdjList.remove(0);
                    if(!visited[v]){
                        popFlag=false;
                        break;
                    }
                }
            }
        }
        
        return count;
    }

    public static void dfs(ArrayList<Integer>[] adj,Stack<Integer> exploredStack,Stack<Integer> stack,int start,boolean[] visited){
        
        ArrayList<Integer> adjList=null;
        boolean exploredFlag=true;
        int temp;
        boolean stackEmpty=false;
        boolean isEmpty=false;
        while(!isEmpty){
             exploredFlag=true;
            if(visited[start]){
                if((int)stack.peek()!=start){
                    exploredStack.add(start);
                }
                start=stack.pop();
            }else{
                visited[start]=true;
                adjList=adj[start];
                stack.add(start);
                for(Integer next:adjList){
                    if(!visited[next]){
                        start=next;
                        exploredFlag=false;
                        break;
                    }
                }
            }
            isEmpty=stack.isEmpty();
        }
        exploredStack.add(start);
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

