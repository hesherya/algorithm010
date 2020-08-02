# 🟧 146. LRU 缓存机制 LRU Cache

## Problem

[146. LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/) 

## 思路

### 自行实现双向链表

1. 通过 HashMap 做缓存，实现存取 O(1) 。
2. 使用双向链表完成 LRU 功能。
3. 新建内部双向链表节点，节点包括键值及前驱后继指针。
4. get 方法，不存在时返回 -1，存在时需要将节点移动到链表头。
5. put 方法。
	* 如果 key 已存在，移动节点到链表头并更新 value。
	* 如果 key 不存在，新建链表节点并添加到缓存 map。
	* 添加节点成功后，要判断缓存容量，如果大于容量需要删除尾节点。

### 利用 LinkedHashMap

直接扩展 LinkedHashMap 并重写 removeEldestEntry 方法，方法内容返回容量是否超出。

## Code

```java
class LRUCache {

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    DLinkedNode head;
    DLinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);
            addToHead(newNode);
            size++;
            cache.put(key, newNode);
            if (size > capacity) {
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
```

```java
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        super.put(key, value);
    }
   
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
        return size() > capacity;
    }
}
```

