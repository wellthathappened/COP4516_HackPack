import java.util.*;
public class Graph {
	public static void main(String[] args) {
		node[] graph = new node[5];
		for(int i = 0; i < graph.length; i++) graph[i] = new node(i);
		graph[0].adj.add(graph[1]); graph[1].adj.add(graph[2]);
		graph[0].adj.add(graph[3]); graph[0].adj.add(graph[4]);
		dfs(graph[0]); // 3 2 1 4 0
		System.out.println();
		bfs(graph[0]); // 0 3 1 4 2
	}
	
	public static void dfs(node currNode) {
		dfs(currNode, new HashSet<node>());
	}
	public static void dfs(node currNode, Set<node> visited) {
		visited.add(currNode); // Mark as visited
		for (node adjNode : currNode.adj) // Recurse on unvisited adjacent nodes
			if (!visited.contains(adjNode)) {
				dfs(adjNode, visited);
			}
		System.out.print(currNode.data + " "); // Process node
	}
	
	public static void bfs(node startNode) {
		Queue<node> queue = new ArrayDeque<>(); // Also consider a ArrayList, LinkedList or PriorityQueue
		Set<node> visited = new HashSet<>(); // Also consider node.visited or boolean[] visited
		queue.add(startNode);
		while (!queue.isEmpty()) {
			node currNode = queue.poll(); // Poll next node in queue
			System.out.print(currNode.data + " "); // Process node
			for(node adjNode : currNode.adj) // Offer unvisited adjacent nodes
				if (!visited.contains(adjNode)) {
					visited.add(adjNode);
					queue.offer(adjNode); // Enqueue adjacent node
				}
		}
	}
}
class node {
	public int data;
	public Set<node> adj;

	public node(int _data) {
		this.data = _data;
		this.adj = new HashSet<node>();
	}
}