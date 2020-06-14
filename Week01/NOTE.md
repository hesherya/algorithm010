# 学习笔记

## 7 种常见的时间复杂度 Big O Notation
	* O(1): Constant Complexity 常数时间复杂度
	* O(log n): Logarithmic Complexity 对数复杂度
	* O(n): Linear Complexity 线性时间复杂度
	* O(n^2): N square Complexity 平方
	* O(n^3): N square Complexity 立方
	* O(2^n): Exponential Growth 指数
	* O(n!): Factorial 阶乘 

## 要点
    1. 不考虑前面的系数。
    2. 计算算法的时间复杂度，要看代码或函数根据 n 的不同情况会运行多少次。
    3. 养成习惯，写完程序后下意识的分析时间复杂度和空间复杂度。
    4. 时间复杂度过高，对公司机器资源损耗巨大，反过来则是替公司节约成本。
    5. 示例：自然数累加求和，for 循环累加，时间复杂度 O(n)。高斯求和公式 n * (n + 1) / 2 降为 O(1)。
    6. 解题四件套：
        1. Clarification ，沟通并弄懂题目意思。
        2. Possible Solutions，想所有解题方法，比较时间和空间复杂度。
        3. Coding，找最优时间复杂度的解法，其次找空间复杂度最小的。
        4. Test Cases，写代码及测试结果。
    7. 递归的分析方法，画出状态递归树。
    8. 示例：用递归法求 Fibonacci 数列第 n 项，时间复杂度是 O(k^n)，千万不要用递归的写法，可以将重复计算的中间结果缓存或用循环来实现，可降低时间复杂度到 O(n)。

## Master Theory 主定理
任何分支和递归的程序都可以用主定理求解其时间复杂度。

记住四种递归常用算法的时间复杂度：

    1. 二分查找 O(log n)
    2. 二叉树遍历 O(n)
    3. 最佳排序矩阵搜索(已排好序的二维矩阵) O(n)
    4. 合并排序 O(n * log n)

简化思考方式：二叉树遍历，每个节点访问一次，所以时间复杂度为 O(n)。


## 思考题
    * 二叉树遍历，前中后序时间复杂度是多少？每个节点仅访问一次，所以是 O(n)。
    * 图的遍历，时间复杂度是多少？同上，每个节点仅访问一次，也是 O(n)。
    * 搜索算法，DFS（深度优先）、BFS（广度优先）时间复杂度是多少？同上，每个节点仅访问一次，也是 O(n)。
    * 二分查找，时间复杂度是多少？每次迭代只查找一半，所以是 O(log n)。


## 空间复杂度
    1. 如果使用了数组，一维数组长度就是空间复杂度，二维就是 n^2，多维同理。
    2. 如果使用了递归，递归的最大深度就是其空间复杂度（递归树深度）
    
   参考 [力扣爬楼梯题目官方 Solution](https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode/)  。


## 对数及对数复杂度
> 如果 x 的 y 次方等于 n（x > 0，且 x 不等 于 1），那么数 y 叫做以 x 为底 n 的对数（logarithm）。 记作 logxN = y。其中，x 叫做对数的底数。

    1. 底数为 10 时，写为 lg。
    2. 底数为 e 时，称为自然对数写为 ln,这个在高等数学中用的很多。
    3. 底数为 2 时，主要用在计算机中，写为 log，也就是不写底数。

下面例子就是对数复杂度：
```java
for (int i = 2; i < n; i *= 2) {
    System.out.println(i);
}
```

此外，二分查找也是对数复杂度。

## 队列 Queue
### 特点
先入先出 FIFO。

### 时间复杂度

    * 添加、删除：O(1)
    * 查询：O(n)，无序，只能遍历

### 源码分析
Queue 接口扩展了 Collection 接口，是集合框架中的队列抽象。

主要方法：

	* boolean add(E e)：添加元素，如果没控件会抛 IllegalStateException 异常。
	* boolean offer(E e)：相比 add 插入失败时抛出异常，这个方法会返回 fail。
	* E remove()：获取并删除队列头部元素，队列为空时抛出 NoSuchElementException 异常。
	* E poll()：获取并删除队列头部元素，队列为空时返回 null 而非抛异常。
	* E element()：获取头部元素但不删除，队列为空时抛出 NoSuchElementException 异常。
	* E peek()：获取头部元素但不删除，队列为空时返回 null 而非抛异常。

