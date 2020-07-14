# 🟧 63. 不同路径 Unique Paths II

## Problem

 [63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)

## 思路

### 动态规划

相比 [🟧 62. 不同路径 Unique Paths](bear://x-callback-url/open-note?id=99D8E06F-8EB5-4648-ADB9-050062E61BF1-596-0000A727FE602C0D) 多了障碍物的情况，在填充 dp 数组时判断 obstacleGrid 是否是 1，如果是障碍物，dp 填充为 0，否则按递推公式填充。

一维 dp 数组需要注意，只需要初始化 dp[0]

## Code

二维 DP 数组，比较好处理。

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 填充第一列
        for (int i = 0; i < m; ++i) {
            if (obstacleGrid[i][0] == 1) break;
            else dp[i][0] = 1;
        }
        // 填充第一行
        for (int i = 0; i < n; ++i) {
            if (obstacleGrid[0][i] == 1) break;
            else dp[0][i] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

官解，一维 DP 数组，需要从 0 开始循环并判断内层循环计数要从第二列开始才使用递推公式填充。

在初始化 dp 数组时，障碍物后面的数组依然填充 1 其实不太正确，但由于是最下层和最右层，

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int dp[] = new int[m];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    if (j > 0 && obstacleGrid[i][j - 1] == 0) dp[j] += dp[j - 1];
                }
            }
        }
        return dp[m - 1];
    }
}

```

国际站高票，写得很清晰。dp[0] 默认赋值为 1，即使第一个位置是障碍物，循环内还会再次判断并将其赋值为 0，所以不必担心。

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int width = obstacleGrid[0].length;
    int[] dp = new int[width];
    dp[0] = 1;
    for (int[] row : obstacleGrid) {
        for (int j = 0; j < width; j++) {
            if (row[j] == 1)
                dp[j] = 0;
            else if (j > 0)
                dp[j] += dp[j - 1];
        }
    }
    return dp[width - 1];
}
```

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] mem = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(mem[i],-1);
        }
        return dfsUniquePathsWithObstacles(obstacleGrid,0,0,mem);
    }

    private int dfsUniquePathsWithObstacles(int[][] board, int i, int j,int[][] mem) {
        if (i == board.length - 1 && j == board[0].length - 1 && board[i][j] == 0) {
            return 1;
        }
        if (i >= board.length || j >= board[0].length || board[i][j] == 1) {
            return 0;
        }
        if (mem[i][j] != -1) {
            return mem[i][j];
        }
        int total = 0;
        total += dfsUniquePathsWithObstacles(board, i + 1, j, mem);
        total += dfsUniquePathsWithObstacles(board, i, j + 1, mem);
        mem[i][j] = total;
        return total;
    }

作者：pdzz
链接：https://leetcode-cn.com/problems/unique-paths-ii/solution/java-dpdfs-ji-yi-hua-chao-yue-100-by-pdzz/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```

## Review

### Third Review

1. 一维数组解法，初始化只需要初始化第一个位置。如果初始化一行也可以，但冗余了一点，遇到障碍物后面全是 0。同时需要从 n = 1 开始遍历。
2. 因为 dp 只初始化了第一个位置，所以在循环内部，除了判断改位置是否有障碍物外，还要判断左边前一个位置是否是空地，这样才能应用递推公式填表。
3. 由于一维数组需要用到 dp[j - 1]，所以要保证 j 大于零。

