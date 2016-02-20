import java.util.ArrayList;
import java.lang.Math;
import java.lang.Integer;
import java.io.PrintStream;

/**
 * Test.java
 *
 * Tester class
 */

public class Test {

    public static void main(String[] args) {
        PrintStream ps = new PrintStream(System.out);

        /*
         * Graph distance() method test
         */
        ps.printf("Testing distance\n");
        ArrayList<Double> listA;
        listA = new ArrayList<Double>(2);
        listA.add(0.0);
        listA.add(2.0);
        ArrayList<Double> listB;
        listB = new ArrayList<Double>(2);
        listB.add(-2.0);
        listB.add(0.0);
        Vertex v1 = new NVertex(listA);
        Vertex v2 = new NVertex(listB);

        ArrayList<Vertex> vertices;
        vertices = new ArrayList<Vertex>(2);
        vertices.add(v1);
        vertices.add(v2);

        /* REMOVE COMMENT TO ENABLE GRAPH GENERATOR TEST
        CompleteGraph g1;
        g1 = CompleteGraph.makeGraph(15, 3);
        g1.printGraph();
        */
        
        /*
        //test heap
        Heap H = new BinHeap<Vertex>();
        H.insert(v1,10);
        H.insert(v2,20);
        if(H.deletemin() != v1){
            ps.printf("Oops! \n");
        }
        ps.printf("Coordinate: %d", H.deletemin().components.get(0).intValue());
        */

    }

}
