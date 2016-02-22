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

    public static CompleteGraph simpleTest() {
        PrintStream ps = new PrintStream(System.out);

        ArrayList<Double> list1;
        list1 = new ArrayList<Double>(2);
        list1.add(1.0);
        list1.add(1.0);
        NVertex v1 = new NVertex(list1);
        ArrayList<Double> list2;
        list2 = new ArrayList<Double>(2);
        list2.add(3.0);
        list2.add(1.0);
        NVertex v2 = new NVertex(list2);
        ArrayList<Double> list3;
        list3 = new ArrayList<Double>(2);
        list3.add(3.0);
        list3.add(3.0);
        NVertex v3 = new NVertex(list3);
        ArrayList<Double> list4;
        list4 = new ArrayList<Double>(2);
        list4.add(4.0);
        list4.add(3.0);
        NVertex v4 = new NVertex(list4);
        ArrayList<Double> list5;
        list5 = new ArrayList<Double>(2);
        list5.add(4.0);
        list5.add(5.0);
        NVertex v5 = new NVertex(list5);
        ArrayList<Double> list6;
        list6 = new ArrayList<Double>(2);
        list6.add(3.0);
        list6.add(6.0);
        NVertex v6 = new NVertex(list6);
        ArrayList<Double> list7;
        list7 = new ArrayList<Double>(2);
        list7.add(3.0);
        list7.add(8.0);
        NVertex v7 = new NVertex(list7);
        ArrayList<Double> list8;
        list8 = new ArrayList<Double>(2);
        list8.add(6.0);
        list8.add(8.0);
        NVertex v8 = new NVertex(list8);
        ArrayList<Double> list9;
        list9 = new ArrayList<Double>(2);
        list9.add(8.0);
        list9.add(8.0);
        NVertex v9 = new NVertex(list9);
        ArrayList<Double> list10;
        list10 = new ArrayList<Double>(2);
        list10.add(8.0);
        list10.add(9.0);
        NVertex v10 = new NVertex(list10);

        ArrayList<NVertex> vertices = new ArrayList<NVertex>(10);
        vertices.add(v1);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v4);
        vertices.add(v9);
        vertices.add(v7);
        vertices.add(v10);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v8);

        CompleteGraph simpleGraph = new CompleteGraph(vertices, 2);

        return simpleGraph;
    }

    public static void heapTest() {
        PrintStream ps = new PrintStream(System.out);

        // set up heap
        BinHeap<NVertex> heap = new BinHeap<NVertex>();
        ArrayList<NVertex> vertices = new ArrayList<NVertex>(10);
        for (int i = 0; i < 10; i++) {
            ArrayList<Double> list;
            list = new ArrayList<Double>(2);
            list.add(0.0);
            list.add(0.0);
            NVertex genericVertex = new NVertex(list);
            genericVertex.setID(i);
            vertices.add(genericVertex);
        }

        // run operations on heap
        heap.insert(vertices.get(2), 2.0);
        heap.insert(vertices.get(1), 1.0);
        heap.insert(vertices.get(3), 3.0);

        NVertex deleted = heap.deletemin();
        // result should be 1
        ps.printf("First delete: %d. Expected: 1\n", deleted.getID());

        deleted = heap.deletemin();
        // result should be 2
        ps.printf("Second delete: %d. Expected: 2\n", deleted.getID());

        heap.insert(vertices.get(5), 5.0);
        deleted = heap.deletemin();
        // result should be 3
        ps.printf("Second delete: %d. Expected: 3\n", deleted.getID());

        heap.insert(vertices.get(2), 2.0);
        heap.insert(vertices.get(1), 3.0);

        // test to change last element to smallest
        ArrayList<Double> list;
        list = new ArrayList<Double>(2);
        list.add(0.0);
        list.add(0.0);
        NVertex v1 = new NVertex(list);
        v1.setPosition(3);
        heap.decreaseKey(v1, 1.0);

        deleted = heap.deletemin();
        // result should be 1
        ps.printf("Second delete: %d. Expected: 1\n", deleted.getID());

    }

}
