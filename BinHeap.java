
import java.util.ArrayList;
import java.util.List;

/**
 * BinHeap.java
 *
 * A binary heap implementation
 */

public class BinHeap<T extends NVertex> {
    // internal encapsulator for the vertex
    private class HeapObj {
        public T myVertex;
        public double myVal;
        public HeapObj(T v, double val){
            this.myVertex = v;
            this.myVal = val;
        }
    }
    
    /* List that contains heap objects. */
    private List<HeapObj> heapList;
    
    /* Tracks heap size, starts at 1 for unfilled 1st element. */
    private int heapSize;
    
    /**
     * Constructs an empty Binary Heap.
     */
    public BinHeap(){
        this.heapList = new ArrayList<>();
        this.heapList.add(null);
        this.heapSize = 1;
    }
    
    /**
     * Percolate an added node up from the bottom of the heap.
     */
    private void percolateUp(int node){
        
        //keep track of which node we're at
        int i = node;
        
        while(i / 2 > 0){
            //if necessary, switch element with parent
            if (this.heapList.get(i).myVal < this.heapList.get(i / 2).myVal){
                HeapObj temp = this.heapList.get(i);
                this.heapList.set(i, this.heapList.get(i / 2));
                this.heapList.get(i / 2).myVertex.setPosition(i);
                this.heapList.set(i / 2, temp);
                temp.myVertex.setPosition(i / 2);
                i /= 2;
            //we can stop percolating if we've found a larger element
            } else {
                break;
            }
        }
    }
    
    /**
     * Percolate a node down from the top of the heap.
     */
    private void percolateDown(int node){
        
        //keep track of which node we're at
        int i = node;
        
        //index of minimum child
        int minChild;
        
        while (2 * i < this.heapSize){
            //find minimum child
            if ((2 * i) + 1 < this.heapSize) {
                if (this.heapList.get(2 * i).myVal >
                        this.heapList.get((2 * i) + 1).myVal) {
                    minChild = 2 * i + 1;
                } else {
                    minChild = 2 * i;
                }
            } else {
                minChild = 2 * i;
            }
            //if greater than child, switch
            if (this.heapList.get(i).myVal > this.heapList.get(minChild).myVal){
                HeapObj temp = this.heapList.get(i);
                this.heapList.set(i, this.heapList.get(minChild));
                this.heapList.get(minChild).myVertex.setPosition(i);
                this.heapList.set(minChild, temp);
                temp.myVertex.setPosition(minChild);
                i = minChild;
            //stop percolating down
            } else {
                break;
            }
        }
          
    }
    
    /**
     * Retrieve smallest element, call percolate subroutine to rebalance tree.
     */
    public T deletemin(){
        if(this.heapSize > 1){
            HeapObj min = this.heapList.get(1);
            this.heapSize--;
            if(this.heapSize > 1){
                this.heapList.set(1,this.heapList.get(this.heapSize));
                this.heapList.get(1).myVertex.setPosition(1);
                this.heapList.remove(this.heapSize);
                percolateDown(1);
            }
            return min.myVertex; 
        } else {
            return null;
        }
    }
    
    /**
     * Insert new object into the heap.
     */
    public void insert(T vertex, double val){
        HeapObj insertObj = new HeapObj(vertex, val);
        this.heapList.add(insertObj);
        vertex.setPosition(this.heapSize);
        this.heapSize++;
        percolateUp(this.heapSize - 1);
    }
    
    /**
     * Change the value of a vertex in the heap.
     */
    public void decreaseKey(T vertex, double new_val){
        int pos = vertex.getPosition();
        HeapObj obj = this.heapList.get(pos);
        if(obj.myVal > new_val){
            obj.myVal = new_val;
            percolateUp(pos);
        }
        
    }

    /**
     * Size of the heap.
     */
    public int size() {
        return this.heapSize;
    }
}
