import java.util.*;
import java.io.*;

public class dijkstra {
	
	static final int INF = 999999999;

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File("dijkstra.in.txt"));
		PrintStream out = new PrintStream(new File("dijkstra.out.txt"));
		
		int V = in.nextInt(); // number of vertices
		int E = in.nextInt(); // number of edges
		//int source = in.nextInt() - 1; // source vertex, subtract 1 to account for array indexing
		int source = 0;
		// initialize adjacency matrix with INF
		int[][] adj = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				adj[i][j] = INF;
			}
		}
		
		// populate adjacency matrix with initial distances
		for (int i = 0; i < E; i++) {
			int vertex1 = in.nextInt() - 1;
			int vertex2 = in.nextInt() - 1;
			int dist = in.nextInt();
			adj[vertex1][vertex2] = Math.min(dist, adj[vertex1][vertex2]);
			adj[vertex2][vertex1] = adj[vertex1][vertex2];
		}
		
		// distance to itself is 0
		for (int i = 0; i < V; i++) {
			adj[i][i] = 0;
		}
		
		boolean[] visited = new boolean[V];
		visited[source] = true;
		
		// initialize distance matrix with values from adjacency matrix
		int[] distances = new int[V];
		for (int i = 0; i < V; i++) {
			distances[i] = adj[source][i];
		}

		// loop V-1 times
		for (int i = 0; i < V - 1; i++) {
			// find the unvisited vertex with minimum distance to visited nodes
			int index = 0, distance = INF;
			for (int j = 0; j < V; j++) {
				if (distances[j] < distance && !visited[j]) {
					distance = distances[j];
					index = j;
				}
			}
			visited[index] = true;
			System.out.println(index + " " + distance);
			// update distance matrix with better distances
			for (int j = 0; j < V; j++) {
				distances[j] = Math.min(distances[j], distances[index] + adj[index][j]);
			}
		}
		
		// print distances
		for (int i = 0; i < V; i++) {
			if (distances[i] == INF) System.out.println(-1);
			else System.out.println(distances[i]);
		}

	}

}
