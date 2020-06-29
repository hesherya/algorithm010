# 47. 全排列 II Permutations II

## Problem

[47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/) 

## 思路

与 [46. 全排列 Permutations](bear://x-callback-url/open-note?id=FD410C65-8FF7-466E-B2F6-FE309DB145B8-594-0002021821FD86CE) 大体一致，只是增加了剪枝步骤。

	1. 用数组中当前元素与前一元素比较，如果相同，并且回溯过，就剪掉。
	2. 比较逻辑要求数组有序，所以事先要对数组排序。

参考解答[力扣](https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/)

[image:48AC65A0-481C-4B02-9EB1-0CE5F9875B30-594-00020541704A8BDF/DB4BAF82-2636-4E41-8DC7-CE5FE5116226.png]

## Code

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) return res;
        // 排序，方便剪枝
        Arrays.sort(nums);
        dfs(nums, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }

    private void dfs(int[] nums, LinkedList<Integer> curr, boolean[] used) {
        if (curr.size() == nums.length) res.add(new LinkedList<>(curr));
        for (int i = 0; i < nums.length; ++i) {
            // 剪枝，对于重复出现的数字，如果前一数字已经回溯过，就剪掉
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            if (!used[i]) {
                curr.add(nums[i]);
                used[i] = true;
                dfs(nums, curr, used);
                used[i] = false;
                curr.removeLast();
            }
        }
    }
}
```

#Programming/Algorithm/Essential

