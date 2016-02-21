
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
public class BinHeap<T> implements Heap<T>{

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
    private void percolateUp(){
        
        //keep track of which node we're at
        int i = this.heapSize - 1;
        
        while(i / 2 > 0){
            //if necessary, switch element with parent
            if(this.heapList.get(i).myVal < this.heapList.get(i/2).myVal){
                HeapObj temp = this.heapList.get(i);
                this.heapList.set(i,this.heapList.get(i/2));
                this.heapList.set(i/2,temp);
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
    private void percolateDown(){
        
        //keep track of which node we're at
        int i = 1;
        
        //index of minimum child
        int minChild;
        
        while(i < this.heapSize){
            //find minimum child
            minChild = this.heapList.get(2*i).myVal > this.heapList.get(2*i+1).myVal ? 2*i+1 : 2*i;
            //if greater than child, switch
            if(this.heapList.get(i).myVal > this.heapList.get(minChild).myVal){
                HeapObj temp = this.heapList.get(i);
                this.heapList.set(i,this.heapList.get(minChild));
                this.heapList.set(minChild,temp);
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
                this.heapList.remove(this.heapSize);
                percolateDown();
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
        this.heapSize++;
        percolateUp();
    }
    
    
}
