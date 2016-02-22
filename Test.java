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
        /* EUCLIDEAN DISTANCE TESTER
        ps.printf("Testing distance\n");
        ArrayList<Double> listA;
        listA = new ArrayList<Double>(2);
        listA.add(0.0);
        listA.add(2.0);
        ArrayList<Double> listB;
        listB = new ArrayList<Double>(2);
        listB.add(-2.0);
        listB.add(0.0);
        NVertex v1 = new NVertex(listA);
        NVertex v2 = new NVertex(listB);

        ArrayList<NVertex> vertices;
        vertices = new ArrayList<NVertex>(2);
        vertices.add(v1);
        vertices.add(v2);
        */

       /* REMOVE COMMENT TO ENABLE GRAPH GENERATOR TEST
        CompleteGraph g1;
        g1 = CompleteGraph.makeGraph(15, 3);
        g1.printGraph();
        */


        
        
        //test heap
        /*
        BinHeap<NVertex> H;
        H = new BinHeap<NVertex>();
        for (int i = 0; i < 10; i++){
            H.insert(v1,100-i);
            H.insert(v2,200-i);
        }
        if(H.deletemin() != v1){
            ps.printf("Oops! \n");
        }
        for(int i = 0; i < 15; i++)
        {
            ps.printf("Coordinate: %d \n", H.deletemin().components.get(0).intValue());
        }
        */

    }

}
