# 121. 股票买卖的最佳时机 Best Time to Buy and Sell Stock

## Problem

[121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/) 

## 思路

### Brute Force

暴力循环求最大差值，时间复杂度 O(n^2)。

### 一次遍历

从前往后遍历过程中，记录最小价格，计算最大利润，都减去最小的价格即可，时间复杂度 O(n)。

### 一维 DP

* 定义状态：dp[i] 为到 i 日最大利润。
* 状态转移方程：`dp[i] = Math.max(dp[i - 1], prices[i] - minPrice)`。

思路可参考  [股票问题（Python3、C++）](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/gu-piao-wen-ti-python3-c-by-z1m/) 。

### 多维 DP，可团灭所有股票最大利润问题

思路可参考 [一个方法团灭 6 道股票问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/) ，列举所有状态，并升维 dp 数组。

* ‼️定义状态：`dp[i][K][0 or 1] (0 <= i <= n - 1, 1 <= k <=K)`  为手上所有钱
	* i 为天数
	* K 为最多交易次数
	* [0, 1] 是否持有股票
	* 总状态数：n * K * 2 种
	
```python
for 0 <= i < n:
    for 1 <= k <= K:
        for s in {0, 1}:
            dp[i][k][s] = max(buy, sell, rest)
```

* ‼️状态转移方程：

```java
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
              max(   选择 rest  ,             选择 sell      )

解释：今天我没有持有股票，有两种可能：
要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
              max(   选择 rest  ,           选择 buy         )

解释：今天我持有着股票，有两种可能：
要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
```

* Base cases：

```java
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity
```

* 对于本题 k = 0，可以省略。

```java
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], -prices[i])
```

## Code

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
        return 0;
    }
    int maxProfit = 0;
    for (int i = 0; i < prices.length - 1; ++i) {
        for (int j = i + 1; j < prices.length; ++j) {
            maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
        }
    }
    return maxProfit;
}
```

一维数组 DP

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
        return 0;
    }
    int minPrice = Integer.MAX_VALUE;
    int days = prices.length;
    int[] dp = new int[days];
    for (int i = 0; i < days; ++i) {
        minPrice = Math.min(minPrice, prices[i]);
        if (i == 0) {
            dp[i] = 0;
        } else {
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
    }
    return dp[days - 1];
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
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }
        }
        return dp[n - 1][0];
    }
}

// 空间优化
class Solution {
    public int maxProfit(int[] prices) {
        //Actually, we can save the space with only two variables.
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }
}
```

