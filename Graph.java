import java.util.ArrayList;
import java.lang.Math;

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
    @Override
    public double getWeight(Vertex a, Vertex b) {
        // get dimensions
        dimension = size(a.components);
        /* make sure dimensions match */
        if (dimension != size(b.components)) {
            throw new IllegalArgumentException();
        }

        // find distance
        double eVector;
        double preSum = 0;
        do {
            dimension -= 1;
            eVector = a.components[dimension] - b.components[dimension];
            eVector = pow(eVector, 2);
            preSum += eVector;
        } while(dimension > 0);
        double sum = sqrt(presum);

        return sum;
    }


}
