
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author boreas
 */
public class BinHeap<T extends NVertex> implements Heap<T>{
    
    private class HeapObj {
        public T myVertex;
        public double myVal;
        public HeapObj(T v, double val){
            this.myVertex = v;
            this.myVal = val;
        }
    }
    
    /**
     * list that contains heap objects
     */
    private List<HeapObj> heapList;
    
    /**
     * track heap size, starts at 1
     * for unfilled 1st element
     */
    private int heapSize;
    
    /**
     * constructor for binomial heap
     */
    public BinHeap(){
        this.heapList = new ArrayList<>();
        this.heapList.add(null);
        this.heapSize = 1;
    }
    
    /**
     * percolate an added node up from the bottom of the heap
     */
    private void percolateUp(int node){
        
        //keep track of which node we're at
        int i = node;
        
        while(i / 2 > 0){
            //if necessary, switch element with parent
            if(this.heapList.get(i).myVal < this.heapList.get(i/2).myVal){
                HeapObj temp = this.heapList.get(i);
                this.heapList.set(i,this.heapList.get(i/2));
                this.heapList.get(i/2).myVertex.position = i;
                this.heapList.set(i/2,temp);
                temp.myVertex.position = i/2;
                i /= 2;
            //we can stop percolating if we've found a larger element
            } else {
                break;
            }
        }
    }
    
    /**
     * percolate a node down from the top of the heap
     */
    private void percolateDown(int node){
        
        //keep track of which node we're at
        int i = node;
        
        //index of minimum child
        int minChild;
        
        while(2*i < this.heapSize){
            //find minimum child
            if((2*i)+1 < this.heapSize)
                minChild = this.heapList.get(2*i).myVal > this.heapList.get((2*i)+1).myVal ? 2*i+1 : 2*i;
            else
                minChild = 2*i;
            //if greater than child, switch
            if(this.heapList.get(i).myVal > this.heapList.get(minChild).myVal){
                HeapObj temp = this.heapList.get(i);
                this.heapList.set(i,this.heapList.get(minChild));
                this.heapList.get(minChild).myVertex.position = i;
                this.heapList.set(minChild,temp);
                temp.myVertex.position = minChild;
                i = minChild;
            //stop percolating down
            } else {
                break;
            }
        }
          
    }
    
    /**
     * retrieve smallest element, call percolate subroutine to rebalance tree
     * @return 
     */
    public T deletemin(){
        if(this.heapSize > 1){
            HeapObj min = this.heapList.get(1);
            this.heapSize--;
            if(this.heapSize > 1){
                this.heapList.set(1,this.heapList.get(this.heapSize));
                this.heapList.get(1).myVertex.position = 1;
                this.heapList.remove(this.heapSize);
                percolateDown(1);
            }
            return min.myVertex; 
        } else {
            return null;
        }
    }
    
    /**
     * insert new object into the heap
     * @param vertex
     * @param val 
     */
    public void insert(T vertex, double val){
        HeapObj insertObj = new HeapObj(vertex,val); 
        this.heapList.add(insertObj);
        vertex.position = this.heapSize;
        this.heapSize++;
        percolateUp(this.heapSize-1);
    }
    
    /**
     * @param vertex
     * @param new_val
     */
    public void decreaseKey(T vertex, double new_val){
        int pos = vertex.position;
        HeapObj obj = this.heapList.get(pos);
        if(obj.myVal > new_val){
            obj.myVal = new_val;
            percolateUp(pos);
        }
        
    }
    
    public int size() {
        return this.heapSize;
    }
}
