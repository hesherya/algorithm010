# 优先级队列 Priority Queue
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
