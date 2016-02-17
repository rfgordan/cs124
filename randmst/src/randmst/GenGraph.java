/**
 * GenGraph.java
 *
 * Interface for a structure a graph of edges and vertices.
 *
 */

public interface GenGraph {

    /**
     * Instance variable containing list of vertices
     */
    public ArrayList<Vertex> vertices;

    /**
     * Find weight (distance) of an edge between vertices A and B
     */
    public double getWeight(Vertex a, Vertex b);

}
