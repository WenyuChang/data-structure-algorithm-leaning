package wenyu.learning.Matrix;

/**
 * Created by Wenyu on 12/1/16.
 *
 * Problem1: A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 *
 * Problem2: Same question as above, just add obstacle cell. 1 in cell is obstacle
 */
public class UniquePathsToCell {
    public int uniquePaths(int m, int n) {
        int[][] aux = new int[n][m];
        aux[0][0] = 1;

        for (int i=1; i<m; i++) {
            // better than just set aux[0][i] to 1, as this would fit for cell with obstacles
            aux[0][i] = aux[0][i-1];
        }
        for (int i=1; i<n; i++) {
            // better than just set aux[0][i] to 1, as this would fit for cell with obstacles
            aux[i][0] = aux[i-1][0];
        }

        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                aux[i][j] = aux[i-1][j] + aux[i][j-1];
            }
        }

        return aux[n-1][m-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] aux = new int[obstacleGrid.length][obstacleGrid[0].length];
        aux[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i=1; i<obstacleGrid[0].length; i++) {
            aux[0][i] = obstacleGrid[0][i] == 1 ? 0 : aux[0][i-1];
        }
        for (int i=1; i<obstacleGrid.length; i++) {
            aux[i][0] = obstacleGrid[i][0] == 1 ? 0 : aux[i-1][0];
        }

        for (int i=1; i<obstacleGrid.length; i++) {
            for (int j=1; j<obstacleGrid[0].length; j++) {
                aux[i][j] = obstacleGrid[i][j] == 1 ? 0 : aux[i-1][j] + aux[i][j-1];

            }
        }

        return aux[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
