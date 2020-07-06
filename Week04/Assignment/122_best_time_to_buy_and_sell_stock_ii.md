# 122. 买卖股票的最佳时机 II Best Time to Buy and Sell Stock II

## Problem

[122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/) 

## 思路

### 贪心算法

遍历价格数组，计算当天与前一天的差值，如果大于零就累加起来。

## Code

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

#Programming/Algorithm/Essential

