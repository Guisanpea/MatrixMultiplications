package matrixMultiplication;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestIntMatrixMultiplication {

    public static void main (String[] args) throws Exception {
        final int n1 = args.length > 0 ? Integer.parseInt(args[0]) : 8; // initial matrix size
        final int dup = args.length > 1 ? Integer.parseInt(args[1]) : 4; // no. of times the size is double
        final int nt = args.length > 2 ? Integer.parseInt(args[2]) : 10; // no. of tests per matrix size
        final Random random = new Random(1);
        final String bruteFilename = "brute.txt";       // filenames for storing statistics
        final String strassenFilename = "strassen.txt";
        final String boxFilename = "box.txt";
        
        IntMatrixMultiplication brute = new BruteForceMatrixMultiplication();
        IntMatrixMultiplication strassen = new MonolithicStrassen();
        IntMatrixMultiplication box = new BoxMatrixMultiplication();
        
        long[][] statsBrute    = new long [dup][nt+1];
        long[][] statsStrassen = new long [dup][nt+1];
        long[][] statsBox      = new long [dup][nt+1];
        
        for (int n=n1, k=0; k<dup; k++, n*=2) {
        	int[][] a, b, c;

        	a = new int[n][n];
        	b = new int[n][n];
        	c = new int[n][n];


        	System.out.println("n = " + n);

        	statsBrute[k][0] = n;
        	statsStrassen[k][0] = n;
        	statsBox[k][0] = n;
        	for (int j = 0; j < nt; ++j) {
        		for(int k1=0; k1<n; k1++) {
            		for(int k2=0; k2<n; k2++) {
            			a[k1][k2] = random.nextInt(99);
            			b[k1][k2] = random.nextInt(99);
            		}
            	}
        		statsBrute[k][j+1]    = timeMultiply(brute, a, b, c);
        		statsStrassen[k][j+1] = timeMultiply(strassen, a, b, c);
        		statsBox[k][j+1]      = timeMultiply(box, a, b, c);
        	}
        }
		writeStats (bruteFilename, statsBrute);
		writeStats (strassenFilename, statsStrassen);
		writeStats (boxFilename, statsBox);
        		
    }

    static long timeMultiply (IntMatrixMultiplication algorithm, int[][] a, int[][] b, int[][] c) {
        long interval;
        final long start = System.currentTimeMillis();
        algorithm.mult(c, a, b);
        final long finish = System.currentTimeMillis();
        interval = finish - start;
        System.out.println(algorithm.getName() + " took " + interval + "ms");
        return interval;
    }
    
    static void writeStats (String filename, long[][] stats) throws IOException {
    	PrintWriter out = new PrintWriter(new FileWriter(filename));
    	final int n1 = stats.length;
    	final int n2 = stats[0].length;
    	for (int i=0; i<n1; i++) {
			out.print(stats[i][0]);
    		for (int j=1; j<n2; j++)
    			out.print("\t" + stats[i][j]);
    		out.println();
    	}
    	out.close();
    }

    static void printMatrix (int[][] m) {
    	int n = m.length;
        for (int i=0; i<n; i++) {
            System.out.print("[\t");
            for (int j=0; j<n; j++) {
                System.out.print(m[i][j]);
                System.out.print('\t');
            }
            System.out.println(']');
        }
    }
}
