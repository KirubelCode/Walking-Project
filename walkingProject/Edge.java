
// Prepared by: Kirubel Temesgen
// Purpose to generate nodes and edges

import java.util.List;
import java.util.ArrayList;
public class Edge {
// Fields
private Node source; // starting node of the edge
private Node destination; // ending node of the edge
private double distance; // distance between the two nodes in the edge
// Constructor
public Edge(Node source, Node destination, double distance) {
this.source = source;
this.destination = destination;
this.distance = distance;
}
public Edge(Node source, Node destination) {
this.source =source;
this.destination =destination;
}
// Getter methods
public Node getSource() {
return source;
}
public Node getDestination() {
return destination;
}
public double getDistance() {
return distance;
}
public String toString() {
return String.format("%s to %s (%f)", source, destination, distance);
}
}
