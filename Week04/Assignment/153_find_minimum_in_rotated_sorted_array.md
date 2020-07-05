# 153. 寻找旋转排序数组中的最小值 Find Minimum in Rotated Sorted Array

## Problem

[153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) 

## 思路

### 二分查找

	1. 两个终止条件，判断中点前后位置是否出现逆序，如果出现说明是反转点，取最小值。
	2. 其余情况，判断中点值与起始值 nums[0] 的大小关系，就可以知道哪一半是有序的，相应的更新左右边界即可。
	3. 注意判断原数组是否有序。
	4. 注意判断原数组是否没有元素或者只有一个元素。

## Code

```java
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        int l = 0, r = nums.length - 1, p = 0;
        // 有序的情况
        if (nums[r] > nums[l]) return nums[l];
        while (l <= r) {
            p = l + (r - l) / 2;
            // 两个终止条件，判断中点前后是否是旋转点
            if (nums[p] > nums[p + 1]) return nums[p + 1];
            if (nums[p] < nums[p - 1]) return nums[p];
            // 其余情况
            if (nums[p] > nums[0]) {
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
        return -1;
    }
}
```

极简版，思路参考[力扣](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-cha-zhao-wei-shi-yao-zuo-you-bu-dui-cheng-z/)。

```java
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int p = l + ((r - l) >> 1);
            if (nums[p] > nums[r]) {
                l = p + 1;
            } else {
                r = p;
            }
        }
        return nums[l];
    }
}
```

#Programming/Algorithm/Essential

