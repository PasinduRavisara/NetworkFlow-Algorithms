//Student Name: Pasindu Ravisara Sendanayake
//ID: 20221033/ w2052750

// Class representing an edge in the flow network
public class Edge {
    int from, to, capacity, flow;  // Variables to store source, destination, capacity and current flow
    Edge residual;                 // The residual edge (reverse direction)

    // Constructor to initialize an edge
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;              // Initially, the flow is zero
    }

    // Method to get the remaining capacity on this edge
    public int residualCapacity() {
        return capacity - flow;
    }

    // Method to augment flow along this edge and adjust residual flow
    public void augment(int bottleneck) {
        flow += bottleneck;         // Increase flow in forward direction
        residual.flow -= bottleneck; // Decrease flow in residual (backward) direction
    }
}
