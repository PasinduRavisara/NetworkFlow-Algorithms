//Student Name: Pasindu Ravisara Sendanayake
//ID: 20221033/ w2052750

// Import necessary utility classes
import java.util.*;

public class MaxFlowSolver {
    private final FlowNetwork network; // The flow network on which to run the max flow algorithm
    private int[] parent;               // Array to store the parent of each node in the BFS path

    // Constructor that takes a flow network
    public MaxFlowSolver(FlowNetwork network) {
        this.network = network;                   // Store the provided flow network
        this.parent = new int[network.n];          // Initialize parent array with size equal to number of nodes
    }

    // Function to compute maximum flow using the Edmonds-Karp algorithm
    public int edmondsKarp(int source, int sink) {
        int maxFlow = 0; // Initialize the overall maximum flow to 0

        // Repeat until there is no augmenting path from source to sink
        while (bfs(source, sink)) {
            int pathFlow = Integer.MAX_VALUE; // Initialize bottleneck flow along the path to a large number

            // Find the minimum residual capacity (bottleneck) along the path found by BFS
            for (int v = sink; v != source; v = parent[v]) { // Traverse backward from sink to source
                int u = parent[v];                          // u is parent of v
                Edge e = findEdge(u, v);                    // Find the edge from u to v
                if (e != null) {
                    pathFlow = Math.min(pathFlow, e.capacity - e.flow); // Update bottleneck (minimum available capacity)
                }
            }

            // Update the flow values along the path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];                         // Get parent node
                Edge e = findEdge(u, v);                    // Find forward edge
                if (e != null) {
                    e.flow += pathFlow;                    // Add flow in forward direction
                }
                Edge reverseEdge = findEdge(v, u);          // Find the corresponding reverse edge
                if (reverseEdge != null) {
                    reverseEdge.flow -= pathFlow;           // Subtract flow in reverse direction
                }
            }

            maxFlow += pathFlow; // Add path flow to total flow
            // Print augmentation step info
            System.out.println("Augmented path with bottleneck " + pathFlow + ", current max flow = " + maxFlow);
        }

        return maxFlow; // Return the total maximum flow
    }

    // Helper function to perform Breadth-First Search (BFS) to find an augmenting path
    private boolean bfs(int source, int sink) {
        Arrays.fill(parent, -1);             // Initialize all nodes as unvisited
        Queue<Integer> queue = new LinkedList<>(); // Create a queue for BFS
        queue.add(source);                   // Start BFS from the source node
        parent[source] = source;              // Mark the source's parent as itself

        while (!queue.isEmpty()) {            // While there are nodes to visit
            int u = queue.poll();             // Get the next node from queue
            for (Edge e : network.adj[u]) {    // Explore all outgoing edges from u
                if (parent[e.to] == -1 && e.capacity > e.flow) { // If destination node has not been visited and there is available capacity
                    parent[e.to] = u;         // Set parent of destination node
                    if (e.to == sink) {       // If we reached the sink node
                        return true;          // Path exists
                    }
                    queue.add(e.to);          // Otherwise, continue BFS
                }
            }
        }

        return false; // No augmenting path found
    }

    // Helper function to find an edge from 'from' node to 'to' node
    private Edge findEdge(int from, int to) {
        for (Edge e : network.adj[from]) {  // Loop over all edges from 'from' node
            if (e.to == to) {               // If edge goes to 'to' node
                return e;                   // Return the edge
            }
        }
        return null; // Edge not found
    }
}