## 优先级队列 Priority Queue
类比 VIP 可以按优先级出队。

底层具体实现的数据结构较为多样的复杂：
	* heap
	* bst
	* treap

```java
java.lang.Object
	java.util.AbstractCollection<E>
		java.util.AbstractQueue<E>
			java.util.PriorityQueue<E>
```

### 时间复杂度
* 添加：O(1)，Java 实现为 O(log n)
* 删除：O(log n)，按优先级取出

### 源码分析
扩展了 AbstractQueue 并实现了 Queue 接口，基于 priority heap 实现。内部方法并非同步，并发情况下应该使用 `ConcurrentBlockingQueue `。

主要方法：

	* PriorityQueue(int initialCapacity, Comparator<? super E> comparator): 构造方法可以初始化初始队列容量及比较器。
	* boolean add(E e)：直接调用了 offer(e)。
	* boolean offer(E e)

```java
// 内部是一个数组
transient Object[] queue;

public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
	  // 增加修改次数
    modCount++;
    int i = size;
    // 容量达到 queue 数组容量时进行扩容
    if (i >= queue.length)
        // 传入参数为扩容最小值，即当前队列长度加 1
        grow(i + 1);
    siftUp(i, e);
    // 记录队列长度
    size = i + 1;
    return true;
}

private void grow(int minCapacity) {
    int oldCapacity = queue.length;
    // Double size if small; else grow by 50%
    // 旧容量小于 64，新容量翻倍并加 2，否则增加 50%，此时扩容频率会相对较高
    int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                     (oldCapacity + 2) :
                                     (oldCapacity >> 1));
    // overflow-conscious code
    // 快溢出时
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        // 最小长度大于最大数组长度时，取最大整数值，防止溢出
        newCapacity = hugeCapacity(minCapacity);
    // 拷贝数组
    queue = Arrays.copyOf(queue, newCapacity);
}

// 最大数组长度为整数最大值 - 8，即 2147483639
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

private void siftUp(int k, E x) {
    // 分别处理 comparator 和 comparable 来加速处理
    if (comparator != null)
        siftUpUsingComparator(k, x, queue, comparator);
    else
        siftUpComparable(k, x, queue);
}

private static <T> void siftUpUsingComparator(
    // k 为数组长度，x 为要添加的元素，es 为队列内部数组
    int k, T x, Object[] es, Comparator<? super T> cmp) {
    while (k > 0) {
        // >>> 无符号右移，忽略符号位，空位都以 0 补齐
        // 右移一位相当于除以 2，定义为父节点位置
        int parent = (k - 1) >>> 1;
        Object e = es[parent];
        // 确定优先级，
        if (cmp.compare(x, (T) e) >= 0)
            break;
        es[k] = e;
        k = parent;
    }
    es[k] = x;
}

private static <T> void siftUpComparable(int k, T x, Object[] es) {
    // k 为数组长度，x 为要添加的元素，es 为队列内部数组
    Comparable<? super T> key = (Comparable<? super T>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = es[parent];
        if (key.compareTo((T) e) >= 0)
            break;
        es[k] = e;
        k = parent;
    }
    es[k] = key;
}
```

入队操作，重点是 `siftUpComparable` 和 `siftUpUsingComparator` 算法基本一致，区别只是使用自定义比较器还是元素自身的比较方法。细节涉及堆排序，后续再做分析。

	* remove()，调用 poll()，返回 null 时抛出 `NoSuchElementException` 异常。
	* poll()，跟 offer 类似，重点方法在 `siftDownComparable` 和 `siftDownUsingComparator`。
	* element()，调用 peek()，返回 null 时抛出 `NoSuchElementException` 异常。
	* peek()，直接返回 queue[0]。


## 改写 Deque 示例代码方法
```java
public static void main(String[] args) {
    Deque<String> deque = new LinkedList<>();

    deque.offerFirst("a");
    deque.offerFirst("b");
    deque.offerFirst("c");
    System.out.println(deque);

    String str = deque.peekFirst();
    System.out.println(str);
    System.out.println(deque);

    while (deque.size() > 0) {
        System.out.println(deque.pollFirst());
    }
    System.out.println(deque);
}
```
