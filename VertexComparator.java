import java.util.Comparator;

public class VertexComparator implements Comparator {

    @Override
    public int compare(NVertex v1, NVertex v2) {
        Vertex a = v1.getRelativeWeight();

        Vertex b = v2.getRelativeWeight();

        // sort in ascending order
        return a.compareTo(b);
    }

}