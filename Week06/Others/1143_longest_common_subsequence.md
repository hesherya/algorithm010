# 🟧 1143. 最长公共子序列 Longest Common Subsequence

## Problem

[1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)

## 思路

### 动态规划

参考[力扣](https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/)。

1. 状态定义：二维数组 dp[i][j]，对于 S1[1..i] 和 S2[1..j] ，他们的 LCS 是 dp[i][j]。
2. 子问题：S1[i] 和 S2[j] 相同，路径数加一。S1[i] 和 S2[j] 不同，则至少有一个不在 LCS 中，应该舍弃。
3. 状态转移方程：
   * `S1[i] ==  S2[j]: dp[i][j] = dp[i - 1][j - 1] + 1`
   * `S1[i] != S2[j]: dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j])`

## Code

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null ||
                text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();
        // 数组定义比字符串长度大一维方便比较字符
        int[][] dp = new int[m + 1][n + 1];
        // 边界均为闭区间从 1 到 m 和 n
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // charAt 方法跟数组下标类似都是从 0 开始
                // 状态转移方程依赖 i - 1 和 j - 1 位置，所以 i 和 j 从 1 开始更方便
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
```

## Review

### Third Review

1. i 和 j 循环边界少了等于 m 和 n 的情况。

