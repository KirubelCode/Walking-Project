
// Prepared by: Kirubel Temesgen
// Purpose: Main executeable
// Refrence: https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;


// Menu class which contains the main method and menu options
public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph; // Graph object to hold imported graph data

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            // Print menu options
            System.out.println("1. Import graph from file");
            System.out.println("2. Search site by name");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter absolute filepath: ");
                    String filename = scanner.nextLine();
                    try {
                        // Try to read graph from file
                        graph = readGraphFromFile(filename);
                        System.out.println(graph);
                        System.out.println("Graph imported from file.");
                    } catch (FileNotFoundException e) {
                        // File not found or not formatted correctly
                        System.out.println("File not found or not formatted correctly.");
                    }
                    break;

                case 2:
                    if (graph == null) {
                        // Check if graph is not found and prompt user to import it first
                        System.out.println("Graph not found. Please import graph from file first.");
                        break;
                    }
                    System.out.print("Enter site name: ");
                    String siteName = scanner.nextLine();
                    Node siteNode = graph.getNodeByName(siteName);

                    if (siteNode == null) {
                        // Check if site is not found
                        System.out.println("Site not found.");
                        break;
                    }

                    System.out.println("\nClosest site:");
                    // Find the closest node to the site
                    Node closestNode = graph.getClosestNode(siteNode);
                    System.out.println(closestNode);
                    System.out.println("\nNeighbour sites:");
                    // Find the adjacent sites node to the site
                    
                        
                        System.out.println(siteNode.getAdjacentNodes());
                        
                    

                    // Create a 2D array of edges
                    int edgeSite0 = (int) Math.abs(siteNode.getLatitude() - closestNode.getLatitude());
                    int edgeSite1 = (int) Math.abs(siteNode.getLongitude() - closestNode.getLongitude());
                    Edge[][] edges = new Edge[edgeSite0][edgeSite1];

                    // Populate the array with edges
                    for (Edge edge : graph.getEdges()) {
                        int row = (int) Math.abs(siteNode.getLatitude() - closestNode.getLatitude());
                        int col = (int) Math.abs(siteNode.getLongitude() - closestNode.getLongitude());
                        edges[row][col] = edge;
                    }

                    for (int i = 0; i < edges.length; i++) {
                        for (int j = 0; j < edges[i].length; j++) {
                            System.out.print(edges[i][j] + " ");
                        }
                        System.out.println(); // Move to next line after printing all elements in the row
                    }

                    break;

                case 3:
                    // Exit the program
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static Graph readGraphFromFile(String filename) throws FileNotFoundException {
        Graph graph = new Graph();
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            double latitude = Double.parseDouble(parts[2]);
            double longitude = Double.parseDouble(parts[3]);
            Node node = new Node(id, name, latitude, longitude);
    
            if (parts.length > 4) { // check if there are edges
                String[] edgeParts = parts[4].split("\\|"); // split edges by pipe symbol
                for (String edgePart : edgeParts) {
                    String[] edgeData = edgePart.split(":"); // split edge data by colon symbol
                    int neighborId = Integer.parseInt(edgeData[0]);
                    double distance = Double.parseDouble(edgeData[1]);
                    node.addEdge(neighborId, distance); // add each edge to the node
                }
            }
    
            graph.addNode(node);
        }
    
        scanner.close();
    
        return graph;
    }
    
    public static void printEdges(Graph graph) {
        List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            System.out.println(edge.getSource() + " -> " + edge.getDestination() + " : " + edge.getDistance());
        }
    }
    
}
    
