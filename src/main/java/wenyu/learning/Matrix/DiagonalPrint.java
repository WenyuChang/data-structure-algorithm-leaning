package wenyu.learning.Matrix;

/*
 * Given a 2D matrix, print all elements of the given matrix in diagonal order. For example, consider the following 5 X 4 input matrix.
 *   1     2     3     4
 *   5     6     7     8
 *   9    10    11    12
 *  13    14    15    16
 *  17    18    19    20
 * 
 * Diagonal printing of the above matrix is
 *   1
 *   5     2
 *   9     6     3
 *  13    10     7     4
 *  17    14    11     8
 *  18    15    12
 *  19    16
 *  20
 */
public class DiagonalPrint {

	public static void print(int[][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			int row = i;
			int col = 0;
			for(; row>=0&&col<matrix[0].length; row--,col++) {
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}
		
		for(int j=1; j<matrix[0].length; j++) {
			int row = matrix.length-1;
			int col = j;
			for(; row>=0&&col<matrix[0].length; row--,col++) {
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}};
		print(matrix);
	}

}
