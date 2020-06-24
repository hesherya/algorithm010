# 学习笔记

## 递归 Recursion

递归其实也是一种循环，从汇编角度来看，都是反复调用一段代码，递归和循环本质上没有明显的边界。

现实中可类比《盗梦空间》，主角携带自己的东西，不断下探到下层梦境，最后再向上返回。每层空间的东西互不影响，其实是一份拷贝。

### 递归代码模板

```java
public void recur(int level, int param) {
  // terminator
  if (level > MAX_LEVEL) {
    // process result
    return;
  }
  // process current logic
  process(level, param);
  // drill down
  recur(level: level + 1, newParam);
  // restore current status
}
```

### 思维要点

	1. 不要人肉递归，这是*最大误区*。
	2. 找到最近最简方法，将其拆解为可重复解决的问题，即找*重复子问题*。
	3. 数学归纳法的思维。

