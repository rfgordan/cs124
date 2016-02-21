import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 *
 * @author boreas
 */
public class RandMst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * Prim's algorithm
     * @param graph - graph to be processed
     * @param initial - index referring to initial vertex in graph vertices
     */
    public static void prim(CompleteGraph graph, int initial) {
        // get vertices list reference
        ArrayList<NVertex> setV = graph.vertices;

        // get reference to starting vertex
        NVertex s = setV.get(initial);

        // dist and prev arrays
        ArrayList<Double> weight = new ArrayList(setV.size());
        ArrayList<NVertex> prev = new ArrayList(setV.size());

        // set of all vertices on mst
        ArrayList<NVertex> finalV = new ArrayList();

        // heap containing all vertices, followed by initial source value
        PriorityQueue<NVertex> heap =
                    new PriorityQueue(setV, new VertexComparator());
        s.setRelativeWeight(0.0);
        heap.add(s);

        // initialize weight and prev arrays
        for (int i = 0, size = setV.size(); i < size; i++) {
            // setting weight to a value larger than the max edge weight
            weight.set(i, 2);
            prev.set(i, null);
        }
        weight.set(initial) = 0.0;

        while (heap.size() > 0) {
            // get head of heap
            NVertex v = new NVertex(heap.poll().components);
            finalV.add(v);
            // TODO: This can be made more efficient
            // iterate through every vortex in disjoint set V - S
            Iterator<NVertex> iter = heap.iterator();
            while (iter.hasNext()) {
                NVertex current = new NVertex(iter.next().components);
                // Not quite how this comparison works
                if (current != v) {
                    continue;
                }
                // temp variable missIndex
                if (weight.get(missIndex) > ComplegeGraph.getWeight(v, current)) {
                    weight.set(missIndex) = ComplegeGraph.getWeight(v, current);
                    prev.set(missIndex) = v;
                    v.setRelativeWeight(weight.get(missIndex));
                    heap.add(v);
                }
            }
        }
    }
}

