/**
 * GenGraph.java
 *
 * Interface for a structure a graph of edges and vertices.
 *
 */

public interface GenGraph {

    /**
     * Find weight (distance) of an edge between vertices A and B
     */
    public double getWeight(Vertex a, Vertex b);

}
