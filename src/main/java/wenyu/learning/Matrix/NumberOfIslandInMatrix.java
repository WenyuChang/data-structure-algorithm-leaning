package wenyu.learning.Matrix;

/**
 * Created by Wenyu on 12/14/16.
 *
 * Given a 2-d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    Example 1:
     11110
     11010
     11000
     00000
    Answer: 1

 * Solution1: The basic idea of the following solution is merging adjacent lands,
 * and the merging should be done recursively. Each element is visited once only.
 * So time is O(m*n).
 */
public class NumberOfIslandInMatrix {
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0||grid[0].length==0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int count=0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1'){
                    count++;
                    merge(grid, i, j);
                }
            }
        }

        return count;
    }

    public void merge(char[][] grid, int i, int j){
        int m=grid.length;
        int n=grid[0].length;

        if(i<0||i>=m||j<0||j>=n||grid[i][j]!='1')
            return;

        grid[i][j]='X';

        merge(grid, i-1, j);
        merge(grid, i+1, j);
        merge(grid, i, j-1);
        merge(grid, i, j+1);
    }
}
