/** 
 * Edge.java
 *
 * A structure containing two different vertices.
 *
 * Note that the vertex (A,B) is equal to the vertex (B,A)
 */

public class Edge implements GenEdge {

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
