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
`   `
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

        while (heap.size() > 0) {
            // get head of heap
            NVertex v = heap.poll();
            finalV.add(v);
            // TODO: could this be more efficient?
            // iterate through every vortex in disjoint set V - S
            Iterator<NVertex> iter = heap.iterator();
            while (iter.hasNext()) {
                NVertex current = iter.next().components;

                // check if new weight from mst to current vertex is smaller
                if (CompleteGraph.getWeight(v, current) < current.getRelativeWeight()) {
                    // TODO: update values
                }
            }
        }
    }
}
v := deletemin(h)
        S := S∪ {v}
        for (v,w) ∈ E and w ∈ V −S do
        if dist[w] > length(v,w)
        dist[w] := length(v,w), prev[w] := v, insert(w,dist[w],H)
        fi
        rof
        end while end
