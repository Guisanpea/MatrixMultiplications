package matrixMultiplication;

public interface IntMatrixMultiplication {
	// stores a*b in dest and return it
    public abstract int[][] mult (int[][] dest, int[][] a, int[][] b) ;
    // returns the name of the algorithm used for multiplication
    public abstract String getName () ;
}
