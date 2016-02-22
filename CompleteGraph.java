import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Math;
import java.lang.Integer;

import java.io.PrintStream;
import java.util.Random;

/**
 * GenGraph.java
 *
 * Interface for a structure a graph of edges and vertices.
 *
 */

public class CompleteGraph implements Graph {

    /* Contains list of vertices */
    public ArrayList<NVertex> vertices;

    /* Mantains dimension of the graph */
    public int dimension;

    /**
     * Find weight (distance) of an edge between vertices A and B
     */
    public static double getWeight(NVertex va, NVertex vb) {
        // get dimensions
        int dimension = va.components.size();
        /* make sure dimensions match */
        if (dimension != vb.components.size()) {
            throw new IllegalArgumentException();
        }

        // find distance
        double eVector;
        double preSum = 0;
        do {
            dimension -= 1;
            eVector = va.components.get(dimension).intValue() -
                            vb.components.get(dimension).intValue();
            eVector = Math.pow(eVector, 2);
            preSum += eVector;
        } while (dimension > 0);
        double sum = Math.sqrt(preSum);

        return sum;
    }

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
     * Class method to create a CompleteGraph of n vertices and dim dimensions
     * @param n
     * @param dim
     * @return
     */
    public static CompleteGraph makeGraph(int n, int dim){
        List<NVertex> vlist = new ArrayList<NVertex>();
        Random vertexGenerator = new Random();

        //create coordinates for each vertex
        for (int i = 0; i < n; i++){
            List<Double> coords = new ArrayList<Double>();
            for (int j = 0; j < dim; j++){
                coords.add(vertexGenerator.nextDouble());
            }
            NVertex newVertex = new NVertex(coords);
            vlist.add(newVertex);
        }

        CompleteGraph graph = new CompleteGraph(vlist, dim);

        return graph;
    }

    /**
     * Prints a list of all the vertices in current graph
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
}
