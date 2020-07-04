# 22. 括号生成 Generate Parentheses

## Problem

[22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/) 

## 思路

### DFS

	1. 终止条件：左括号及右括号数量到达 n，或者从 n 往下减到 0。
	2. 每层逻辑：在字符串右侧拼左括号或者右括号。如果用 StringBuilder 拼接括号，需要回溯。
	3. 下探：即递归调用，参数中带上终止条件需要的左右括号数、最大数及结果集。

### BFS

参考[力扣](https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/)

	1. 需编写节点类，成员变量包括拼接字符串、左括号数量、右括号数量。
	2. 使用队列，按递归树层次遍历。
	3. 添加左右括号，相当于走左右子树两个分支，条件与 dfs 一样。

## Code

DFS：左右括号累加计数

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        // 左右括号从 0 计数
        dfs(res, n, 0, 0, "");
        return res;
    }

    private void dfs(List<String> res, int n, int left, int right, String s) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        // 左括号没用完，递归下探并拼接 s
        if (left < n) dfs(res, n, left + 1, right, s + "(");
        // 右括号比左括号少，递归下探并拼接 s
        if (right < left) dfs(res, n, left, right + 1, s + ")");
    }
}
```

DFS：左右括号从 n 递减

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        if (n == 0) return res;
        // 左右括号数量从 n 往下减
        dfs(res, n, n, "");
        return res;
    }

    private void dfs(List<String> res, int left, int right, String s) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        // 左括号没用完，递归下探
        if (left > 0) dfs(res, left - 1, right, s + "(");
        // 可用的右括号比左括号多，递归下探
        if (right > left) dfs(res, left, right - 1, s + ")");
    }
}
```

BFS：构造节点，借助队列遍历

```java
class Solution {
    /**
     * 构造节点，聚合结果字符串、左右括号数量
     */
    class Node {
        private String s;
        private int left;
        private int right;
        public Node(String s, int left, int right) {
            this.s = s;
            this.left = left;
            this.right = right;
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        // 借助队列遍历
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            // 左右括号数量都降为 0，说明括号添加完毕
            if (node.left == 0 && node.right == 0) res.add(node.s);
            // 分别入队添加左右括号节点的情况
            if (node.left > 0) queue.offer(new Node(node.s + "(", node.left - 1, node.right));
            if (node.right > node.left) queue.offer(new Node(node.s + ")", node.left, node.right - 1));
        }
        return res;
    }
}
```

#Programming/Algorithm/Essential

