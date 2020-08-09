# 🟥 188. 买卖股票的最佳司机 IV Best Time to Buy and Sell Stock IV

## Problem

[188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/) 

## 思路

### 多维 DP，可团灭所有股票最大利润问题

思路可参考 [一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/) ，列举所有状态，并升维 dp 数组。

```java
dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
```

P.S. ‼️需要注意有一个 test case，k 值很大，会导致超时。考虑 n 天交易，最大次数为 n / 2。如果 k 大于 n / 2，相当于 k 没有限制交易次数，退化为 [121. 股票买卖的最佳时机 Best Time to Buy and Sell Stock](bear://x-callback-url/open-note?id=E1CA8184-BFD9-4484-BB57-3967E50CAB74-582-0001365556183E8D)，可通过该 case。

## Code

```java
public int maxProfit(int k, int[] prices) {
    if (prices == null || prices.length == 0) {
        return 0;
    }
    int n = prices.length;
    // k 超过最大可交易次数，相当于没有限制交易次数
    if (k > n / 2) {
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
    int[][][] dp = new int[n][k + 1][2];
    for (int i = 0; i < n; ++i) {
        for (int j = 1; j <= k; ++j) {
            if (i == 0) {
                dp[i][j][0] = 0;
                dp[i][j][1] = -prices[i];
            } else {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
    }
    return dp[n - 1][k][0];
}
```

