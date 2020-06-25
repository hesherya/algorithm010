# 226. 反转二叉树 Invert Binary Tree

## Problem

[226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/) 

## 思路

### 递归

	1. 边界条件为 root == null。
	2. 逻辑为左右节点交换。
	3. 下探由于返回值为当前节点，所以可以先得出反转后的子节点，然后再交换。节省临时变量。

### 迭代

	1. 类似 BFT 层序遍历，借助队列实现。
	2. 每层节点出队后，交换其左右儿子。
	3. 对于其左右儿子，非空就入队，等待下次迭代交换节点。

## Code

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
```

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        // 与 BFT 层序遍历类似，一层一层翻转左右节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        TreeNode node, temp;
        while (!queue.isEmpty()) {
            node = queue.pollFirst();
            // 交换左右儿子
            temp = node.left;
            node.left = node.right;
            node.right = temp;
            // 非空节点入队
            if (node.left != null) queue.offerLast(node.left);
            if (node.right != null) queue.offerLast(node.right);
        }
        return root;
    }
}
```

#Programming/Algorithm/Essential
