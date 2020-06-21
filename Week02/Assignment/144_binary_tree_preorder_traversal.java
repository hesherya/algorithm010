class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (root == null) return res;
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            res.add(node.val);
            if (node.right != null) {
                stack.offerFirst(node.right);
            }
            if (node.left != null) {
                stack.offerFirst(node.left);
            }
        }
        return res;
    }
}
