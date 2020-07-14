# 🟧 120. 三角形最小路径和 Triangle

## Problem

[120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

## 思路

### 动态规划 Bottom-up

1. 子问题：找出与当前位置与右侧位置在下层的节点中的最小值并累加。
2. 状态定义：dp[i][j] 对应三角形中每个位置自顶向下的最小路径和。
3. 状态转移方程：`dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + dp[i][j]`。
4. 三角形有一个隐含条件，每行的长度等于其所在行 n，所有构造 dp 数组时初始化为 dp[n][n] 即可。

## Code

外层循环从 n -1 递减，即从底边开始往上递推。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
```

空间优化，由于只涉及两行之间的数据，可以将 dp 降维。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
```

## Review

## First Review

按从上往下填表的思路，碰到很多坑，比如边界处理，甚至 DP 方程都分化为两种，很不好想。看了题解后，发现从下往上填表就不用考虑特殊情况了，只需要把 DP 方程中 i - 1 和 j - 1 的部分改为 i + 1 和 j + 1 就好。但偶然发现，[官方解答](https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/)也是从上往下，正确答案比我想象中复杂，决定放弃这种写法。

