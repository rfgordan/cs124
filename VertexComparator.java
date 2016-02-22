import java.util.Comparator;

/**
 * VertexComparator.java
 *
 * Comparator class that enables comparisons between NVertex objects.
 * Used for iterators in algorithm.
 */

public class VertexComparator implements Comparator<NVertex> {

    @Override
    public int compare(NVertex v1, NVertex v2) {
        Double a = Double.valueOf(v1.getRelativeWeight());

        Double b = Double.valueOf(v2.getRelativeWeight());

        // sort in ascending order
        return a.compareTo(b);
    }

}