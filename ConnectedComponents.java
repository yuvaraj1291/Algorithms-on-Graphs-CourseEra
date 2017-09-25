import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectedComponents {
	private static int numberOfComponents(ArrayList<Integer>[] adj) {
		int result = 0;
		//write your code here

		boolean[] visited=new boolean[adj.length];
		int start=0;
		ArrayList<Integer> adjList;
		List<Integer> stack=new ArrayList<Integer>();
		int adjVertex;
		boolean end=false;
		boolean nextNode=true;
		outer:
			while(nextNode){
				result++;
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
							//System.out.println("Adjacent vertex ");
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

				for(int i=0;i<visited.length;i++){
					if(!visited[i]){
						start=i;
						nextNode=true;
						continue outer;
					}
				}	nextNode=false;		
			}
		return result;
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
		System.out.println(numberOfComponents(adj));
	}
}

