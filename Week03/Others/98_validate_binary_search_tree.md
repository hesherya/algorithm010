# 98. 验证二叉树 Validate BST

## Problem

[98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/) 

## 思路

### 按 BST 性质递归

	1. 节点为空返回 true。
	2. 分别验证上届和下届。
	3. 递归下探时，也是验证递归结果。
	4. 注意左子树只需验证上届，右子树验证下届。
	5. 所有验证都通过最后返回 true。
	6. 容易出错的点：不传递上届下届，只验证节点及其左右子节点。

### 中序遍历按升序排列验证

	1. 使用栈，迭代方式进行中序遍历。
	2. 保存上一节点值与当前节点值比较即可。

## Code

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
            return isValid(root, null, null);
    }

    private boolean isValid(TreeNode node, Integer lower, Integer upper) {
        // 只需要判断节点是否为空
        if (node == null) return true;
        int val = node.val;
        // 下届非空时，需保证节点大于下届
        if (lower != null && val <= lower) return false;
        // 上届非空时，需保证节点小于上届
        if (upper != null && val >= upper) return false;
        // 验证左子树，保证上届条件
        if (!isValid(node.left, lower, val)) return false;
        // 验证右子树，保证下届条件
        if (!isValid(node.right, val, upper)) return false;
        return true;
    }
}
```

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 整形会有测试用例过不了
        long min = -Long.MIN_VALUE;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.offerFirst(node);
                node = node.left;
            }
            node = stack.pollFirst();
            // 比较上一节点值 min
            if (node.val <= min) return false;
            min = node.val;
            node = node.right;
        }
        return true;
    }
}
```

#Programming/Algorithm/Essential

