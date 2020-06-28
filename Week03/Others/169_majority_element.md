# 169. 多数元素 Majority Element

## Problem

[169. 多数元素](https://leetcode-cn.com/problems/majority-element/) 


## 思路

### 散列表

	1. 先把数组中所有元素放入哈表中，累计数字出现次数。
	2. 累计次数的同时，如果碰到大于 n / 2 的，即是结果。
	3. 时间复杂度、空间复杂度都是 O(n)。

### 分治

	1. 数组只有一个元素，那这个元素就是众数。
	2. 将数组从中心位开始，分解为左右两个区间求众数，递归分解过程。
	3. 如果左右两边得出的众数相同，那这个值就是结果。
	4. 如果左右两边不同，需要在区间内进行计数，多的一方即为众数。

## Code

```java
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null) return 0;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int n = nums.length;
        for (int num : nums) {
            map.put(num, map.computeIfAbsent(num, k -> 0) + 1);
            if (map.get(num) > n / 2) {
                return num;
            }
        }
        return 0;
    }
}
```

```java
class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null) return 0;
        return majorityElement(nums, 0, nums.length - 1);
    }

    private int majorityElement(int[] nums, int l, int r) {
        // 终止条件：左右端相交
        if (l == r) return nums[l];
        // 中心位
        int p = l + (r - l) / 2;
        // 递归左右两个区间
        int left = majorityElement(nums, l, p);
        int right = majorityElement(nums, p + 1, r);
        // 两个区间得出结果相同，那就是最终结果
        if (left == right) return left;
        // 否则，分别计数，返回多的那个数
        int leftCount = countInRange(nums, left, l, r);
        int rightCount = countInRange(nums, right, l, r);
        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; ++i) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
}
```

#Programming/Algorithm/Essential

