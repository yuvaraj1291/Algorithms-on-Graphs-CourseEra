import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        for(int i=0;i<used.length;i++){
           if(used[i]==0){
                dfs(adj,used,order,i); 
            }
        }
        Collections.reverse(order);
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
      //write your code here
        Stack<Integer> stack=new Stack<Integer>();
        stack.add(s);
        int temp=0;
        ArrayList<Integer> adjList=null;
        while(!stack.isEmpty()){
                adjList=adj[s];
                used[s]=1;
                if(!adjList.isEmpty()){
                   temp=adjList.get(0);
                   adjList.remove(0);
                   if(used[temp]!=1){
                        stack.add(temp);
                        s=temp;
                   }
                }else{
                    order.add(s);
                    stack.pop();
                    if(!stack.isEmpty()){
                        s=stack.peek();
                    }
                    
                }
        }   

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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

