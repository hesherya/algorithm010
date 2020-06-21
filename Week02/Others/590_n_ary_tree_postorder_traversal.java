class Solution {
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<Node> stack = new LinkedList<Node>();
        Node node;
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            node = stack.pollFirst();
            res.offerFirst(node.val);
            for (Node child : node.children) {
                stack.offerFirst(child);
            }
        }
        return res;
    }
}

