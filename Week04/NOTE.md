# 学习笔记

## 深度优先搜索 DFS

> *深度优先搜索算法*（英语：Depth-First-Search，DFS）是一种用于遍历或搜索或的算法 。这个算法会尽可能深的搜索树的分支。当节点v的所在边都己被探寻过，搜索将回溯到发现节点v的那条边的起始节点。这一过程一直进行到已发现从源节点可达的所有节点为止。如果还存在未被发现的节点，则选择其中一个作为源节点并重复以上过程，整个进程反复进行直到所有节点都被访问为止。 这种算法不会根据图的结构等信息调整执行策略。
> 
> 深度优先搜索是图论中的经典算法，利用深度优先搜索算法可以产生目标图的 [拓扑排序](https://zh.wikipedia.org/wiki/%E6%8B%93%E6%89%91%E6%8E%92%E5%BA%8F) 表 ，利用拓扑排序表可以方便的解决很多相关的 [图论](https://zh.wikipedia.org/wiki/%E5%9B%BE%E8%AE%BA) 问题，如无权最长路径问题等等。
> 
> 因发明“深度优先搜索算法”， [约翰·霍普克洛夫特](https://zh.wikipedia.org/wiki/%E7%B4%84%E7%BF%B0%C2%B7%E9%9C%8D%E6%99%AE%E5%85%8B%E6%B4%9B%E5%A4%AB%E7%89%B9) 与 [罗伯特·塔扬](https://zh.wikipedia.org/wiki/%E7%BD%97%E4%BC%AF%E7%89%B9%C2%B7%E5%A1%94%E6%89%AC) 在 1986 年共同获得计算机领域的最高奖：图灵奖 。  
> 
> 节选自 [深度优先搜索 - 维基百科，自由的百科全书](https://zh.wikipedia.org/wiki/%E6%B7%B1%E5%BA%A6%E4%BC%98%E5%85%88%E6%90%9C%E7%B4%A2)

## DFS 代码模板

```python
visited = set() 

def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 

	visited.add(node) 

	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```

```python
def DFS(self, tree): 

	if tree.root is None: 
		return [] 

	visited, stack = [], [tree.root]

	while stack: 
		node = stack.pop() 
		visited.add(node)

		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 

	# other processing work 
	...
```


## 广度优先搜索 BFS

> *广度优先搜索算法*（英语：Breadth-First Search，缩写为 BFS），又译作*宽度优先搜索*，或*横向优先搜索*，是一种图形搜索算法 。简单的说，BFS 是从根节点开始，沿着树的宽度遍历树的节点。如果所有节点均被访问，则算法中止。广度优先搜索的实现一般采用 open-closed 表。
> 
> 节选自 [广度优先搜索 - 维基百科，自由的百科全书](https://zh.wikipedia.org/wiki/%E5%B9%BF%E5%BA%A6%E4%BC%98%E5%85%88%E6%90%9C%E7%B4%A2)

## BFS 代码模板

```python
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 

	while queue: 
		node = queue.pop() 
		visited.add(node)

		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)

	# other processing work 
	...
```

