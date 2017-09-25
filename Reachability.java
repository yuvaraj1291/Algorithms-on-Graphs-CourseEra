import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Reachability {
	private static int reach(ArrayList<Integer>[] adj, int x, int y) {
		//write your code here
		boolean[] visited=new boolean[adj.length];
		int start=x;
		ArrayList<Integer> adjList;
		List<Integer> stack=new ArrayList<Integer>();
		int adjVertex;
		boolean end=false;
		stack.add(new Integer(start));

		while(!stack.isEmpty()){
			if(!stack.isEmpty() && end){
				start=stack.get(stack.size()-1);
			}
			visited[start]=true;

			adjList=adj[start];
		
			if(!adjList.isEmpty()){

				for(int i=0;i<adjList.size();i++){
					adjVertex=adjList.get(i);
					if(adjVertex==y){
						return 1;
					}
					if(i==adjList.size()-1){
						if(stack.contains(new Integer(start))){
							stack.remove(new Integer(start));
						}
						end=true;
					}
					if(!visited[adjVertex]){
						stack.add(new Integer(adjVertex));
						start=adjVertex;
						end=false;
						break;
					}
				} 
			}else{
				if(stack.contains(new Integer(start))){
					stack.remove(new Integer(start));
				}
				end=false;
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
			adj[y - 1].add(x - 1);
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		//System.out.println("Completed getting values");
		System.out.println(reach(adj, x, y));

	}
}

