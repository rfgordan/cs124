import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;
import java.lang.Integer;

import java.io.PrintStream;

/**
 * GenGraph.java
 *
 * Interface for a structure a graph of edges and vertices.
 *
 */

public class Graph implements GenGraph {

    /**
     * Instance variable containing list of vertices
     */
    public ArrayList<Vertex> vertices;

    /**
     * Find weight (distance) of an edge between vertices A and B
     */
    @Override
    public double getWeight(Vertex a, Vertex b) {
        // get dimensions
        int dimension = a.components.size();
        /* make sure dimensions match */
        if (dimension != b.components.size()) {
            throw new IllegalArgumentException();
        }

        // find distance
        double eVector;
        double preSum = 0;
        do {
            dimension -= 1;
            eVector = a.components.get(dimension).intValue() - b.components.get(dimension).intValue();
            eVector = Math.pow(eVector, 2);
            preSum += eVector;
        } while (dimension > 0);
        double sum = Math.sqrt(preSum);

        return sum;
    }

    /**
     * Construct a Graph from a list of vertices.
     */
    public Graph(List<Vertex> vertices) {
        this.vertices = new ArrayList<Vertex>(vertices);
    }
}
