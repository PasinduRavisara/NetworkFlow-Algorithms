//Student Name: Pasindu Ravisara Sendanayake
//ID: 20221033/ w2052750

// Import necessary classes for file handling and user input
import java.io.File;         // For working with files
import java.io.IOException; // For handling file input/output exceptions
import java.util.Scanner;   // For reading user input from the console

// Main class where the program starts execution
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input

        while (true) { // Infinite loop to repeatedly prompt user until valid input is received
            System.out.print("Enter the name of the benchmark file (e.g., bridge_5.txt): ");
            String filename = scanner.nextLine().trim(); // Read the filename input and remove any extra spaces

            File file = new File("benchmarks/" + filename); // Create a File object pointing to the file inside the benchmarks folder

            if (!file.exists()) { // Check if the file actually exists
                // If it doesn't exist, show an error message and restart the loop
                System.out.println("Error: File " + filename + " does not exist in benchmarks folder. Please try again.\n");
                continue;  // Continue to next iteration of loop (ask for input again)
            }

            try {
                // Parse the benchmark file into a flow network structure
                FlowNetwork network = FlowNetworkParser.parseInput(file.getAbsolutePath());

                // Create a solver object that will calculate the max flow on the network
                MaxFlowSolver solver = new MaxFlowSolver(network);

                int source = 0;                // Define source node as 0 (always fixed per spec)
                int sink = network.n - 1;      // Define sink node as the last node (n - 1)

                // Print all forward edges and their capacities
                System.out.println("\nFlow values on edges:");
                for (int u = 0; u < network.n; u++) { // Loop over each node in the network
                    for (Edge e : network.adj[u]) {   // Loop over all outgoing edges from node u
                        if (e.capacity > 0) {         // Only show forward edges (those with capacity > 0)
                            System.out.println("Edge " + e.from + " -> " + e.to + " | Capacity: " + e.capacity);
                        }
                    }
                }

                // Run the max-flow algorithm (Edmonds-Karp) and store the result
                int maxFlow = solver.edmondsKarp(source, sink);

                // Print the result in a formatted output block
                System.out.println("\n==========================================");
                System.out.println("File: " + filename);              // Show which file was processed
                System.out.println("Maximum Flow: " + maxFlow);      // Show the computed max flow
                System.out.println("==========================================\n");

                break;  // Exit the loop after successful execution
            } catch (IOException e) { // Catch and handle file input/output exceptions
                // Show error if something went wrong while reading or parsing the file
                System.out.println("Error reading file " + filename + ": " + e.getMessage());
                System.out.println("Please try again.\n"); // Prompt the user again
            }
        }
    }
}
