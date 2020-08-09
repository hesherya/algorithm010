# 🟩 746. 使用最小花费爬楼梯 Min Cost Climbing Stairs

## Problem

[746. 使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/) 

## 思路

### 动态规划

* 状态定义：dp[i] 为第 i 级楼梯的最小花费
* 状态转移方程：`dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2])`

## Code

```java
public int minCostClimbingStairs(int[] cost) {
    if (cost == null || cost.length == 0) {
        return 0;
    }
    int length = cost.length;
    for (int i = 2; i < length; ++i) {
        // cost[0] 和 cost[1] 都是 0，不用处理
        // cost[i] 是当前花费与前两步中最小值相加得到
        cost[i] += Math.min(cost[i - 1], cost[i - 2]);
    }
    // 最后两个步都能登顶，选少的
    return Math.min(cost[length - 1], cost[length - 2]);
}
```

