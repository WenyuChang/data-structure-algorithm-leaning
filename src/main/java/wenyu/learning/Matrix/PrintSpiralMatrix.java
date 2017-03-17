package wenyu.learning.Matrix;

/**
 * Created by Wenyu on 11/30/16.
 *
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example:
 * Given n = 3,
 * You should return the following matrix:
     [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
     ]
 */
public class PrintSpiralMatrix {
    private int printSingleSpiral(int[][] matrix, int level, int start) {
        int row = level;
        int col = level;

        while(col<matrix[0].length-level) {
            matrix[row][col++] = start++;
        }

        row++;
        col = matrix[0].length-level-1;
        while(row<matrix.length-level) {
            matrix[row++][col] = start++;
        }

        col--;
        row = matrix.length-level-1;
        while(col>=level && row>level) {
            matrix[row][col--] = start++;
        }

        col = level;
        row--;
        while(row>level && col<matrix[0].length-level-1) {
            matrix[row--][col] = start++;
        }

        return start;
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int level = 0;
        int start = 1;
        while(level*2<matrix.length && level*2<matrix[0].length) {
            start = printSingleSpiral(matrix, level, start);
            level++;
        }

        return matrix;
    }
}
