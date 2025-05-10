//Student Name: Pasindu Ravisara Sendanayake
//ID: 20221033/ w2052750

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class responsible for reading the flow network from a file
public class FlowNetworkParser {

    // Method to parse the input file and create a FlowNetwork object
    public static FlowNetwork parseInput(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename)); // Open file
        int n = Integer.parseInt(br.readLine().trim()); // Read number of nodes
        FlowNetwork net = new FlowNetwork(n); // Create a flow network
        String line;
        while ((line = br.readLine()) != null) { // Read each line
            String[] parts = line.trim().split("\\s+"); // Split line by spaces
            if (parts.length == 3) {                    // Check if it's a valid edge line
                int from = Integer.parseInt(parts[0]);   // Source node
                int to = Integer.parseInt(parts[1]);     // Destination node
                int capacity = Integer.parseInt(parts[2]); // Capacity
                net.addEdge(from, to, capacity);         // Add edge to network
            }
        }
        br.close(); // Close file
        return net; // Return the created network
    }
}
