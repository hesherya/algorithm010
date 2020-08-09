# 🟩 122. 买卖股票的最佳时机 II Best Time to Buy and Sell Stock II

## Problem

[122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) 

## 思路

### 贪心算法

遍历价格数组，计算当天与前一天的差值，如果大于零就累加起来。相当于每天买卖，但其实题目要求是不允许当天多次买卖的，只不过从数学上来说，结果是等价的，参考题解[力扣](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/)。

### 动态规划

参考题解[力扣](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/)。

### 多维 DP，可团灭所有股票最大利润问题

思路可参考 [一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/) ，列举所有状态，并升维 dp 数组。

## Code

贪心算法

```java
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int tmp;
        for (int i = 1; i < prices.length; ++i) {
            tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }
        return profit;
    }
}
```

暴力搜索

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        return maxProfit(prices, 1);
    }

    private int maxProfit(int[] prices, int n) {
        if (n == prices.length) return 0;
        int profit = prices[n] - prices[n - 1];
        if (profit > 0) {
            return maxProfit(prices, n + 1) + profit;
        } else {
            return maxProfit(prices, n + 1);
        }
    }
}
```

多维数组 DP

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
        return 0;
    }
    int n = prices.length;
    int[][] dp = new int[n][2];
    for (int i = 0; i < n; ++i) {
        if (i == 0) {
            dp[i][0] = 0;
            dp[i][1] = -prices[i];
        } else {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
    }
    return dp[n - 1][0];
}

// 空间优化
class Solution {
    public int maxProfit(int[] prices) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }
}
```

