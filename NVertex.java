import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/** 
 * Vertex.java
 *
 * A n-th dimensional vertex.
 *
 */

public class NVertex{

    /* Immutable components of the vertex */
    public List<Double> components;

    /* Indicates current weight relative to source */
    private double relativeWeight;

    /* Points to the MST parent vertex */
    private NVertex parent;
    
    /* Position in data structure. Used for quick heap lookup */
    public int position;

    /* Unique id */
    private int id;

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
    
    public void setPosition(int newPosition){
        this.position = newPosition;
    }
    
    public int getPosition(){
        return this.position;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }
}
