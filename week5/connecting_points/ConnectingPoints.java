import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        
        double result = 0.;
        //write your code here
        double[][] edges=new double[x.length][y.length];
        double distance;
        
        for(int i=0;i<x.length;i++){
            for(int j=i+1;j<x.length;j++){
                distance=computeDistance(x[i],y[i],x[j],y[j]);
                edges[i][j]=distance;
                edges[j][i]=distance;
            }
        }

        boolean[] visited=new boolean[x.length];
        int[] prev=new int[x.length];
        double[] priorQueue=new double[x.length];

        priorQueue[0]=0.;
        
        for(int i=1;i<x.length;i++){
            priorQueue[i]=Double.MAX_VALUE;
        }

        int current=0;
        double dist=0.;
        while(true){

            current=extractMin(priorQueue,visited);
            if(current==Integer.MAX_VALUE){
                break;
            }
            visited[current]=true;
            for(int i=0;i<x.length;i++){
                if(!visited[i]){
                   dist=edges[current][i];
                    if(dist<priorQueue[i]){
                        priorQueue[i]=dist;
                        prev[i]=current;
                    } 
                }
            }

        }
        for(int i=0;i<priorQueue.length;i++){
            result+=priorQueue[i];
        }

        return result;
    }

    public static int extractMin(double[] priorQueue,boolean[] visited){
        double min=Double.MAX_VALUE;
        int index=Integer.MAX_VALUE;
        for(int i=0;i<priorQueue.length;i++){
            if(!visited[i] && min>priorQueue[i]){
                min=priorQueue[i];
                index=i;
            }
        }
        return index;
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
        System.out.println(minimumDistance(x, y));
    }
}

