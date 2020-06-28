# 78. 子集 Subsets

## Problem

[78. 子集](https://leetcode-cn.com/problems/subsets/) 

## 思路

### 回溯

	1. 与 [77. 组合 Combinations](bear://x-callback-url/open-note?id=ECF4AA59-27A9-4056-8198-628B8BB70F0F-594-0001E73335ABAFED) 类似，是一个子结果集长度为 1-n 的组合，或者是组合的更一般情况。
	2. 因为是用数组中元素作为结果集中的元素，所以数组要递归传递。
	3. 回溯函数的的使用与组合完全一样，都是将子集合中最后一个元素移除。

## Code

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    int n, k;

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        // 子集中元素个数是可以是 0 - n 个
        for (k = 0; k <= n; ++k) {
            backtrack(0, new LinkedList<>(), nums);
        }
        return res;
    }

    private void backtrack(int first, LinkedList<Integer> curr, int[] nums) {
        if (curr.size() == k) res.add(new LinkedList<>(curr));
        // 注意 i 为数组索引，不要越界
        for (int i = first; i < n; ++i) {
            curr.add(nums[i]);
            backtrack(i + 1, curr, nums);
            curr.removeLast();
        }
    }
}
```

#Programming/Algorithm/Essential

