class Solution {
    public int nthUglyNumber(int n) {
        // 1. 优先级队列
       PriorityQueue<Long> heap = new PriorityQueue<>();
       HashSet<Long> set = new HashSet<>();
       long[] primes = new long[] {2, 3, 5};
       // 基本因子先入栈和集合，集合用于排重
       for (long prime : primes) {
           heap.offer(prime);
           set.add(prime);
       }
       long num = 1;
       // 从第二个值开始迭代，第 n 个出堆的就是结果
       for (int i = 1; i < n; ++i) {
           num = heap.poll();
           // 根据每个出堆的最小值，分别乘以基本因子求出下一层的丑数
           for (int j = 0; j < 3; ++j) {
               // 排重并入堆
               if (!set.contains(num * primes[j])) {
                   set.add(num * primes[j]);
                   heap.offer(num * primes[j]);
               }
           }
       }
       return (int) num;
    }
}

