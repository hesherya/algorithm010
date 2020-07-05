# 33. 搜索旋转排序数组 Search in Rotated Sorted Array

## Problem

[33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/) 

## 思路

### 二分查找

	1. 旋转数组至少有一半是有序，而另一半无序。
	2. 根据两个部分是否有序，分别处理。
	3. 再分别处理值在有序区间和无序区间的情况。

## Code

```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        int p;
        while (l <= r) {
            p = l + (r - l) / 2;
            if (nums[p] == target) return p;
            // 分别处理前半部分有序和后半部分有序
            if (nums[l] <= nums[p]) {
                // 分别处理目标值在有序前半段和无序后半段
                if (target >= nums[l] && target < nums[p]) {
                    r = p - 1;
                } else {
                    l = p + 1;
                }
            } else {
                if (target > nums[p] && target <= nums[r]) {
                    l = p + 1;
                } else {
                    r = p - 1;
                }
            }
        }
        return -1;
    }
}
```

#Programming/Algorithm/Essential

