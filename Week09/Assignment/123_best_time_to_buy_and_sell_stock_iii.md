# 🟥 123. 买卖股票的最佳时机 III Best Time to Buy and Sell Stock III

## Problem

[123. 买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/) 

## 思路

### 多维 DP，可团灭所有股票最大利润问题

思路可参考 [一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/) ，列举所有状态，并升维 dp 数组。

由于有次数限制，每次买入需要减掉一次交易次数。

```java
dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
```


## Code

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
        return 0;
    }
    int n = prices.length;
    int count = 2;
    int[][][] dp = new int[n][count + 1][2];
    for (int i = 0; i < n; ++i) {
        for (int k = 1; k <= count; ++k) {
            if (i == 0) {
                dp[i][k][0] = 0;
                dp[i][k][1] = -prices[i];
            } else {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
    }
    return dp[n - 1][count][0];
}
```

