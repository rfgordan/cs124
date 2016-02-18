import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/** 
 * Vertex.java
 *
 * A n-th dimensional vertex.
 *
 */

public class Vertex implements GenVertex {

    /**
     * Immutable components of the vertex
     */
    public List<Integer> components;

    /**
     * Costructs a Vertex from a list of component values. Note that
     * the resulting list is immutable.
     */
    public Vertex(List<Integer> components) {
        // List must contain a positive amount of numbers
        if (components == null) {
            throw new NullPointerException();
        } else if (components.size() < 1) {
            throw new IllegalArgumentException();
        }
        this.components = Collections.unmodifiableList(
            new ArrayList<Integer>(components));
    }
}
