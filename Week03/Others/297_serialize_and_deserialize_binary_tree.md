# 297. 二叉树的序列化与反序列化

## Problem

[297. 二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/) 

## 思路

	1. 序列化和反序列化都可以用递归思路实现。
	2. 设置一个终止符 TERMINATOR 标记序列化和反序列化结束。

## Code

```java
public class Codec {
    private static final String SPLITER = ",";
    // 定义一个终止符用于确定结束位置
    private static final String TERMINATOR = "x";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return doSerialize(root, new StringBuilder()).toString();
    }

    public StringBuilder doSerialize(TreeNode root, StringBuilder s) {
        // 以终止符结尾
        if (root == null) return s.append(TERMINATOR);
        // 前序遍历，根和左儿子后面接分隔符
        s.append(root.val).append(SPLITER);
        doSerialize(root.left, s).append(SPLITER);
        doSerialize(root.right, s);
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return doDeserialize(new LinkedList<>(Arrays.asList(data.split(SPLITER))));
    }

    private TreeNode doDeserialize(Deque<String> q) {
        String value = q.pollFirst();
        // 终止条件为碰到终止符
        if (TERMINATOR.equals(value)) return null;
        // 递归逻辑为创建 TreeNode
        TreeNode root = new TreeNode(Integer.valueOf(value));
        // 左右子树分别下探
        root.left = doDeserialize(q);
        root.right = doDeserialize(q);
        return root;
    }
}
```

#Programming/Algorithm/Essential
