package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 使用二维表存储图
 */
public class Graph {

	private ArrayList<String> vertexList; // 顶点集合
	private int[][] edges; // 存储图对应的邻接矩阵
	private int numOfEdges; // 表示边的数目
	private boolean[] isVisited; // 表示当前节点是否已经被访问过

	public static void main(String[] args) {
		String[] arr = {"A","B","C","D","E"};
		Graph graph = new Graph(arr.length);
		for (String s : arr) {
			graph.insertVertex(s);
		}
		// 添加节点
		graph.linkVertex(0,2,1);
		graph.linkVertex(0,1,1);
		graph.linkVertex(1,2,1);
		graph.linkVertex(1,3,1);
		graph.linkVertex(1,4,1);

		graph.showGraph();

		//graph.dfs();
		graph.bfs();
	}

	public Graph(int n){
		vertexList = new ArrayList<>(n);
		edges = new int[n][n];
		isVisited = new boolean[n];
	}

	// 添加顶点
	public void insertVertex(String vertex){
		vertexList.add(vertex);
	}

	// 添加边
	// v1 第几个节点，v2 要连接的节点的下标，weight 权值，0不连接，1连接
	public void linkVertex(int v1,int v2,int weight){
		// 由于是无向图，两边同时要修改
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		if(weight == 1){
			numOfEdges++;
		}
	}

	// 获取指定下标节点的第一个邻接节点的下标
	public int getFirstNeighbor(int index){
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] > 0){
				return i;
			}
		}
		return -1; // 如果没有找到直接返回-1
	}

	// 获取指定下标节点的邻接节点的下一个邻接节点
	public int getNextNeighbor(int index,int v1){
		for (int i = v1 + 1; i < vertexList.size(); i++) {
				if (edges[index][i] > 0){
					return i;
				}
		}
		return -1; // 如果没有找到直接返回-1
	}

	// 深度优先遍历
	// isVisited 节点是否被访问，i 第几个节点
	public void dfs(boolean[] isVisited,int i){
		// 打印当前节点
		System.out.print(vertexList.get(i)+" -> ");
		// 将当前节点标记为已访问
		isVisited[i] = true;
		int w = getFirstNeighbor(i); // 获取当前节点的第一个邻接节点
		while (w != -1){
			if(!isVisited[w]){ // 如果邻接节点未被访问,直接递归遍历
				dfs(isVisited,w);
			}
			// 如果已经访问就获取下一个节点
			w = getNextNeighbor(i,w);
		}
	}

	// 遍历所有节点进行dfs，因为有可能有的节点并不能到达最后，需要进行回溯
	public void dfs(){
		for (int i = 0; i < countVertex(); i++) {
			if(!isVisited[i]){
				dfs(isVisited,i);
			}
		}
	}

	// 广度优先遍历
	public void bfs(boolean[] isVisited,int i){
		int u; // 表示队列的头结点对应的下标
		int w; // 邻接节点w
		// 队列，用于记录节点访问的顺序
		LinkedList queue = new LinkedList();
		// 访问节点，输出对应的信息
		System.out.print(getVertex(i)+" -> ");
		// 标记为已访问
		isVisited[i] = true;
		// 将访问的节点加入队列
		queue.addLast(i);
		
		while (!queue.isEmpty()){
			// 获取队列的头结点下标
			u = (Integer) queue.removeFirst();
			// 获取u的第一个邻接节点的下标
			w = getFirstNeighbor(u);
			while (w != -1){
				// 判断邻接节点是否已经被访问过
				if(!isVisited[w]){
					System.out.print(getVertex(w)+" -> ");
					isVisited[w] = true;
					queue.addLast(w);
				}
				// 如果已经被访问过了，就找下一个邻接节点
				w = getNextNeighbor(u, w);
			}
		}
	}

	public void bfs(){
		for (int i = 0; i < countVertex(); i++) {
			if(!isVisited[i]){
				bfs(isVisited,i);
			}
		}
	}

	// 返回节点的个数
	public int countVertex(){
		return vertexList.size();
	}

	// 返回边的条数
	public int countEdge(){
		return numOfEdges;
	}

	// 返回指定下标对应的节点
	public String getVertex(int i){
		return vertexList.get(i);
	}

	// 获取边的权值
	public int getWeight(int v1,int v2){
		return edges[v1][v2];
	}

	// 显示矩阵
	public void showGraph(){
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
	}
}
