package matrixMultiplication;

public abstract class DivideAndConquerMatrixMultiplication implements IntMatrixMultiplication  {
	// stores a*b in dest
    public abstract int[][] mult (int[][] dest, int[][] a, int[][] b) ;
    // returns the name of the algorithm used for multiplication
    public abstract String getName () ;

    // returns A + B
    public int [][] addMatrices(int [][] A, int [][] B) {
        int n = A.length;

        int [][] result = new int[n][n];

        for(int i=0; i<n; i++)
        	for(int j=0; j<n; j++)
        			result[i][j] = A[i][j] + B[i][j];

        return result;
    }

    // returns A - B
    public int [][] substractMatrices(int [][] A, int [][] B) {
        int n = A.length;

        int [][] result = new int[n][n];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                result[i][j] = A[i][j] - B[i][j];

        return result;
    }
    
    // stores parent[iB..iB+S-1][jB..jB+S-1] in child, where S = child.length
    public void divideMatrix(int[][] parent, int[][] child, int iB, int jB) {
        for(int i1 = 0, i2=iB; i1<child.length; i1++, i2++)
            for(int j1 = 0, j2=jB; j1<child.length; j1++, j2++)
                child[i1][j1] = parent[i2][j2];
    }

    // stores child in parent[iB..iB+S-1][jB..jB+S-1], where S = child.length
    public void copySubMatrix(int[][] child, int[][] parent, int iB, int jB) {
        for(int i1 = 0, i2=iB; i1<child.length; i1++, i2++)
            for(int j1 = 0, j2=jB; j1<child.length; j1++, j2++)
                parent[i2][j2] = child[i1][j1];
    }	
}
