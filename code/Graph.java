import java.util.*;
public class Graph {
	public static int oo = (int) 1e9; // Infinity (∞) placeholder
	public static void main(String[] args) {
		node[] graph = new node[5];
		for (int i = 0; i < graph.length; i++) graph[i] = new node(i);
		/*		node[] testParent1 = {graph[0]};
				node[] testParent2 = {graph[1]};
				graph[0] = new node(null, 0);
				graph[1] = new node(testParent1, 1);
				graph[2] = new node(testParent2, 2);
				graph[3] = new node(testParent1, 3);
				graph[4] = new node(testParent1, 4);*/
 		graph[0].adj.add(graph[1]); graph[1].adj.add(graph[2]);
		graph[0].adj.add(graph[3]); graph[0].adj.add(graph[4]);
		dfs(graph[0]); // 3 4 2 1 0 
		System.out.println();
		bfs(graph[0]); // 0 3 4 1 2
		System.out.println("\n" + dijkstras(graph[0], graph[2])); // 2
		/*		node[] disjointGraph = new node[7];			 // Begin test case for Kruskal's
				edge[] disjointSet = new edge[11];
				for(int i = 0;i < disjointGraph.length;i++)
					disjointGraph[i] = new node(null, i);
				disjointSet[0] = new edge(disjointGraph[0], disjointGraph[1], 7);
				disjointSet[1] = new edge(disjointGraph[0], disjointGraph[3], 5);
				disjointSet[2] = new edge(disjointGraph[1], disjointGraph[2], 8);
				disjointSet[3] = new edge(disjointGraph[1], disjointGraph[3], 9);
				disjointSet[4] = new edge(disjointGraph[1], disjointGraph[4], 7);
				disjointSet[5] = new edge(disjointGraph[2], disjointGraph[4], 5);
				disjointSet[6] = new edge(disjointGraph[3], disjointGraph[4], 15);
				disjointSet[7] = new edge(disjointGraph[3], disjointGraph[5], 6);
				disjointSet[8] = new edge(disjointGraph[4], disjointGraph[5], 8);
				disjointSet[9] = new edge(disjointGraph[4], disjointGraph[6], 11);
				disjointSet[10] = new edge(disjointGraph[5], disjointGraph[6], 9);
				System.out.println(kruskals(disjointGraph.length, disjointSet)); // 39*/

		System.out.println("Bellman-Ford");
		bellmanFord(graph, graph[0]);
		System.out.println("Running Floyd-Warshall");
		floydWarshall(graph);
		System.out.println("Running Topo Sort");
		List<node> topo = topoSort(graph);
		for (node n : topo) System.out.format("%d ", n.data); System.out.println(); }
	public static void dfs(node currNode) { dfs(currNode, new HashSet<node>()); }
	public static void dfs(node currNode, Set<node> visited) {
		visited.add(currNode);                               // Mark start node visited
		for (node adjNode : currNode.adj)                    
			if (!visited.contains(adjNode))
				dfs(adjNode, visited);                       // Recurse on all adjacent unvisited nodes
		System.out.print(currNode.data + " "); }             // Finally, process node
	public static void bfs(node startNode) {
		Queue<node> queue = new ArrayDeque<>();              // Also consider a ArrayList, LinkedList or PriorityQueue
		Set<node> visited = new HashSet<>();                 // Also consider node.visited or boolean[] visited
		queue.offer(startNode);
		while (!queue.isEmpty()) {
			node currNode = queue.poll();                    // Poll next node in queue
			System.out.print(currNode.data + " ");           // Process node
			for (node adjNode : currNode.adj)
				if (!visited.contains(adjNode)) {
					visited.add(adjNode);
					queue.offer(adjNode); } } }
	public static int dijkstras(node source, node dest) {   // O(|E| + |V| log |V|) Dist from source node to dest node (no negative edges)
		Set<node> visited = new HashSet<>();
		PriorityQueue<edge> pq = new PriorityQueue<>();     // PriorityQueue improves runtime
		pq.offer(new edge(source, 0));
		while (!pq.isEmpty()) {
			edge e = pq.poll();                             // Poll for next edge of smallest cost
			if (visited.contains(e.target)) continue;       // Skip edges to already visited nodes
			visited.add(e.target);                          // Visit node
			if (e.target == dest) return e.weight;          // Stop if we found the target node
			for (node adjNode : e.target.adj)               // Enqueue edges to unvisited nodes, add to weight
				if (!visited.contains(adjNode))
					pq.add(new edge(adjNode, e.weight + e.target.adjW.getOrDefault(adjNode, 1))); }
		return oo; }                                        // Destination not found, return infinite cost (impossible)
	public static void bellmanFord(node[] graph, node source) { // O(|V|*|E|) Dist from source node to all nodes (no neg. cycles)
		Map<node, Integer> dist = new HashMap<node, Integer>(); // Distance from source node to given node
		Map<node, node> pred = new HashMap<node, node>();   // Predecessor nodes in the path to some destination node
		for (node n : graph) dist.put(n, oo); dist.put(source, 0); // Initialize distances to infinity, except source (0)
		for (int i = 0; i < graph.length - 1; i++) { boolean stop = true;
			for (node u : graph) for (node v : u.adj) {     // Iterate over all edges
					int w = u.adjW.getOrDefault(v, 1);
					if (dist.get(u) + w < dist.get(v)) { stop = false;
						dist.put(v, dist.get(u) + w);
						pred.put(v, u); } }
			if (stop) break; } }                            // Optimisation: Stop early if no change was made
	public static void floydWarshall(node[] graph) {        // O(|V|^3) (SLOW!) Dist from all nodes to all nodes
		Map<node, Map<node, Integer>> dist = new HashMap<node, Map<node, Integer>>();
		Map<node, Map<node, node>> next = new HashMap<node, Map<node, node>>();
		for (node n : graph) {
			Map<node, Integer> map = new HashMap<node, Integer>();
			dist.put(n, map);
			for (node n2 : graph) map.put(n2, oo);          // Initialize distances to infinity
			next.put(n, new HashMap<node, node>()); }       // Initialize next pointers to null
		for (node n : graph)                                // Initialize edges
			for (node adjNode : n.adj) {
				dist.get(n).put(adjNode, n.adjW.getOrDefault(adjNode, 1));
				next.get(n).put(adjNode, adjNode); }
		for (node k : graph)                                // Floyd-warshall DP - O(n^3)
			for (node i : graph)
				for (node j : graph) {
					int sum = dist.get(i).get(k) + dist.get(k).get(j);
					if (sum < dist.get(i).get(j)) {         // Update if shorter path found
						dist.get(i).put(j, sum);
						next.get(i).put(j, next.get(i).get(k)); } } }
	public static List<node> topoSort(node[] graph) {       // Node ordering that does not violate edge directions
		Map<node, Integer> numIncEdges = new HashMap<node, Integer>();
		for (node n : graph) {                                // Count incoming edges for each node
			numIncEdges.put(n, numIncEdges.getOrDefault(n, 0));
			for (node adj : n.adj)
				numIncEdges.put(adj, numIncEdges.getOrDefault(adj, 0) + 1); }
		Queue<node> queue = new ArrayDeque<node>();         // Also consider PriorityQueue
		for (node n : numIncEdges.keySet())                 // Initialize with nodes w/ 0 incoming edges
			if (numIncEdges.get(n) == 0) queue.offer(n);
		List<node> topoSort = new ArrayList<node>();
		while (!queue.isEmpty()) {                          // Process nodes until we have none left
			node n = queue.poll();                          // Choose next node with 0 incoming edges
			topoSort.add(n);                                // Process this node (add to topo sort)
			for (node adj : n.adj) {                        // Remove edges - decrement incoming edge count
				numIncEdges.put(adj, numIncEdges.get(adj) - 1);
				if (numIncEdges.get(adj) == 0) queue.offer(adj); } } // No more edges - enqueue
		return topoSort.size() == graph.length ? topoSort : null; } // If all nodes processed, we have valid toposort
	public static int kruskals(int size, edge[] graph) {
		int totalWeight = 0;
		int edges = 0;
		ArrayList<edge> mst = new ArrayList<>();
		Arrays.sort(graph);                                 // Sort the edge weights in increasing order.
		for(int i = 0;i < size;i++) {                       // Check every edge to see if they are selected.
			if(!mst.contains(graph[i])) {                   // If the given edge is not selected,
			mst.add(graph[i]); edges++;                     // add it to the mst,
			totalWeight += graph[i].weight;                 // and tally their weight.
			if(edges == (graph.length - 1)) return -1; } }  // If we ever breach our size limit, we no longer have an MST. Signify with a -1 "weight".
		return totalWeight; }                               // Report cost of all selected edge weights.
}
class node {
	public int data;
	public Set<node> adj;                                   // Set of adjacent nodes
	public Map<node, Integer> adjW;                         // Map: adjacent node -> weight of edge to that node
	public node(int _data) {
		data = _data; adj = new HashSet<>();
		adjW = new HashMap<>(); } }
class edge implements Comparable<edge> {
	public node target;
	public int weight;
	public edge(node _target, int _weight) {
		target = _target; weight = _weight; }
	public int compareTo(edge o) {                          // Smaller weights first
		return weight - o.weight; } }                       // Also consider Integer.compare