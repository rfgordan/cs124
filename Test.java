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

        CompleteGraph g1;
        g1 = CompleteGraph.makeGraph(15, 3);
        g1.printGraph();
        //double weight =



        //ps.printf("Weight obtained: %f\n", weight);
//        ps.printf("First element: %d\n", v1.components.get(0).intValue());
//        ps.printf("Second element: %d\n", v2.components.get(1).intValue());

    }

}
