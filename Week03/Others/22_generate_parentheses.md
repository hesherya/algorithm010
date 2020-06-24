# 22. 括号生成 Generate Parentheses

## Problem

[22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/) 

## 思路

递归

	1. 终止条件：左括号及右括号数量到达 n，或者从 n 往下减到 0。
	2. 每层逻辑：在字符串右侧拼左括号或者右括号，需要两种递归调用。注意这里不能用 StringBuilder。
	3. 下探：即递归调用，参数中带上终止条件需要的左右括号数、最大数及结果集。


## Code

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        generate(0, 0, n, "", res);
        return res;
    }

    private void generate(int left, int right, int max, String s, List<String> res) {
        if (left == max && right == max) {
            res.add(s);
            return;
        }
        if (left < max) generate(left + 1, right, max, s + "(", res);
        if (left > right) generate(left, right + 1, max, s + ")", res);
    }
}
```

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, n, n, "");
        return res;
    }

    private void generate(List<String> res, int left, int right, String s) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) generate(res, left - 1, right, s + "(");
        if (left < right) generate(res, left, right - 1, s + ")");
    }
}
```

