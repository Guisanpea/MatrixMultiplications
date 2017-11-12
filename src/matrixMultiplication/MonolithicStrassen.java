package matrixMultiplication;

public class MonolithicStrassen extends DivideAndConquerMatrixMultiplication{
	public String getName() {
		return "Strassen Method";
	}
	
	public int[][] mult(int[][] dest, int[][] a, int[][] b) {
		int size = a.length;
		int childSize = size/2;
		
		if (size == 2) {
			int m1 = (a[0][0]+a[1][1])*(b[0][0]+b[1][1]);
			int m2 = (a[1][0]+a[1][1])*b[0][0];
			int m3 = a[0][0]*(b[0][1]-b[1][1]);
			int m4 = a[1][1]*(b[1][0]-b[0][0]);
			int m5 = (a[0][0]+a[0][1])*b[1][1];
			
			dest[0][0] = m1+m4-m5+(a[0][1]-a[1][1])*(b[1][0]+b[1][1]);
			dest[0][1] = m3+m5;
			dest[1][0] = m2+m4;
			dest[1][1] = m1+m3-m2+(a[1][0]-a[0][0])*(b[0][0]+b[0][1]);
		} else {
			int [][] a11 = new int[childSize][childSize];
			int [][] a12 = new int[childSize][childSize];
			int [][] a21 = new int[childSize][childSize];
			int [][] a22 = new int[childSize][childSize];
			int [][] b11 = new int[childSize][childSize];
			int [][] b12 = new int[childSize][childSize];
			int [][] b21 = new int[childSize][childSize];
			int [][] b22 = new int[childSize][childSize];
			
			//axx initialization
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					a11[i][j] = a[i][j];
				}
			}
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					a12[i][j] = a[i][j+childSize];
				}
			}
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					a21[i][j] = a[i+childSize][j];
				}
			}
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					a22[i][j] = a[i+childSize][j+childSize];
				}
			}
			//bxx initialization
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					b11[i][j] = a[i][j];
				}
			}
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					b12[i][j] = a[i][j+childSize];
				}
			}
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					b21[i][j] = a[i+childSize][j];
				}
			}
			for (int i = 0; i < childSize; i++) {
				for (int j = 0; j < childSize; j++) {
					b22[i][j] = a[i+childSize][j+childSize];
				}
			}
			int [][] m1 = mult(new int[childSize][childSize],
					addMatrices(a11, a22), addMatrices(b11, b22));
			int [][] m2 = mult(new int[childSize][childSize],
					addMatrices(a21, a22), b11);
			int [][] m3 = mult(new int[childSize][childSize],
					a11, substractMatrices(b12, b22));
			int [][] m4 = mult(new int[childSize][childSize],
					a22, substractMatrices(b21, b11));
			int [][] m5 = mult(new int[childSize][childSize],
					addMatrices(a11, a12), b22);
			int [][] m6 = mult(new int[childSize][childSize],
					substractMatrices(a21, a11), addMatrices(b11,b12));
			int [][] m7 = mult(new int[childSize][childSize],
					substractMatrices(a12, a22), addMatrices(b21,b22));
			
			copySubMatrix(addMatrices(m1,addMatrices(substractMatrices(m4, m5),m7)), dest, 0, 0);
	        copySubMatrix(addMatrices(m3, m5), dest, 0, childSize);
	        copySubMatrix(addMatrices(m2, m4), dest, childSize, 0);
	        copySubMatrix(addMatrices(m1,addMatrices(substractMatrices(m3, m2),m6)), dest, childSize, childSize);
        }
		return dest;
	}
	
	
}
