// Ford-Fulkerson 算法的简单实现，适合 Java 初学者
import java.util.*;

public class NetworkFlow {
	// 使用邻接矩阵表示图
	private int[][] capacity; // 容量矩阵
	private int n; // 顶点数

	public NetworkFlow(int n) {
		this.n = n;
		capacity = new int[n][n];
	}

	// 添加一条从 u 到 v 的边，容量为 cap
	public void addEdge(int u, int v, int cap) {
		capacity[u][v] = cap;
	}

	// 寻找一条从 s 到 t 的增广路，返回路径上的最小残量
	private int bfs(int s, int t, int[] parent) {
		Arrays.fill(parent, -1);
		parent[s] = -2; // 标记源点
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{s, Integer.MAX_VALUE});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int u = cur[0];
			int flow = cur[1];
			for (int v = 0; v < n; v++) {
				if (parent[v] == -1 && capacity[u][v] > 0) {
					parent[v] = u;
					int new_flow = Math.min(flow, capacity[u][v]);
					if (v == t) {
						return new_flow;
					}
					queue.add(new int[]{v, new_flow});
				}
			}
		}
		return 0;
	}

	// Ford-Fulkerson 主函数，返回最大流
	public int maxFlow(int s, int t) {
		int flow = 0;
		int[] parent = new int[n];
		int new_flow;

		while ((new_flow = bfs(s, t, parent)) != 0) {
			flow += new_flow;
			int v = t;
			while (v != s) {
				int u = parent[v];
				capacity[u][v] -= new_flow;
				capacity[v][u] += new_flow; // 反向边
				v = u;
			}
		}
		return flow;
	}

	// 示例：主函数
	public static void main(String[] args) {
		// 创建一个有 4 个顶点的图
		NetworkFlow g = new NetworkFlow(4);
		g.addEdge(0, 1, 20);
		g.addEdge(1, 2, 20);
		g.addEdge(2, 3, 20);
		g.addEdge(0, 2, 10);
		g.addEdge(1, 3, 10);
/*
 图示 一个针对贪心的最典型的反例
        -> 1 -----\
    (20)   \       (10)
 0         \(20)       --> 3
  (10)       \>   /(20)
         ->  2
*/
		int s = 0; // 源点
		int t = 3; // 汇点
		System.out.println("最大流: " + g.maxFlow(s, t));
	}
}
