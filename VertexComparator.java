import java.util.Comparator;

public class VertexComparator implements Comparator<NVertex> {

    @Override
    public int compare(NVertex v1, NVertex v2) {
        Double a = Double.valueOf(v1.getRelativeWeight());

        Double b = Double.valueOf(v2.getRelativeWeight());

        // sort in ascending order
        return a.compareTo(b);
    }

}