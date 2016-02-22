import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author boreas
 */
public class RandMst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Generate random graph
        CompleteGraph g1;
        g1 = CompleteGraph.makeGraph(15, 2);
        g1.printGraph();

        prim(g1, 1);
    }

    /**
     * Prim's algorithm
     *
     * @param graph   - graph to be processed
     * @param initial - index referring to initial vertex in graph vertices
     */
    public static void prim(CompleteGraph graph, int initial) {
        // get vertices list reference
        ArrayList<NVertex> setV = graph.vertices;

        // get reference to starting vertex
        NVertex s = setV.get(initial);

        // set of all vertices on mst
        ArrayList<NVertex> finalV = new ArrayList<NVertex>();

        // new heap containing source, and initialization of source weight
        Comparator<NVertex> comparator = new VertexComparator();
        PriorityQueue<NVertex> heap =
                    new PriorityQueue<NVertex>(1, comparator);
        s.setRelativeWeight(0.0);

        // add every element to the heap
        Iterator<NVertex> setVIter = setV.iterator();
        while (setVIter.hasNext()) {
            NVertex w = setVIter.next();
            heap.add(w);
        }

        while (heap.size() > 0) {
            // get head of heap
            NVertex v = heap.poll();
            finalV.add(v);
            // iterate through every vortex in disjoint set V - S
            Iterator<NVertex> heapIter = heap.iterator();
            while (heapIter.hasNext()) {
                NVertex w = heapIter.next();

                // check if new weight from mst to current vertex is smaller
                double newWeight = CompleteGraph.getWeight(v, w);
                if (newWeight < w.getRelativeWeight()) {
                    heap.remove(w);
                    w.setRelativeWeight(newWeight);
                    w.setParent(v);
                    heap.add(w);
                }
            }
        }
    }
}
