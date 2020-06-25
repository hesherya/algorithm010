# 111. 二叉树的最小深度 Minimus Depth Of Binary Tree

## Problem

[111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) 

## 思路

### 递归

终止条件情况很多，需要分别处理：

		1. 空树，返回 0。
		2. 叶子节点，返回 1。
		3. 只有一个子节点，取该子节点深度 + 1。
		4. 两个子节点均存在，取两个子树最小深度 + 1。
		5. 注意 [1,2] 这个测试用例，并非求所有节点中深度最小的节点，而是所有叶子节点中的最小深度。参考题解 [力扣](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/li-jie-zhe-dao-ti-de-jie-shu-tiao-jian-by-user7208/)。

### BFS 遍历 & DFS 遍历

利用栈保存节点及其深度，当节点为叶子节点时，去最小值。

## Code

```java
class Solution {
    public int minDepth(TreeNode root) {
        // 空树
        if (root == null) return 0;
        // 叶子节点深度为 1
        if (root.left == null && root.right == null) return 1;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 有左无右，取左儿子深度 + 当前节点
        if (root.left != null && root.right == null) return left + 1;
        // 有右无左，取右儿子深度 + 当前节点
        if (root.left == null && root.right != null) return right + 1; 
        // 两个节点都有，取最小值 + 当前节点
        return Math.min(left, right) + 1;
    }
}
```

#Programming/Algorithm

