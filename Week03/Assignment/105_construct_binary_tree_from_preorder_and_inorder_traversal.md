# 105. 从前序与中序遍历序列构造二叉树

## Problem

[105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) 

## 思路

### 递归

	1. 从前序与中序遍历的定义入手，根节点可以通过前序遍历的第一个节点确定。
	2. 由于节点值不重复，可以通过根的值确定跟在中序遍历中的位置，这一步可以使用散列表以空间换时间，加速查找。
	3. 中序遍历中，根节点左侧就是其左子树所有节点，右侧就是其右子树所有节点。分别递归左右子树即可构造出整棵树。
	4. 递归终止条件需要界定左右子树在前序遍历序列中的左右边界，这就需要通过中序遍历中，根节点到左右子树边界的差值（差值与前序中所有子树边界差值一致）确定。而一旦右边界索引小于左边界索引，递归终止。

## Code

```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        // 通过 map 存储中序数组用于快速定位 root 索引
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight,
                               int inLeft, int inRight, Map<Integer, Integer> map) {
        // 终止条件，前序和中序左右边界不构成区间
        if (preLeft > preRight || inLeft > inRight) return null;
        // 定位 root，前序遍历左边界一定是 root
        int rootVal = preorder[preLeft];
        // 通过 root 查询其中中序遍历中的位置
        int pIndex = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树，前序遍历中左右子树的边界条件需要根据中序遍历中根节点位置确定
        // 中序和前序遍历，左右子树的个数是一致的
        root.left = buildTree(preorder, inorder,
                preLeft + 1, pIndex - inLeft + preLeft,
                inLeft, pIndex - 1, map);
        root.right = buildTree(preorder, inorder,
                pIndex - inLeft + preLeft + 1, preRight,
                pIndex + 1, inRight, map);
        return root;
    }
}
```

#Programming/Algorithm/Essential

