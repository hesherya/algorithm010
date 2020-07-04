# 102. 二叉树的层序遍历 Binary Tree Level Order Traversal

## Problem

[102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) 

## 思路

### BFS

	1. 借助队列，按层遍历，并将非空子节点入队。
	2. 注意要在遍历层时，出队节点。
	3. 与 [429. N 叉树的层序遍历 N Array Tree Level Order Traversal](bear://x-callback-url/open-note?id=80D5DAB9-C0FB-4F8F-A22F-DFB0730B7839-594-0000342D7AE9B6BE)解法一致。

### DFS

根据递归深度 index，可以在每次下探时，将当前节点值存入 list 中。

## Code

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            // 遍历本层所有节点，入队其子节点
            for (int i = 0; i < size; ++i) {
                node = queue.pollFirst();
                level.add(node.val);
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            res.add(level);
        }
        return res;
    }
}
```

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(0, res, root);
        return res;
    }

    private void dfs(int index, List<List<Integer>> res, TreeNode root) {
        // 比较结果集大小与递归深度，需要做初始化工作，新建 list
        if (res.size() < index + 1) res.add(new ArrayList<>());
        res.get(index).add(root.val);
        if (root.left != null) dfs(index + 1, res, root.left);
        if (root.right != null) dfs(index + 1, res, root.right);
    }
}
```

#Programming/Algorithm/Essential

