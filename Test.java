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
        ArrayList<Integer> listA;
        listA = new ArrayList<Integer>(2);
        listA.add(0);
        listA.add(2);
        ArrayList<Integer> listB;
        listB = new ArrayList<Integer>(2);
        listB.add(-2);
        listB.add(0);
        Vertex v1 = new Vertex(listA);
        Vertex v2 = new Vertex(listB);

        ArrayList<Vertex> vertices;
        vertices = new ArrayList<Vertex>(2);
        vertices.add(v1);
        vertices.add(v2);
        
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
                
        Graph g1 = new Graph(vertices);

        double weight = g1.getWeight(g1.vertices.get(0), g1.vertices.get(1));
        ps.printf("Weight obtained: %f\n", weight);
//        ps.printf("First element: %d\n", v1.components.get(0).intValue());
//        ps.printf("Second element: %d\n", v2.components.get(1).intValue());

    }

}
