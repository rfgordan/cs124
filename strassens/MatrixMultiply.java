import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class MatrixMultiply {
    public static void main(String[] args) throws FileNotFoundException{
	PrintStream ps = new PrintStream(System.out);
        
        //ensure proper arguments
        if(args.length != 3) {
            ps.printf("Input should be of the form: ./strassen flag dimension inputfile \n");
            return;
        }
        
        //load in arguments
        int flag = Integer.parseInt(args[0]);
        int dimension = Integer.parseInt(args[1]);
        
        int dim_pow_2 = 1;
        while(dim_pow_2 < dimension){
            dim_pow_2*=2;
        }
        
        Scanner s;
        //try{
        s = new Scanner(new BufferedReader(new FileReader(args[2])));
        /*} catch (FileNotFoundException e) {
            //failed to load file
            ps.printf("failed to read data correctly, please try again!\n");
            return;
        }*/
        
        //create two arrays
        int[][] arrayA = new int[dim_pow_2][dim_pow_2];
        int[][] arrayB = new int[dim_pow_2][dim_pow_2];
        
        //loop over both arrays
        if(flag==1){
            Random rand = new Random();
            for (int j = 0; j < dim_pow_2; j++){
                for (int k = 0; k < dim_pow_2; k++){
                    if(j<dimension && k < dimension){
                        if(rand.nextDouble() > 0.5){
                            arrayA[j][k] = 0;
                        }else{
                            arrayA[j][k] = 1;
                        }
                    }else{
                        arrayA[j][k] = 0;
                    }
                }
            }
            for (int j = 0; j < dim_pow_2; j++){
                for (int k = 0; k < dim_pow_2; k++){
                    if(j<dimension && k < dimension){
                        if(rand.nextDouble() > 0.5){
                            arrayB[j][k] = 0;
                        }else{
                            arrayB[j][k] = 1;
                        }
                    }else{
                        arrayB[j][k] = 0;
                    }
                }
            }
        }else{
            for (int j = 0; j < dim_pow_2; j++){
                for (int k = 0; k < dim_pow_2; k++){
                    if(j<dimension && k < dimension){
                        arrayA[j][k] = s.nextInt();
                    }else{
                        arrayA[j][k] = 0;
                    }
                }
            }

            //printDiagonal(arrayA,dim_pow_2,ps);

            for (int j = 0; j < dim_pow_2; j++){
                for (int k = 0; k < dim_pow_2; k++){
                    if(j<dimension && k <dimension){
                        arrayB[j][k] = s.nextInt();
                    }else{
                        arrayB[j][k] = 0;
                    }
                }
            }
        }
        
        //printDiagonal(arrayB,dim_pow_2,ps);
        int[][] result = new int[dim_pow_2][dim_pow_2];
        multiply(arrayA,0,0,arrayB,0,0,result,0,0,dim_pow_2);
        printDiagonal(result,dimension,ps);
        
        
    }
    
    public static void add(int[][] arrayA, int offsetAX, int offsetAY, int[][] arrayB, int offsetBX, int offsetBY, int[][] target, int offsetTX, int offsetTY, int dim, boolean addition){
        if(addition){
            for(int i = 0; i < dim; i++){
                for(int j = 0; j < dim; j++){
                    target[offsetTY+i][offsetTX+j] = arrayA[offsetAY+i][offsetAX+j]+arrayB[offsetBY+i][offsetBX+j];
                }
            }
        }else{
            for(int i = 0; i < dim; i++){
                for(int j = 0; j < dim; j++){
                    target[offsetTY+i][offsetTX+j] = arrayA[offsetAY+i][offsetAX+j]-arrayB[offsetBY+i][offsetBX+j];
                }
            }
        }
    }
    
    //decide which multiplication algorithm to use
    //or perform simple base case
    //TODO
    public static void multiply(int[][] arrayA, int offsetAX, int offsetAY, int[][] arrayB, int offsetBX, int offsetBY, int[][] target, int offsetTX, int offsetTY, int dim){
        if(dim==1){
            target[offsetTY][offsetTX] = arrayA[offsetAY][offsetAX]*arrayB[offsetBY][offsetBX];
        }else if(dim<=64){
            basicMultiply(arrayA,offsetAX,offsetAY,arrayB,offsetBX,offsetBY,target, offsetTX, offsetTY, dim);
        }else{
            strassens(arrayA,offsetAX,offsetAY,arrayB,offsetBX,offsetBY,target, offsetTX, offsetTY, dim);
        }
    }
    
    //implementation of strassens algorithm
    public static void strassens(int[][] arrayA, int offsetAX, int offsetAY, int[][] arrayB, int offsetBX, int offsetBY, int[][] target, int offsetTX, int offsetTY, int dim){
        
        //PrintStream ps = new PrintStream(System.out);
        
        int halved = dim/2;
        //allocate sub matrices
        int[][] P5 = new int[halved][halved];
        int[][] P2 = new int[halved][halved];//will become P3
        int[][] P1 = new int[halved][halved];
        int[][] P4 = new int[halved][halved];
        
        int[][] utility = new int[halved][halved];
        
        //calculate P6 and store it directly in the target slot, will only be needed once
        add(arrayA,offsetAX+halved,offsetAY,arrayA,offsetAX+halved,offsetAY+halved,P4,0,0,halved,false);
        add(arrayB,offsetBX+0,offsetBY+halved,arrayB,offsetBX+halved,offsetBY+halved,P1,0,0,halved,true);
        multiply(P4,0,0,P1,0,0,target,offsetTX,offsetTY,halved);
        
        //print P6
        //printDiagonal(target,dim,ps);
        
        //calculate P5
        add(arrayA,offsetAX,offsetAY,arrayA,offsetAX+halved,offsetAY+halved,P4,0,0,halved,true);
        add(arrayB,offsetBX,offsetBY,arrayB,offsetBX+halved,offsetBY+halved,utility,0,0,halved,true);
        multiply(P4,0,0,utility,0,0,P5,0,0,halved);
        
        //print P5
        //printDiagonal(P5,halved,ps);
        
        //calculate P4
        add(arrayB,offsetBX,offsetBY+halved,arrayB,offsetBX,offsetBY,utility,0,0,halved,false);
        multiply(arrayA,offsetAX+halved,offsetAY+halved,utility,0,0,P4,0,0,halved);
        
        //print P4
        //printDiagonal(P4,halved,ps);
        
        //calculate P2
        add(arrayA,offsetAX,offsetAY,arrayA,offsetAX+halved,offsetAY,utility,0,0,halved,true);
        multiply(utility,0,0,arrayB,offsetBX+halved,offsetBY+halved,P2,0,0,halved);
        
        //print P2
        //printDiagonal(P2,halved,ps);
        
        //add P5,P4,P2 into AE+BG
        add(P5,0,0,target,0,0,target,0,0,halved,true);
        add(P4,0,0,target,0,0,target,0,0,halved,true);
        add(target,0,0,P2,0,0,target,0,0,halved,false);
        
        //calculate P1
        add(arrayB,offsetBX+halved,offsetBY,arrayB,offsetBX+halved,offsetBY+halved,utility,0,0,halved,false);
        multiply(arrayA,offsetAX,offsetAY,utility,0,0,P1,0,0,halved);
        
        //print P1
        //printDiagonal(P1,halved,ps);
        
        //add P1 and P2 into AF+BH
        add(P1,0,0,P2,0,0,target,halved,0,halved,false);
        
        //dont need P2, now stores P3
        add(arrayA,offsetAX,offsetAY+halved,arrayA,offsetAX+halved,offsetAY+halved,utility,0,0,halved,true);
        multiply(utility,0,0,arrayB,offsetBX,offsetBY,P2,0,0,halved);
        
        //print P3
        //printDiagonal(P2,halved,ps);
        
        //add P3 and P4 into CE+DG
        add(P2,0,0,P4,0,0,target,offsetTX,offsetTY+halved,halved,true);
        
        //multiply P7 into CF+DH, THEN add in other Ps
        add(arrayA,offsetAX,offsetAY,arrayA,offsetAX,offsetAY+halved,utility,0,0,halved,false);
        add(arrayB,offsetBX,offsetBY,arrayB,offsetBX+halved,offsetBY,P4,0,0,halved,true);
        multiply(utility,0,0,P4,0,0,target,offsetTX+halved,offsetTY+halved,halved);
        
        //print P7
        //printDiagonal(target,dim,ps);
        
        add(P5,0,0,target,offsetTX+halved,offsetTY+halved,target,offsetTX+halved,offsetTY+halved,halved,false);
        add(P1,0,0,target,offsetTX+halved,offsetTY+halved,target,offsetTX+halved,offsetTY+halved,halved,true);
        add(target,offsetTX+halved,offsetTY+halved,P2,0,0,target,offsetTX+halved,offsetTY+halved,halved,false);
        
    }
    
    //left array should be array of rows, right array should be array of columns
    public static void basicMultiply(int[][] arrayA, int offsetAX, int offsetAY, int[][] arrayB, int offsetBX, int offsetBY, int[][] target, int offsetTX, int offsetTY, int dim){
        
        
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                target[offsetTY+i][offsetTX+j] = 0;
                for (int k = 0; k < dim; k++){
                    target[offsetTY+i][offsetTX+j] += arrayA[offsetAY+j][offsetAX+k]*arrayB[offsetBY+k][offsetBX+i];
                }
            
            }
        }
        
    
        
    }
    
    public static void printDiagonal(int[][] array, int dim, PrintStream ps){
        for(int i = 0; i < dim; i++){
            ps.printf("%d\n",array[i][i]);
        }
        ps.printf("\n");
    }





}