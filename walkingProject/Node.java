import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Node {
private int id;
private String name;
private double latitude;
private double longitude;
private Map<Integer, Double> adjacencyList;
public Node(int id, String name, double latitude, double longitude) {
this.id = id;
this.name = name;
this.latitude = latitude;
this.longitude = longitude;
adjacencyList = new HashMap<Integer, Double>();
}
public int getId() {
return id;
}
public String getName() {
return name;
}
public double getLatitude() {
return latitude;
}
public double getLongitude() {
return longitude;
}
public double distanceTo(Node otherNode) {
double xDiff = this.latitude - otherNode.latitude;
double yDiff = this.longitude - otherNode.longitude;
return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
}
public void addEdge(int nodeId, double distance) {
adjacencyList.put(nodeId, distance);
}
public List<Integer> getAdjacentNodes() {
List<Integer> adjacentNodes = new ArrayList<Integer>();
for (Integer nodeId : adjacencyList.keySet()) {
adjacentNodes.add(nodeId);
}
return adjacentNodes;
}
public double getDistanceTo(int nodeId) {
if (adjacencyList.containsKey(nodeId)) {
return adjacencyList.get(nodeId);
} else {
return Double.POSITIVE_INFINITY;
}
}
@Override
public String toString() {
return String.format("%s (%f, %f)", name, latitude, longitude);
}
}