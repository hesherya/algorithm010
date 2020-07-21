# 🟩 70. 爬楼梯 Climb Stairs

## Problem

[70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) 

## 思路

其实就是求解斐波那契数列第 n 项。

### 动态规划 Memorization

1. 定义状态 dp[i] 为第 i 级楼梯的走法。
2. 状态转移方程 `dp[i] = dp[i - 1] + dp[i - 2]`。

### 动态规划 Bottom-up

已知 f1、f2，循环从 3 开始，每次循环将 f1 和 f2 向前挪动，最终结果就是最后一次循环的 f3。注意循环起始位置。时间复杂度 O(n)。

## Code
注意 `dp[0] = 1`，也可以从 2 开始循环。

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int f1 = 1, f2 = 2, f3 = 3;
        for (int i = 3; i <= n; ++i) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }
}
```

