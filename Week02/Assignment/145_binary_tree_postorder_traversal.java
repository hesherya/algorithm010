class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        // 结果集使用双端队列，可以从后往前插入。
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode node;
        // 边界条件
        if (root == null) return res;
        // 根节点先入栈
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            // 根节点先出，插在队尾
            node = stack.pollFirst();
            res.offerFirst(node.val);
            // 入栈顺序为先左后右，出栈就是先右后左
            // 倒插结果队列为 -> 左 -> 右 -> 根
            if (node.left != null) stack.offerFirst(node.left);
            if (node.right != null) stack.offerFirst(node.right);
        }
        return res;
    }
}

