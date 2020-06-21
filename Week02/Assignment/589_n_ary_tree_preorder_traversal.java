class Solution {
    public List<Integer> preorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<Node> stack = new LinkedList<>();
        stack.offerFirst(root);
        Node node;
        while (!stack.isEmpty()) {
            node = stack.pollFirst();
            res.offerLast(node.val);
            for (int i = node.children.size(); i > 0; --i) {
                stack.offerFirst(node.children.get(i -));
            }
        }
        return res;
    }
}

