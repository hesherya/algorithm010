# 455. 分发饼干 Assign Cookies

## Problem

[455. 分发饼干](https://leetcode-cn.com/problems/assign-cookies/) 

## 思路

### 贪心算法

	1. 先排序两个数组，从小到大。
	2. 遍历两个数组，寻找能满足孩子胃口的最小值，如果能满足，继续喂下个孩子。
	3. 无论是否满足，饼干都偶继续计数。

## Code

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++) {
            if (g[i] <= s[j]) i++;
        }
        return i;
    }
}
```

#Programming/Algorithm/Essential

