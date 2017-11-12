package matrixMultiplication;

public class individualTest {
	public static void main(String[] args) {
		BoxMatrixMultiplication box = new BoxMatrixMultiplication();
		int [][] a = new int[][]{{1,2,1,2},{2,2,1,3},{1,4,1,5},{1,6,1,7}};
		printMatrix(a);
		int [][] b = new int[][]{{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
		printMatrix(b);
		int [][] dest = new int[4][4];
		printMatrix(box.mult(dest, a, b));
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
        System.out.println('\n');
    }
}
