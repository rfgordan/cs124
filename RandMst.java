/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author boreas
 */
public class RandMst {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static List<Vertex> makeGraph(int n, int dim){
        List<Vertex> vlist = new ArrayList<Vertex>();
        Random vertexGenerator = new Random();
        
        //create coordinates for each vertex
        for(int i = 0; i < n; i++){
            List<Double> coords = new ArrayList<Double>();
            for(int j = 0; j < dim; j++){
                coords.add(vertexGenerator.nextDouble());
            }
            Vertex newVertex = new Vertex(coords);
            vlist.add(newVertex);
        }
        
        return vlist;
    }
    public static void prim(List<Vertex>){
        
    }
    
}

