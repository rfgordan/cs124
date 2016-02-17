/** 
 * GenEdge.java
 *
 * Interface for a structure containing two different vertices.
 *
 * Note that the vertex (A,B) is equal to the vertex (B,A)
 */

public interface GenEdge {

    /**
     * Gets first vertex of edge.
     */
    public double getVertexA();
    
    /**
     * Gets second vertex of edge.
     */
    public double getVertexB();
    
    /**
     * Calculates weight of edge.
     */
    public double getWeight();
    
}
