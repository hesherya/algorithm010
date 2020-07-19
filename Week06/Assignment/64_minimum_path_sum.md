# 🟧 64. 最小路径和 Minimum Path Sum

## Problem

[64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/) 

## 思路

### 动态规划

1. 状态定义：dp[i][j] 为网格中左上角到 (i, j) 的最小路径和。
2. 状态转移方程：`dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]`。

## Code

```java
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) dp[i][j] = grid[i][j] + dp[i][j - 1];
                else if (j == 0) dp[i][j] = grid[i][j] + dp[i - 1][j];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n -1];
    }
}
```

由于每次要累加 grid[i][j] 的值，可以不用额外的数组 dp，直接在 grid 上累加即可。

状态转移方程变为：`grid(i, j) =grid(i, j) + min(grid(i + 1, j), grid(i, j + 1))`。

空间复杂度降为 O(1)。思路参考 [力扣](https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-dong-tai-gui-hua-gui-fan-liu-c/)。

```java
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] = grid[i][j] + grid[i][j - 1];
                else if (j == 0) grid[i][j] = grid[i][j] + grid[i - 1][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n -1];
    }
}
```

