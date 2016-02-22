import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Comparator;
import java.lang.Integer;
import java.io.PrintStream;

/**
 *
 * @author boreas
 */
public class RandMst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // printing
        PrintStream ps = new PrintStream(System.out);

        // parsing inputs
        int numpoints = Integer.parseInt(args[1]);
        int numtrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);

        // input safety



        // run tests
        double sum = 0;
        for (int i = 0; i < numtrials; i++) {
            // Generate random graph
            CompleteGraph randomGraph;
            randomGraph = CompleteGraph.makeGraph(numpoints, dimension);
            double subSum = prim(randomGraph, 1);
            //ps.printf("Trial %d: %f\n", i, subSum);
            sum += subSum;
        }
        double average = sum / numtrials;

        // results
        ps.printf("%f %d %d %d\n", average, numpoints, numtrials, dimension);
    }

    /**
     * Prim's algorithm
     *
     * @param graph   - graph to be processed
     * @param initial - index referring to initial vertex in graph vertices
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
<<<<<<< HEAD
            //ps.printf("x-axis:%f y-axis:%f\n", finalV.get(i).components.get(0), finalV.get(i).components.get(1));
            sum += finalV.get(i).getRelativeWeight();
            //ps.printf("Weight element %d: %f\n", i, finalV.get(i).getRelativeWeight());
=======
            sum += finalV.get(i).getRelativeWeight();
>>>>>>> 0a95f6ab0c2b4785a3a5e98c63a5e65843a583b6
        }

        return sum;
    }
}
