# 50. 快速幂 Pow(x, n)

## Problem

[50. Pow(x, n)](https://leetcode-cn.com/problems/powx-n/) 

## 思路

### 分治算法

	1. 将问题分解对半分解，power(x, n) 可以分解为 power(x, n / 2) 然后递归，最后将结果相乘。
	2. 考虑特殊情况，n 如果是奇数，结果相乘之后会少乘一个 x，所以要分别处理。
	3. 考虑特殊情况，n 如果是负数，相当于其对其倒数求幂。
	4. 终止条件，n 为 0 时，返回结果为 1。

## Code

```java
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        double res = myPow(x, n / 2);
        return n % 2 == 0 ? res * res : n < 0 ? res * res * (1 / x) : res * res * x;
    }
}
```

#Programming/Algorithm/Essential

