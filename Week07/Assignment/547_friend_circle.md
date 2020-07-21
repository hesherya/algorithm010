# 🟧 547. 朋友圈 Friend Circles

## Problem

[547. 朋友圈](https://leetcode-cn.com/problems/friend-circles/) 

## 思路

### 并查集

1. (i, j) 为 1 的节点加入并查集，返回连通分量数即可。
2. find 方法可以考虑路径压缩优化。
3. union 方法考虑平衡性优化。

参考  [Union-Find 算法详解](https://leetcode-cn.com/problems/friend-circles/solution/union-find-suan-fa-xiang-jie-by-labuladong/) 。

### DFS

### BFS

## Code

```java
class Solution {
    class UnionFind {
        // 树的节点数
        private int[] size;
        // 父节点
        private int[] parent;
        // 连通分量
        private int count;

        public UnionFind(int n) {
            size = new int[n];
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; ++i) {
                // 初始化，父节点指向自己，重量为 1
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int x) {
            // 将 x 的父节点循环指向爷爷节点，最终将指向根节点
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            // 判断树重
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }
    }

    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count();
    }
}
```

