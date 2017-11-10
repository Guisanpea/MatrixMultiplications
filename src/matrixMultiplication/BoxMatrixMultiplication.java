package matrixMultiplication;

import java.util.ArrayList;

public class BoxMatrixMultiplication extends DivideAndConquerMatrixMultiplication {

	public String getName() {
		return "Box Multiplication";
	}

	public int[][] mult(int[][] dest, int[][] a, int[][] b) {
		IntMatrixMultiplication brute = new BruteForceMatrixMultiplication();
		int size = a.length;
		
		if(size == 2)
			dest = brute.mult(dest, a, b);
		else {
			ArrayList<int[][]> quadrantsA, quadrantsB;
			quadrantsA = new ArrayList<int[][]>();
			quadrantsB = new ArrayList<int[][]>();
			
			initializeQuadrants(quadrantsA, quadrantsB,a,b);
			computeMultiplicationAndMerge(quadrantsA,quadrantsB, dest);
		}
	
		return dest;
	}

	private void initializeQuadrants(ArrayList<int[][]> quadrantsA, ArrayList<int[][]> quadrantsB,
									 int[][] a, 					int[][] b) {
		getQuadrants(a, quadrantsA);
		getQuadrants(b, quadrantsB);
	}

	private void getQuadrants(int[][] a, ArrayList<int[][]> quadrants) {
		int childSize = a.length/2;
		
		int [][] X11 = new int [childSize][childSize];
		int [][] X12 = new int [childSize][childSize];
		int [][] X21 = new int [childSize][childSize];
		int [][] X22 = new int [childSize][childSize];
		
		divideMatrix(a, X11, 0, 0);
		divideMatrix(a, X12, 0, childSize);
		divideMatrix(a, X21, childSize, 0);
		divideMatrix(a, X22, childSize, childSize);
		
		quadrants.add(X11);
		quadrants.add(X12);
		quadrants.add(X21);
		quadrants.add(X22);
	}

	private void computeMultiplicationAndMerge(ArrayList<int[][]> quadrantsA, ArrayList<int[][]> quadrantsB, int[][] dest) {
		int childSize = dest.length/2;
		int[][] a11xb11 = mult(new int [childSize][childSize], quadrantsA.get(0), quadrantsB.get(0));
		int[][] a12xb21 = mult(new int [childSize][childSize], quadrantsA.get(1), quadrantsB.get(2));
		int[][] a11xb12 = mult(new int [childSize][childSize], quadrantsA.get(0), quadrantsB.get(1));
		int[][] a12xb22 = mult(new int [childSize][childSize], quadrantsA.get(1), quadrantsB.get(3));
		int[][] a21xb11 = mult(new int [childSize][childSize], quadrantsA.get(2), quadrantsB.get(0));
		int[][] a22xb21 = mult(new int [childSize][childSize], quadrantsA.get(3), quadrantsB.get(2));
		int[][] a21xb12 = mult(new int [childSize][childSize], quadrantsA.get(2), quadrantsB.get(1));
		int[][] a22xb22 = mult(new int [childSize][childSize], quadrantsA.get(3), quadrantsB.get(3));
		
		int[][] c11 = addMatrices(a11xb11,a12xb21);
		int[][] c12 = addMatrices(a11xb12,a12xb22);
		int[][] c21 = addMatrices(a21xb11,a22xb21);
		int[][] c22 = addMatrices(a21xb12,a22xb22);
		
		copySubMatrix(c11, dest, 0, 0);
		copySubMatrix(c12, dest, 0, childSize);
		copySubMatrix(c21, dest, childSize, 0);
		copySubMatrix(c22, dest, childSize, childSize);
	}
    
}
