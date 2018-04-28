import java.util.*;
public class Graph {
	public static int oo = (int) 1e9; // Infinity (∞) placeholder
	public static void main(String[] args) {
		node[] graph = new node[5];
                node[] testParent1 = {graph[0]};
                node[] testParent2 = {graph[1]};
                graph[0] = new node(null, 0);
                graph[1] = new node(testParent1, 1);
                graph[2] = new node(testParent2, 2);
                graph[3] = new node(testParent1, 3);
                graph[4] = new node(testParent1, 4);
 		graph[0].adj.add(graph[1]); graph[1].adj.add(graph[2]);
		graph[0].adj.add(graph[3]); graph[0].adj.add(graph[4]);
		dfs(graph[0]); // 3 4 2 1 0
		System.out.println();
		bfs(graph[0]); // 0 3 4 1 2
		System.out.println("\n" + dijkstras(graph[0], graph[2])); // 2
                node[] disjointGraph = new node[7];             // Begin test case for Kruskal's
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
                System.out.println(kruskals(disjointGraph.length, disjointSet)); // 39
	}
	static void dfs(node currNode) {
		dfs(currNode, new HashSet<>());
	}
	static void dfs(node currNode, Set<node> visited) {
		visited.add(currNode); // Mark as visited
                currNode.adj.stream().filter((adjNode) -> (!visited.contains(adjNode))).forEachOrdered((adjNode) -> {
                    dfs(adjNode, visited);
            }); // Recurse on unvisited adjacent nodes
		System.out.print(currNode.data + " "); // Process node
	}
	static void bfs(node startNode) {
		Queue<node> queue = new ArrayDeque<>(); // Also consider a ArrayList, LinkedList or PriorityQueue
		Set<node> visited = new HashSet<>(); // Also consider node.visited or boolean[] visited
		queue.offer(startNode);
		while (!queue.isEmpty()) {
			node currNode = queue.poll(); // Poll next node in queue
			System.out.print(currNode.data + " "); // Process node
                        currNode.adj.stream().filter((adjNode) -> (!visited.contains(adjNode))).map((adjNode) -> {
                            visited.add(adjNode);
                        return adjNode;
                    }).forEachOrdered((adjNode) -> {
                        queue.offer(adjNode); // Enqueue adjacent node
                    }); // Offer unvisited adjacent nodes
		}
	}
	static int dijkstras(node source, node dest) {
		Set<node> visited = new HashSet<>();
		PriorityQueue<edge> pq = new PriorityQueue<>();
		pq.offer(new edge(null, source, 0));
		while (!pq.isEmpty()) {
			edge e = pq.poll();                        // Poll for next edge of smallest cost
			if (visited.contains(e.target)) continue;  // Skip edges to already visited nodes
			visited.add(e.target);                     // Visit node
			if (e.target == dest) return e.weight;     // Stop if we found the target node
                        e.target.adj.stream().filter((adjNode) -> (!visited.contains(adjNode))).forEachOrdered((adjNode) -> {
                            pq.add(new edge(e.target, adjNode, e.weight + e.target.adjW.getOrDefault(adjNode, 1)));
                    }); // Enqueue edges to unvisited nodes, accumulate weight
		}
		return oo; // We never found our node, return infinite cost (impossible)
	}
        static int kruskals(int size, edge[] graph) {
                int totalWeight = 0;
                int edges = 0;
                ArrayList<edge> mst = new ArrayList<>();
                Arrays.sort(graph);                                  // Sort the edge weights in increasing order.
                for(int i = 0;i < size;i++) {                        // Check every edge to see if they are selected.
                    if(!mst.contains(graph[i])) {                    // If the given edge is not selected,
                    mst.add(graph[i]); edges++;                      // add it to the mst,
                    totalWeight += graph[i].weight;                  // and tally their weight.
                    if(edges == (graph.length - 1)) return -1; } }   // If we ever breach our size limit, we no longer have an MST. Signify with a -1 "weight".
                return totalWeight;                                  // Report cost of all selected edge weights.
        }
}
class node {
	public int data;
        public node[] parents;
	public Set<node> adj;
	public Map<node, Integer> adjW; // Map edge to node -> weight
	public node(node[] _parents, int _data) {
		data = _data; adj = new HashSet<>();
		adjW = new HashMap<>();
	}
}
class edge implements Comparable<edge> {
        public node source;
	public node target;
	public int weight;
	public edge(node _source, node _target, int _weight) {
		source = _source; target = _target; weight = _weight;
	}
        @Override
	public int compareTo(edge o) {
		return weight - o.weight; // Smaller weights first
	}
}