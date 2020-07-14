# 🟧 62. 不同路径 Unique Paths

## Problem

[62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)

## 思路

### Brute Force 递归

从起始位置递归到 m、n.。

1. 终止条件：走到 [m, n]，就加一。
2. 每层分别求向右走的路径数与向下走的路径数，最后相加就是此位置到终点的路径数。
3. 时间复杂度 O(2^(m + n))。

### 动态规划 Bottom Up

利用 dp[i][j] 记录每个位置路径数，从终点往前走，递推起点的路径数，为了编码方便终点可以记为  (0, 0)，起点为  (m - 1, n - 1)。

1. 子问题：求左侧路径数与上面路径数的和。
2. 状态：dp[i][j]。
3. 递推公式：`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`。

由于 dp[i][j] 的大小只与左侧及上面有关，所以二维数组可以降成一维，即：

`dp[i] = dp[i] + dp[i -1]`

等式右边的 dp[i] 是上一层路径数，dp[i -1]  是左侧路径数。

## Code

```java
class Solution {
    public int uniquePaths(int m, int n) {
        return dfs(m, n, 1, 1);
    }

    private int dfs(int m , int n, int col, int row) {
        if (col == m && row == n) return 1;
        int rightPaths = col <= m  ? dfs(m, n, col + 1, row) : 0;
        int downPaths = row <= n ? dfs(m, n, col, row + 1) : 0;
        return rightPaths + downPaths;
    }
}
```

DP 二维数组 Bottom Up，左上角其实是重点，往起点走。由于第一行和第一列只有一条路径，所以都填充为 1，DP 方程 `dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`，注意循环时行列都要从 1 开始。

时间复杂度：`O(m * n)` 两重循环
空间复杂度：`O(m * n)` 二维数组

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) dp[i][0] = 1;
        for (int i = 0; i < n; ++i) dp[0][i] = 1;
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

DP 优化，二维数组降为一维数组，因为其实只用到了 dp[i][j - 1] （本行）和 dp[i - 1][j]（上一行）。所以优化后空间复杂度为 O(n)。

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        for (int i = 0; i < m; ++i) dp[i] = 1;
        for (int j = 1; j < n; ++j) {
            for (int i = 1; i < m; ++i) {
                // 用上一行循环的结果
                dp[i] += dp[i - 1];
            }
        }
        return dp[m - 1];
    }
}
```

