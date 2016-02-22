import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

/**
 *
 * @author boreas
 */
public class RandMst {

    /**
     * index for iterator
     */
    private int iterator;

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
        BinHeap<NVertex> heap = new BinHeap<NVertex>();
        s.setRelativeWeight(0.0);

        // add every element to the heap
        for (int i = 0, size = setV.size(); i < size; i++) {
            NVertex w = setV.get(i);
            w.setID(i);
            heap.insert(w, w.getRelativeWeight());
        }

        while (heap.size() > 0) {
            // get head of heap
            NVertex v = heap.deletemin();
            int vID = v.getID();
            setV.set(vID, null);
            finalV.add(v);
            // iterate through every vortex in disjoint set V - S
            for (int i = 0, size = setV.size(); i < size; i++) {
                NVertex w = setV.get(i);
                if (w == null) {
                    continue;
                }

                // check if new weight from mst to current vertex is smaller
                double newWeight = CompleteGraph.getWeight(v, w);
                if (newWeight < w.getRelativeWeight()) {
                    w.setRelativeWeight(newWeight);
                    w.setParent(v);
                    heap.decreaseKey(w, newWeight);
                }
            }
        }
    }
}
