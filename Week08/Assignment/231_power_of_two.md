# 🟩 231. 2 的幂 Power of Two

## Problem

[231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)

## 思路

参考官方题解 [2的幂](https://leetcode-cn.com/problems/power-of-two/solution/2de-mi-by-leetcode/)。

### 利用 `x & (-x)`

1.  2 的幂不可能小于等于 0，所以直接返回 false，负数由于符号位的存在，可能存在多个 1，计算结果会受影响。
2. 补码表示法中，`−x = ~x + 1` 也就是取反加一。
3. `x & (-x)` 会保留最后一位 1，并将其余位置清 0。
4. 2 的幂要求某一二进制位为 1 其余为 0，所以 `x & (-x)` 如果与 x 相同，说明只有一位是 1，就说明 n 就是 2 的幂。

### 利用 `x & (x - 1)`

`x & (x - 1)` 将清零最低位的 1，而如果结果等于 0，就说明只有一位 1，也就是 n 是 2 的幂。

## Code

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return n == (n & (-n));
    }
}
```

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return 0 == (n & (n - 1));
    }
}
```

循环 32 次，分别用掩码 1 左移与 n 比较，如果相等就说明是 2 的幂。

```java
public boolean isPowerOfTwo(int n) {
    if (n < 0) return false;
    for (int i = 0; i < 32; ++i) {
        if ((1 << i) == n) {
            return true;
        } else if ((1 << i) > n) {
            // 大于 n 即可停止
            return false;
        }
    }
    return false;
}
```

