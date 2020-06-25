# 104. 二叉树的最大深度 Maximum Depth Of Binary Tree

## Problem

[104. 二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) 

## 思路

### 递归

	1. 终止条件：节点为空，返回深度为 0.
	2. 逻辑：深度 + 1；
	3. 下探：左右儿子分别求最大深度。
	4. 结果：取左右儿子深度最大值，因为根节点没有计算，所以最后加一。
	5. 时间复杂度: 最差是退化为链表 O(n)，最好是平衡二叉树 O(log n)。

### 迭代 BFS

	1. 使用队列，按层遍历。
	2. 每层遍历时将出队节点的左右儿子入队。
	3. 遍历完当前层后深度加一。

### 迭代 DFS

	1. 使用两个栈，一个保存节点，一个保存深度值。
	2. 每次左右儿子入栈时，将深度值加一也入栈。
	3. 出栈时机一样，并对保存的最大深度与出栈的深度去最大值。这样同一层内不会叠加深度值，非常巧妙。
	4. 也可以使用同一个栈，在栈中保存节点及其深度，可以节省一个栈的额外空间占用。

## Code

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offerLast(root);
        TreeNode node;
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                node = queue.pollFirst();
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            depth++;
        }
        return depth;
    }
}
```

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> stack = new LinkedList<>();
        // 给深度也建一个栈
        Deque<Integer> depthStack = new LinkedList<>();
        stack.offerFirst(root);
        depthStack.offerFirst(1);
        TreeNode node;
        int depth = 0;
        int temp;
        while (!stack.isEmpty()) {
            node = stack.pollFirst();
            temp = depthStack.pollFirst();
            // 取最大深度
            depth = Math.max(temp, depth);
            // 左右儿子压栈时，深度加一也压栈
            // 由于同一层内深度一致，所以去最大值也不会多叠加
            if (node.right != null) {
                stack.offerFirst(node.right);
                depthStack.offerFirst(temp + 1);
            }
            if (node.left != null) {
                stack.offerFirst(node.left);
                depthStack.offerFirst(temp + 1);
            }
        }
        return depth;
    }
}
```

#Programming/Algorithm/Essential

