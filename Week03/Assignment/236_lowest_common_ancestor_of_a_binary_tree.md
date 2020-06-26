# 236. 二叉树的公共祖先 Lowest Common Ancestor of a Binary Tree

## Problem

[236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/) 

## 思路

参考 [Lowest Common Ancestor of a Binary Tree - LeetCode](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C%2B%2BJavaPythonRuby)

### 递归

	1. 空树或 p、q 就是根，那最大公共祖先就是根节点。
	2. 递归下探左右子树。
	3. 比较左右子树求得的 LCA 节点，其中一个结果为空，LCA 就是另外一个子树的 LCA（说明两个节点都在同一个棵子树上），两个子树的 LCA 都不空（说明两节点分布在左右子树中），LCA 就是 root。

## Code

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 终止条件：空树，p 或 q 就是根
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左空 -> 右
        // 右空 -> 左
        // 左右都不空 -> 根
        return left == null ? right : right == null ? left : root;
    }
}
```

#Programming/Algorithm/Essential
