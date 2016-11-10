import java.util.*;
import java.io.*;

public class pwrfail {
	
	static final int INF = 999999999;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("pwrfail.in.txt"));
		PrintStream out = new PrintStream(new File("pwrfail.out.txt"));
		
		int V = in.nextInt(); // number of vertices
		int E = in.nextInt(); // number of edges
		int source = 0; // source vertex, subtract 1 to account for array indexing
		
		// initialize adjacency matrix with INF
		double[][] adj = new double[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				adj[i][j] = INF;
			}
		}
		
		int[][] coordinates = new int[V][2];
		
		// read in coordinates
		for (int i = 0; i < V; i++) {
			coordinates[i][0] = in.nextInt();
			coordinates[i][1] = in.nextInt();
		}
		
		// populate adjacency matrix with initial distances
		for (int i = 0; i < E; i++) {
			int vertex1 = in.nextInt() - 1;
			int vertex2 = in.nextInt() - 1;
			double distance = Math.sqrt(Math.pow(coordinates[vertex1][0] - coordinates[vertex2][0], 2) + Math.pow(coordinates[vertex1][1] - coordinates[vertex2][1], 2));
			adj[vertex1][vertex2] = distance;
			adj[vertex2][vertex1] = distance;
		}
		
		// distance to itself is 0
		for (int i = 0; i < V; i++) {
			adj[i][i] = 0;
		}
		
		boolean[] visited = new boolean[V];
		visited[source] = true;
		
		// initialize distance matrix with values from adjacency matrix
		double[] distances = new double[V];
		for (int i = 0; i < V; i++) {
			distances[i] = adj[source][i];
		}

		// loop V-1 times
		for (int i = 0; i < V - 1; i++) {
			// find the unvisited vertex with minimum distance to visited nodes
			int index = 0;
			double distance = INF;
			for (int j = 0; j < V; j++) {
				if (distances[j] < distance && !visited[j]) {
					distance = distances[j];
					index = j;
				}
			}
			visited[index] = true;
			
			// update distance matrix with better distances
			for (int j = 0; j < V; j++) {
				distances[j] = Math.min(distances[j], distances[index] + adj[index][j]);
			}
		}
		
		// print distances
		if (distances[V-1] == INF) System.out.println(-1);
		else System.out.println(distances[V-1]);

	}

}
