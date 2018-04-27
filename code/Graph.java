import java.util.*;
public class Graph {
	public static int oo = (int) 1e9; // Infinity (∞) placeholder
	public static void main(String[] args) {
		node[] graph = new node[5];
		for(int i = 0; i < graph.length; i++) graph[i] = new node(i);
		graph[0].adj.add(graph[1]); graph[1].adj.add(graph[2]);
		graph[0].adj.add(graph[3]); graph[0].adj.add(graph[4]);
		dfs(graph[0]); // 3 2 1 4 0
		System.out.println();
		bfs(graph[0]); // 0 3 1 4 2
		System.out.println("\n" + dijkstras(graph[0], graph[2])); // 2
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
		queue.offer(startNode);
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
	public static int dijkstras(node source, node dest) {
		Set<node> visited = new HashSet<node>();
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		pq.offer(new edge(source, 0));
		while (!pq.isEmpty()) {
			edge e = pq.poll();                       // Poll for next edge of smallest cost
			if (visited.contains(e.target)) continue; // Skip edges to already visited nodes
			visited.add(e.target);                    // Visit node
			if (e.target == dest) return e.weight; // Stop if we found the target node
			for (node adjNode : e.target.adj)         // Enqueue edges to unvisited nodes, accumulate weight
				if (!visited.contains(adjNode))
					pq.add(new edge(adjNode, e.weight + e.target.adjW.getOrDefault(adjNode, 1)));
		}
		return oo; // We never found our node, return infinite cost (impossible)
	}
}
class node {
	public int data;
	public Set<node> adj;
	public Map<node, Integer> adjW; // Map edge to node -> weight
	public node(int _data) {
		data = _data; adj = new HashSet<node>();
		adjW = new HashMap<node, Integer>();
	}
}
class edge implements Comparable<edge> {
	public node target;
	public int weight;
	public edge(node _target, int _weight) {
		target = _target; weight = _weight;
	}
	public int compareTo(edge o) {
		return weight - o.weight; // Smaller weights first
	}
}