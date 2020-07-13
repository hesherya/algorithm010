# 🟩 剑指 Offer 10-1. 斐波那契数列 Fibonacci Number

## Problem

[剑指 Offer 10- I. 斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/) 

## 思路

### 动态规划 Bottom Up

	1. 自底向上，从 f0 迭代计算到 fn。
	2. 注意超过 int 上界时，会导致结果错误，根据提示要对 1000000007 取值。

### 动态规划：递归 + 缓存数组

	1. 利用一个数组缓存中间结果，自顶向下递归。
	2. 注意初始化 dp[0] 和 dp[1]。
	3. 因为要求 dp[n]，所以数组大小需要 n + 1。
	4. 循环 2 到 n 闭区间，最后结果就是 dp[n]。

### 矩阵求幂

参考[官方题解](https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode/)

### 数学公式

[image:C9A08C7F-0318-414B-8702-81809B71C64E-596-00001469ADDA9A71/d5e05b3f46910d4bf79d3b235e5a3d7803b63bce092c6c20a53c16d228e33113.png]

参考[官方题解](https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode/)

## Code

Bottom Up 最优解

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        int f0 = 0, f1 = 1, f2 = 1;
        for (int i = 2; i < n; ++i) {
            f0 = f1;
            f1 = f2;
            f2 = (f0 + f1) % 1000000007;
        }
        return f2;
    }
}
```

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if (dp[i] == 0) dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }
}
```

数学公式法，时间复杂度 O(1)。

```java
class Solution {
    public int fib(int N) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, N)/ Math.sqrt(5));
    }
}

作者：LeetCode
链接：https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```

#Programming/Algorithm/Essential

