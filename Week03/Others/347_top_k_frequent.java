class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 构造 HashMap 记录数字及其个数
        HashMap<Integer, Integer> map = new HashMap<>(k);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 优先级队列，升序，即最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        // 把所有元素插入堆中，大于 K 个元素时把堆顶元素移除
        for (int n : map.keySet()) {
            heap.offer(n);
            if (heap.size() > k) heap.poll();
        }
        // 取 k 次最大堆的根
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            res[i] = heap.poll();
        }
        return res;
    }
}

