import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;
import java.lang.Integer;

import java.io.PrintStream;
import java.util.Random;

/**
 * CompleteGraph.java
 *
 * Representation of a complete, undirected graph on n vertices.
 * Provides with various methods to operate on graphs and their components,
 * as well as a printing debugger method.
 */

public class CompleteGraph {

    /* Contains list of vertices. This instance variable is public. */
    public ArrayList<NVertex> vertices;

    /* Mantains dimension of the graph. This instance variable is public. */
    public int dimension;

    /**
     * Construct a Graph from a list of vertices.
     */
    public CompleteGraph(List<NVertex> vertices, int dimension) {
        // check that desired dimension matches sample element of array
        if (vertices.get(0).components.size() != dimension) {
            throw new IllegalArgumentException();
        }
        this.vertices = new ArrayList<NVertex>(vertices);
        this.dimension = dimension;
    }

    /**
     * Prints a list of all the vertices in current graph.
     */
    public void printGraph() {
        PrintStream ps = new PrintStream(System.out);

        // print every vertex on graph
        for (int i = 0, size = this.vertices.size(); i < size; i++) {
            // temprarily holds vertex components
            ArrayList<Double> comp =
                    new ArrayList<Double>(this.vertices.get(i).components);
            // actual printing
            ps.printf("Vertex %3d: (", i);
            for (int j = 0; j < this.dimension - 1; j++) {
                ps.printf("%f,", comp.get(j));
            }
            ps.printf("%f)\n", comp.get(this.dimension - 1));
        }
        ps.printf("All vertices printed\n");
    }

    /**
     * Class method that finds weight (distance) of an edge
     * between vertices A and B.
     */
    public static double getWeight(NVertex va, NVertex vb) {
        // get dimensions and ensure the vertices are compatible
        int dimension = va.components.size();
        if (dimension != vb.components.size()) {
            // vertices are of different number of components
            throw new IllegalArgumentException();
        }

        // scenario where edge weights are assigned uniformly at random
        if (dimension == 0) {
            Random r = new Random();
            return r.nextDouble();
        } else {
            // for all other scenarios, calculate actual Euclidean distance
            double eVector;
            double preSum = 0;
            do {
                dimension -= 1;
                eVector = va.components.get(dimension).doubleValue() -
                        vb.components.get(dimension).doubleValue();
                eVector = Math.pow(eVector, 2);
                preSum += eVector;
            } while (dimension > 0);
            double sum = Math.sqrt(preSum);

            return sum;
        }
    }

    /**
     * Class method to create a uniformly random CompleteGraph
     * of n vertices and dim dimensions
     */
    public static CompleteGraph makeGraph(int n, int dim) {
        List<NVertex> vlist = new ArrayList<NVertex>();
        Random vertexGenerator = new Random();

        // assign 1-dimension to the random edge-weight scenario
        dim = (dim == 1) ? 0 : dim;

        //create coordinates for each vertex
        for (int i = 0; i < n; i++) {
            List<Double> coords = new ArrayList<Double>();
            for (int j = 0; j < dim; j++) {
                coords.add(vertexGenerator.nextDouble());
            }
            NVertex newVertex = new NVertex(coords);
            vlist.add(newVertex);
        }

        CompleteGraph graph = new CompleteGraph(vlist, dim);

        return graph;
    }
}