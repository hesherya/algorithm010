# 🟩 191. 位 1 的个数 Number of 1 Bits

## Problem

[191. 位1的个数](https://leetcode-cn.com/problems/number-of-1-bits/) 

## 思路

### 转字符串统计

1. 转为二进制字符串，用 Integer 的 toBinaryString 方法。
2. 遍历字符串的每个字符，统计字符 1 的个数。

### 掩码位运算

1. 使用位掩码循环 32 次。
2. 可选择左移掩码或者右移数字 n。
3. n 与掩码做位于操作，累计结果为 1 的次数即可。

### 最低位 1 清零位运算

利用 `n & (n - 1)` 循环清零最低位的 1，并累计次数就得到了 1 的个数。

## Code

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        String num = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < num.length(); ++i) {
            if (num.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
```

```java
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; ++i) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}
```

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);   
        }
        return count;
    }
}
```

