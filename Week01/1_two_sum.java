// 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
// 因为 nums[0] + nums[1] = 2 + 7 = 9
// 所以返回 [0, 1]
// 
// Related Topics 数组 哈希表


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 1. 暴力法
//        for (int i = 0; i < nums.length - 1; ++i) {
//            for (int j = i + 1; j < nums.length; ++j) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[] {i, j};
//                }
//            }
//        }
//        return null;

        // 2. 两遍哈希表
//        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
//        for (int i = 0; i < nums.length; ++i) {
//            map.put(nums[i], i);
//        }
//        for (int i = 0; i < nums.length; ++i) {
//            int complement = target - nums[i];
//            if (map.containsKey(complement) && map.get(complement) != i) {
//                return new int[] {i, map.get(complement)};
//            }
//        }
//        return null;

        // 3. 一遍哈希表
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; ++i) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

