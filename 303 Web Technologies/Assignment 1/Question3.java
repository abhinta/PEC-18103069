import java.util.*; 
 
class Graph 
{ 
	int V, E;
	class Edge 
	{ 
		int src, dest, weight; 
		Edge() 
		{ 
			src = 0;
			dest = 0;
			weight = 0; 
		} 
	}; 

	Edge edge[]; 

	Graph(int V, int E) 
	{ 
		this.V = V; 
		this.E = E; 
		edge = new Edge[E]; 
		for (int i = 0; i < E; i++) 
			edge[i] = new Edge(); 
	} 
	
	void BellmanFord(Graph graph, int src) 
	{ 
		int V = graph.V, E = graph.E; 
		int dist[] = new int[V]; 

		for (int i = 0; i < V; i++) 
			dist[i] = Integer.MAX_VALUE; 
		dist[src] = 0; 

		for (int i = 1; i < V; i++) 
		{ 
			for (int j = 0; j < E; j++) 
			{ 
				int u = graph.edge[j].src; 
				int v = graph.edge[j].dest; 
				int weight = graph.edge[j].weight; 
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
				{
					dist[v]=dist[u]+weight;
				}
			} 
		} 

		for (int j = 0; j < E; j++)
		{ 
			int u = graph.edge[j].src; 
			int v = graph.edge[j].dest; 
			int weight = graph.edge[j].weight; 
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) 
			{ 
				System.out.println("Negative cycles Exist"); 
				return; 
			} 
		} 
		printArr(dist, V); 
	} 

	void printArr(int dist[], int V) 
	{ 
		System.out.println("Vertex Distance from Source"); 
		for (int i = 0; i < V; i++) 
			System.out.println(i + "\t\t" + dist[i]); 
	} 

	public static void main(String[] args) 
	{ 
		Scanner cin = new Scanner(System.in);
		System.out.println("No. of vertices:");
		int V = cin.nextInt();
		System.out.println("No. of edges:");
		int E = cin.nextInt();  

		Graph graph = new Graph(V, E); 
		System.out.println("Enter the source vertex, destination vertex and weight of each edge:");
		for(int i=0;i<E;i++)
		{
			graph.edge[i].src = cin.nextInt(); 
			graph.edge[i].dest = cin.nextInt(); 
			graph.edge[i].weight = cin.nextInt();
		}
		cin.close();
		graph.BellmanFord(graph, 0); 
	} 
} 
//answer for second part of question is YES.