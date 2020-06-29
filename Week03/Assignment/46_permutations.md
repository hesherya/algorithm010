# 46. 全排列 Permutations

## Problem

[46. 全排列](https://leetcode-cn.com/problems/permutations/) 

## 思路

回溯算法的典型应用。

	1. 递归终止条件：排列元素长度到达数组长度。
	2. 排列与组合的区别在于循环数组过程中，每次从 0 开始尝试所有数。而组合的起始位置会随深度增加而增加，以避免重复。
	3. 排列避免重复可以通过已经入栈的元素是否重复或者使用额外空间判断，比如数组、HashSet、位掩码，或者通过交换数组元素将已使用过和未使用的元素分别放在数组两端，节省空间并加速。
	4. 回溯时相应的要将元素出栈并将使用标识恢复。

## Code

```java
class Solution {
    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return res;
        dfs(nums, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }

    private void dfs(int[] nums, LinkedList<Integer> curr, boolean[] used) {
        if (curr.size() == nums.length) res.add(new LinkedList<>(curr));
        for (int i = 0; i < nums.length; ++i) {
            if (!used[i]) {
                used[i] = true;
                curr.addLast(nums[i]);
                dfs(nums, curr, used);
                used[i] = false;
                curr.removeLast();
            }
        }
    }
}
```

#Programming/Algorithm/Essential

