import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes; // List of nodes in the graph
    private List<Edge> edges; // List of edges in the graph

    public Graph() {
        nodes = new ArrayList<>(); // Initialize the nodes list
        edges = new ArrayList<>(); // Initialize the edges list
    }

    public void addNode(Node node) {
        nodes.add(node); // Add a node to the nodes list
    }

    public void addEdge(int nodeId1, int nodeId2, double distance) {
        Node node1 = nodes.get(nodeId1);
        Node node2 = nodes.get(nodeId2);
        node1.addEdge(node2.getId(), distance);
        node2.addEdge(node1.getId(), distance);
    }
    

    public List<Node> getNodes() {
        return nodes; // Get the nodes list
    }

    public List<Edge> getEdges() {
        return edges; // Get the edges list
    }

    public Node getNodeByName(String name) {
        for (Node node : nodes) { // Iterate over the nodes list
            if (node.getName().equalsIgnoreCase(name)) { // If the node's name matches the given name (case-insensitive)
                return node; // Return the node
            }
        }
        return null; // If no node is found, return null
    }

    public Node getClosestNode(Node node) {
        Node closest = null; // Initialize the closest node to null
        double minDistance = Double.MAX_VALUE; // Initialize the minimum distance to the maximum possible value of a double
        for (Node otherNode : nodes) { // Iterate over the nodes list
            if (node.equals(otherNode)) { // If the current node is the same as the given node, skip it
                continue;
            }
            double distance = node.distanceTo(otherNode); // Calculate the distance between the current node and the given node
            if (distance < minDistance) { // If the calculated distance is less than the current minimum distance
                closest = otherNode; // Update the closest node to the current node
                minDistance = distance; // Update the minimum distance to the calculated distance
            }
        }
        return closest; // Return the closest node
    }

    public Node getSecondClosestNode(Node node) {
        Node closest = null; // Initialize the closest node to null
        Node secondClosest = null; // Initialize the second closest node to null
        double minDistance = Double.MAX_VALUE; // Initialize the minimum distance to the maximum possible value of a double
        double secondMinDistance = Double.MAX_VALUE; // Initialize the second minimum distance to the maximum possible value of a double
        for (Node otherNode : nodes) { // Iterate over the nodes list
            if (node.equals(otherNode)) { // If the current node is the same as the given node, skip it
                continue;
            }
            double distance = node.distanceTo(otherNode); // Calculate the distance between the current node and the given node
            if (distance < minDistance) { // If the calculated distance is less than the current minimum distance
                secondClosest = closest; // Update the second closest node to the previous closest node
                secondMinDistance = minDistance; // Update the second minimum distance to the previous minimum distance
                closest = otherNode; // Update the closest node to the current node
                minDistance = distance; // Update the minimum distance to the calculated distance
            } else if (distance < secondMinDistance) { // If the calculated distance is less than the second minimum distance but greater than or equal to the minimum distance
                secondClosest = otherNode; // Update the second closest node to the current node
                secondMinDistance = distance; // Update the second minimum distance to the calculated distance
            }
        }
        return secondClosest; // Return the second closest node
    }
    
       

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodes:\n");
        for (Node node : nodes) { // Iterate over the nodes list
            sb.append(String.format("%d: %s (%f, %f)\n", node.getId(), node.getName(), node.getLatitude(), node.getLongitude())); // Append the node's id, name, latitude, and longitude to the StringBuilder
        }
        sb.append("\n");
        for (Edge edge : edges) { // Iterate over the edges list
            sb.append(String.format("%s -- %f --> %s\n", edge.getSource().getName(), edge.getDistance(), edge.getDestination().getName())); // Append the source node's name, distance, and destination node's name to the StringBuilder
        }
        return sb.toString(); // Return the StringBuilder as a String
    }
}
