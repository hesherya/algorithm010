# 860. 柠檬水找零 Lemonade Change

## Problem

[860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/) 

## 思路

### 贪心算法

	1. 根据支付顺序遍历金额，只有 5、10、20 三种情况，分别处理。
	2. 记录 $5 和 $10 的数量。
	3. 支付 $5，$5 数量增加。
	4. 支付 $10，找 $5，增加 $10，不够找返回 false。
	5. 支付 $20，找 $10 + $5 或 3 张 $5，这里优先用前者，保留更多 $5 用于找零。
	6. 其他情况返回 false。
	7. 也可以先按支付金额先做增减，最后判断 $5 数量是否是负数。

## Code

官解

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
```

国际站最高票

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;
            else if (bill == 10) {
                five--;
                ten++;
            } else if (ten > 0) {
                ten--;
                five--;
            } else {
                five -= 3;
            }
            // 先减最后看 $5 是否是负数
            if (five < 0) return false;
        }
        return true;
    }
}
```

#Programming/Algorithm/Essential

