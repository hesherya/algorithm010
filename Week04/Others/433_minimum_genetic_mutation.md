# 433. 最小基因变化 Minimum Genetic Mutation

## Problem

[433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/) 

## 思路

### BSF

	1. 将初始基因放入队列，循环出队。
	2. 将出队的基因，按可选基因循环变异，并与目标基因比较。
	3. 如果变异的基因在基因库中并且未被访问过，就标记已访问状态并入队进入下次循环。
	4. 每遍历一层，基因变更次数加一，最终结果就是层数。

## Code

```java
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        // 用 HashSet 存基因库方便后续读取
        Set<String> bankSet = new HashSet<>(bank.length);
        for (String b : bank) bankSet.add(b);
        // 可选基因
        char[] charSet = new char[] {'A', 'C', 'G', 'T'};
        // 定义层数、访问状态及队列
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        // 初始基因入队
        queue.offer(start);
        visited.add(start);
        // 循环出队
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                // 已得到目标基因，遍历层数就是基因变异次数
                if (curr.equals(end)) return level;
                // 循环替换出队基因序列中的基因
                char[] currArray = curr.toCharArray();
                for (int i = 0; i < currArray.length; ++i) {
                    char old = currArray[i];
                    for (char c : charSet) {
                        currArray[i] = c;
                        String next = new String(currArray);
                        // 未访问并且在基因库中，记录状态并入队
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    // 回溯变化的基因位
                    currArray[i] = old;
                }
            }
            level++;
        }
        return -1;
    }
}
```

#Programming/Algorithm/Essential

