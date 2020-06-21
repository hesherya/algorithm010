class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<Node> queue = new LinkedList<>();
        Node node;
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // 注意这里要用一个局部变量保存该层队列长度
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                node = queue.pollFirst();
                level.add(node.val);
                // 这里添加子节点会引起队列长度变化
                queue.addAll(node.children);
            }
            res.add(level);
        }
        return res;
    }
}

