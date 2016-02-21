import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/** 
 * Vertex.java
 *
 * A n-th dimensional vertex.
 *
 */

public class NVertex implements Vertex {

    /* Immutable components of the vertex */
    public List<Double> components;

    /* Indicates current weight relative to source */
    private double relativeWeight;

    /**
     * Costructs a Vertex from a list of component values. Note that
     * the resulting list is immutable.
     */
    public NVertex(List<Double> components) {
        // List must contain a positive amount of numbers
        if (components == null) {
            throw new NullPointerException();
        } else if (components.size() < 1) {
            throw new IllegalArgumentException();
        }
        this.components = Collections.unmodifiableList(
            new ArrayList<Double>(components));
    }

    /**
     * Getter method for relative weight to source
     */
    public double getRelativeWeight() {
        return this.relativeWeight;
    }

    /**
     * Setter method for relative weight to source
     */
    public void setRelativeWeight(double weight) {
        this.relativeWeight = weight;
    }
}
