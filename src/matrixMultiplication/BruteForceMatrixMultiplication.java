package matrixMultiplication;

public class BruteForceMatrixMultiplication implements IntMatrixMultiplication {

	public String getName() {
		return "Brute Force";
	}

	public int[][] mult(int[][] dest, int[][] a, int[][] b) {
        int n = a.length;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int sum = 0;

                for(int k=0; k<n; k++) {
                    sum += a[i][k] * b[k][j];
                }

                dest[i][j] = sum;
            }
        }

        return dest;

	}

}
