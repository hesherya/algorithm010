# 🟧 208. 实现 Trie （前缀树）Implement Trie Prefix Tree

## Problem

[208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/) 

## 思路

1. 构造 `TrieNode` 节点，包括一个状态字段标志字符串是否结束 `isEnd`，一个长度为 26 字母长度的 `TrieNode` 数组 `links`，实现 `put`、`get`、`containsKey` 和构造方法。
2. `get`、`put` 和 `containsKey` 方法都是基于字符与 ‘a'  的差值作为 links 的值存储。
3. `insert` 和 `search`、`startWith` 方法思路相似，都是遍历待查询字符串的每个字符，判断从根节点 root 开始，是否包含其每个字符，直到循环结束。`search` 与 `startWith` 的区别，仅仅在于最后一个 node 状态是否结束。

## Code

```java
class Trie {
    private boolean isEnd;
    private Trie[] next;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) {
                curr.next[n] = new Trie();
            }
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }
}
```

