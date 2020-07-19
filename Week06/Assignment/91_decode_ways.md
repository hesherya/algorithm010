# 🟧 91. 解码方法 Decode Ways

## Problem

[91. 解码方法](https://leetcode-cn.com/problems/decode-ways/) 

## 思路

[参考题解](https://leetcode-cn.com/problems/decode-ways/solution/dong-tai-gui-hua-java-python-by-liweiwei1419/)。

1. 定义状态 dp[i] 为前缀子串前缀子串 s(0..i) 的解码方法数。
2. 状态转移方程：
	* `s[i] != '0' 时 dp[i] = dp[i - 1]`，如果后面不能组合解码，则解码数与前一位相同。
	* `10 <= s[i - 1..i] <= 26 时 dp[i] += dp[i - 2]`，10 到 26 之间可以组合解码，但需要注意 i - 2 需要 i 要大于 1，i 等于 1 时（即 s(2))，即使组合解码，也只能比 s(1) 多一种解码方法。

## Code

```java
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int len = s.length();
        // 定义状态 dp[i] 为前缀子串 s(i) 的解码数，所以 dp(len - 1) 就是 s 的解码数
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; ++i) {
            // 循环内非零值初始化为前一位解码数，如果不能组合解码，与前一位解码数相同
            if (s.charAt(i) != '0') dp[i] = dp[i - 1];
            // 算后两位字符对应数字
            int num = 10 * (s.charAt(i - 1) - '0') + (s.charAt(i) - '0');
            // 后两位可以组合解码
            if (num >= 10 && num <= 26) {
                // 第二位只能有一种情况
                if (i == 1) {
                    dp[i]++;
                } else {
                    // 非第二位，其后面两位可以单独解码，也可以组合解码
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len - 1];
    }
}
```

国际站最高票

```java
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
```

