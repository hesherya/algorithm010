# 127. 单词接龙 Word Ladder

## Problem

[127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/) 

## 思路

### BFS

通过从起始单词，每次改变一个字母，并判断其是否在词典中，不断扩散。扩散的层数就是转换长度（含起始层）。本体与 [433. 最小基因变化 Minimum Genetic Mutation](bear://x-callback-url/open-note?id=94D586A7-0931-4772-A30E-081D612F576B-594-000376DCB68E2E32)思路一致。

### 双向 BFS

当目标已知时，可以从收尾两端想中间夹逼扩散，以减小搜索范围，提高效率。使用两个 visited 散列表分别记录两端扩散的值，并在循环时每次取小的一方进行扩散。

## Code

BFS

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) return 0;
        if (beginWord.equals(endWord)) return 1;
        int level = 1;

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                char[] currArray = curr.toCharArray();
                for (int i = 0; i < currArray.length; ++i) {
                    char old = currArray[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (currArray[i] == c) continue;
                        currArray[i] = c;
                        String next = String.valueOf(currArray);
                        if (wordSet.contains(next)) {
                            if (next.equals(endWord)) return level + 1;
                            if (!visited.contains(next)) {
                                visited.add(next);
                                queue.offer(next);
                            }
                        }
                    }
                    currArray[i] = old;
                }
            }
            level++;
        }
        return 0;
    }
}
```

双向 BFS

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordList.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Set<String> visited = new HashSet<>();

        // 使用两个 visited 分别从两段记录访问状态，当相遇时停止
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);

        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int level = 1;

        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 遍历每层时，选择小的哈希表进行扩散可以减小范围
            if (beginVisited.size() > endVisited.size()) {
                Set<String> tmp = beginVisited;
                beginVisited = endVisited;
                endVisited = tmp;
            }

            // nextLevelVisited 用于临时记录当前层访问状态用于下层遍历
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                char[] charArray = word.toCharArray();
                for (int i = 0; i < charArray.length; ++i) {
                    char old = charArray[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (old == c) continue;
                        charArray[i] = c;
                        String next = String.valueOf(charArray);
                        if (wordSet.contains(next)) {
                            // 另一端扩散的结果中包含了这个值，那结果就在下一层
                            if (endVisited.contains(next)) return level + 1;
                            // 未访问过，标记为已访问
                            if (!visited.contains(next)) {
                                visited.add(next);
                                // 记录每层访问状态
                                nextLevelVisited.add(next);
                            }
                        }
                    }
                    charArray[i] = old;
                }
            }
            beginVisited = nextLevelVisited;
            level++;
        }
        return 0;
    }
}
```

#Programming/Algorithm/Essential

