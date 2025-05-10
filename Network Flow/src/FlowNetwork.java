//Student Name: Pasindu Ravisara Sendanayake
//ID: 20221033/ w2052750

import java.util.ArrayList;
import java.util.List;

// Class representing the entire flow network
public class FlowNetwork {
    List<Edge>[] adj;  // Adjacency list to store edges for each node
    int n;             // Number of nodes

    // Constructor to initialize the flow network
    @SuppressWarnings("unchecked")
    public FlowNetwork(int n) {
        this.n = n;
        adj = new List[n];          // Create adjacency list array
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>(); // Initialize list for each node
        }
    }

    // Method to add a directed edge with a residual edge
    public void addEdge(int from, int to, int capacity) {
        Edge e1 = new Edge(from, to, capacity); // Create original edge
        Edge e2 = new Edge(to, from, 0);         // Create residual edge with 0 capacity
        e1.residual = e2;                       // Link them as residuals of each other
        e2.residual = e1;
        adj[from].add(e1);                      // Add forward edge
        adj[to].add(e2);                        // Add residual edge
    }
}
