import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Comparator;
import java.lang.Integer;
import java.io.PrintStream;

/**
 * RandMst.java
 *
 * Authors: Carlos Mendizabal and Robert Gordan
 * Harvard College Class of 2018, Computer Science 124 - Spring 2016
 *
 * Algorithm that generates a Minimum Spanning Tree. The implementation
 * is based on Prim's algorithm. The program calculates the total weight of
 * the MST, and outputs it to the user.
 *
 * Makes use of:
 * --NVertex.java: a class that represents the graph's vertices
 * --CompleteGraph.java: a class that represents a complete, undirected graph
 * --BinHeap.java: an implementation of a Binary Heap, used to store the
 *   V - S disjoint set in Prim's algorithm, containing all vertices that
 *   have not been added to the MST.
 * --Test.java: a tester class.
 *
 * Usage:
 * java RandMst mode numpoints numtrials dimension
 *
 * where:
 * --mode: 0 for regular, 1 for testing on a simple arbitrary graph, 2 for
 *   testing of binary heap.
 * --numpoints: the number of vertices that will be added to the complete
 *   graph.
 * --numtrials: number of times that the algorithm will be run before
 *   calculating an average MST weight.
 * --dimension: dimension of the graph. Value must be positive.
 *
 * Usage and testing patterns:
 * --The program is proven to work successfully on at least 65536 numpoints.
 *   Runtime for a single execution of Prim's on 65536 numpoints has
 *   averaged 12 minutes.
 * --The included tester methods in Test.java were used for correctness
 *   testing, as well as debugging during development.
 *
 * Implementation notes:
 * --The implementation follows the general structure of Prim's algorithm.
 *   Various optimizations were made to improve performance and to make
 *   the coding process more practical:
 * --Use of Java: the authors of this assignment recognize that Java serves as
 *   a high-performance object-oriented language. Java was chosen because of
 *   its protection against memory errors, compared to other choices such as C,
 *   and for its object-oriented nature.
 * --Implicit edges: The code does not define an Edge object or data structure.
 *   Since a complete graph will have an edge between any v and w vertex
 *   pairing, then the presence of such edges is implicit. Instead, edge
 *   features, such as the weight, are represented by either 1) the relative
 *   positions of any two vertices, with their respective distance calculated
 *   using the Euclidean formula, or 2) a random weight-to-MST lazy assignment
 *   that will not have to be reassigned after the vertex is added to the heap
 *   (for the case with dimension zero). Vertices also include a parent field,
 *   indicating the other end of the edge that is defined by the weight
 *   assignment.
 * --Vertices are added to the heap all at once, and never after that. Since
 *   there is an implicit edge between every vertex pair, every vertex in the
 *   heap, though its weight and parent fields, represents its
 *   lightest-known-yet edge to any point in the MST. All iterations that
 *   add a new vertex v to the MST simply consider every edge (v, w), where
 *   w is all vertices in V - S disjoint set, and updates w's weight and parent
 *   if the (v, w) edge is lighter.
 */
public class RandMst {
    public static void main(String[] args) {

        // printing
        PrintStream ps = new PrintStream(System.out);

        // parsing inputs
        int testing = Integer.parseInt(args[0]);
        int numpoints = Integer.parseInt(args[1]);
        int numtrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);

        if (testing < 0 || numpoints < 0 || numtrials < 0 || dimension < 0) {
            throw new IllegalArgumentException();
        }

        double sum = 0;

        // run tests
        if (testing == 1) {
            CompleteGraph simpleGraph = Test.simpleTest();
            sum = prim(simpleGraph, 1);
            numtrials = 1;
            numpoints = 10;
            dimension = 2;
        } else if (testing == 2) {
            Test.heapTest();
        } else {
            for (int i = 0; i < numtrials; i++) {
                // Generate random graph
                CompleteGraph randomGraph;
                randomGraph = CompleteGraph.makeGraph(numpoints, dimension);
                double subSum = prim(randomGraph, 1);
                sum += subSum;
            }
        }

        double average = sum / numtrials;

        // print results
        ps.printf("%f %d %d %d\n", average, numpoints, numtrials, dimension);
    }

    /**
     * Prim's algorithm
     */
    public static double prim(CompleteGraph graph, int initial) {

        // get vertices list reference
        ArrayList<NVertex> setV = graph.vertices;

        // get reference to starting vertex
        NVertex s = setV.get(initial);

        // set of all vertices on mst
        ArrayList<NVertex> finalV = new ArrayList<NVertex>();

        // new heap containing source, and initialization of source weight
        Comparator<NVertex> comparator = new VertexComparator();
        BinHeap<NVertex> heap = new BinHeap<NVertex>();
        s.setRelativeWeight(0.0);

        // add every element to the heap
        for (int i = 0, size = setV.size(); i < size; i++) {
            NVertex w = setV.get(i);
            w.setID(i);
            heap.insert(w, w.getRelativeWeight());
        }

        while (heap.size() > 1) {
            // get head of heap
            NVertex v = heap.deletemin();
            try {
                int vID = v.getID();
                setV.set(vID, null);
            } catch (NullPointerException e) {
                // heap is empty
            }
            finalV.add(v);
            // iterate through every vortex in disjoint set V - S
            for (int i = 0, size = setV.size(); i < size; i++) {
                try {
                    NVertex w = setV.get(i);

                    // check if new weight from mst to current vertex is smaller
                    double newWeight = CompleteGraph.getWeight(v, w);
                    //ps.printf("New WEight: %f", newWeight);
                    if (newWeight < w.getRelativeWeight()) {
                        w.setRelativeWeight(newWeight);
                        w.setParent(v);
                        heap.decreaseKey(w, newWeight);
                        //ps.printf("x-axis:%f y-axis:%f\n", w.components.get(0), w.components.get(1));
                    }
                } catch (NullPointerException e) {
                    // vertex is not in V - S
                    continue;
                }
            }
        }

        double sum = 0;
        for (int i = 0, size = finalV.size(); i < size; i++) {
            sum += finalV.get(i).getRelativeWeight();
        }

        return sum;
    }
}
