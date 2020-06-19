//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();
        // 1. 递归
//        this.helper(root, res);
//        return res;

        // 2. 迭代，借助栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        // 注意条件为或
        while (curr != null || !stack.isEmpty()) {
            // 循环下探左儿子并入栈
            while (curr != null) {
                stack.offerFirst(curr);
                curr = curr.left;
            }
            // 出栈并加入结果列表，左儿子就排在了最前面
            curr = stack.pollFirst();
            res.add(curr.val);
            // 最后访问右儿子，在下次迭代没有左儿子时，该节点值会出栈进入结果列表
            curr = curr.right;
        }
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root.left != null) helper(root.left, res);
        res.add(root.val);
        if (root.right != null) helper(root.right, res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

