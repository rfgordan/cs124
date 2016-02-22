import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/** 
 * NVertex.java
 *
 * A n-th dimensional vertex.
 */

public class NVertex{

    /* Immutable components of the vertex. This list is public. */
    public List<Double> components;

    /* Indicates current weight relative to source */
    private double relativeWeight;

    /* Points to the MST parent vertex */
    private NVertex parent;
    
    /* Position in data structure. Used for quick heap lookup */
    private int position;

    /* Unique id, used for relationship with heap */
    private int id;

    /**
     * Costructs a Vertex from a list of component values. Note that
     * the resulting component list is immutable.
     */
    public NVertex(List<Double> components) {
        // List must contain a positive amount of numbers
        if (components == null) {
            throw new NullPointerException();
        } /*else if (components.size() < 1) {
            throw new IllegalArgumentException();
        }*/
        this.components = Collections.unmodifiableList(
            new ArrayList<Double>(components));
        this.parent = null;
        // relative weight value over max of 1
        this.relativeWeight = 100.0;
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

    /**
     * Getter method for mst parent
     */
    public NVertex getParent() {
        return this.parent;
    }

    /**
     * Setter method for mst parent
     */
    public void setParent(NVertex parent) {
        this.parent = parent;
    }

    /**
     * Getter method for vertex position in heap implementation
     */
    public int getPosition(){
        return this.position;
    }

    /**
     * Setter method for vertex position in heap implementation
     */
    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    /**
     * Getter method for vertex ID
     */
    public int getID() {
        return this.id;
    }

    /**
     * Setter method for vertex ID
     */
    public void setID(int id){
        this.id = id;
    }
}
