package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * ʹ�ö�ά��洢ͼ
 */
public class Graph {

	private ArrayList<String> vertexList; // ���㼯��
	private int[][] edges; // �洢ͼ��Ӧ���ڽӾ���
	private int numOfEdges; // ��ʾ�ߵ���Ŀ
	private boolean[] isVisited; // ��ʾ��ǰ�ڵ��Ƿ��Ѿ������ʹ�

	public static void main(String[] args) {
		String[] arr = {"A","B","C","D","E"};
		Graph graph = new Graph(arr.length);
		for (String s : arr) {
			graph.insertVertex(s);
		}
		// ��ӽڵ�
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

	// ��Ӷ���
	public void insertVertex(String vertex){
		vertexList.add(vertex);
	}

	// ��ӱ�
	// v1 �ڼ����ڵ㣬v2 Ҫ���ӵĽڵ���±꣬weight Ȩֵ��0�����ӣ�1����
	public void linkVertex(int v1,int v2,int weight){
		// ����������ͼ������ͬʱҪ�޸�
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		if(weight == 1){
			numOfEdges++;
		}
	}

	// ��ȡָ���±�ڵ�ĵ�һ���ڽӽڵ���±�
	public int getFirstNeighbor(int index){
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] > 0){
				return i;
			}
		}
		return -1; // ���û���ҵ�ֱ�ӷ���-1
	}

	// ��ȡָ���±�ڵ���ڽӽڵ����һ���ڽӽڵ�
	public int getNextNeighbor(int index,int v1){
		for (int i = v1 + 1; i < vertexList.size(); i++) {
				if (edges[index][i] > 0){
					return i;
				}
		}
		return -1; // ���û���ҵ�ֱ�ӷ���-1
	}

	// ������ȱ���
	// isVisited �ڵ��Ƿ񱻷��ʣ�i �ڼ����ڵ�
	public void dfs(boolean[] isVisited,int i){
		// ��ӡ��ǰ�ڵ�
		System.out.print(vertexList.get(i)+" -> ");
		// ����ǰ�ڵ���Ϊ�ѷ���
		isVisited[i] = true;
		int w = getFirstNeighbor(i); // ��ȡ��ǰ�ڵ�ĵ�һ���ڽӽڵ�
		while (w != -1){
			if(!isVisited[w]){ // ����ڽӽڵ�δ������,ֱ�ӵݹ����
				dfs(isVisited,w);
			}
			// ����Ѿ����ʾͻ�ȡ��һ���ڵ�
			w = getNextNeighbor(i,w);
		}
	}

	// �������нڵ����dfs����Ϊ�п����еĽڵ㲢���ܵ��������Ҫ���л���
	public void dfs(){
		for (int i = 0; i < countVertex(); i++) {
			if(!isVisited[i]){
				dfs(isVisited,i);
			}
		}
	}

	// ������ȱ���
	public void bfs(boolean[] isVisited,int i){
		int u; // ��ʾ���е�ͷ����Ӧ���±�
		int w; // �ڽӽڵ�w
		// ���У����ڼ�¼�ڵ���ʵ�˳��
		LinkedList queue = new LinkedList();
		// ���ʽڵ㣬�����Ӧ����Ϣ
		System.out.print(getVertex(i)+" -> ");
		// ���Ϊ�ѷ���
		isVisited[i] = true;
		// �����ʵĽڵ�������
		queue.addLast(i);
		
		while (!queue.isEmpty()){
			// ��ȡ���е�ͷ����±�
			u = (Integer) queue.removeFirst();
			// ��ȡu�ĵ�һ���ڽӽڵ���±�
			w = getFirstNeighbor(u);
			while (w != -1){
				// �ж��ڽӽڵ��Ƿ��Ѿ������ʹ�
				if(!isVisited[w]){
					System.out.print(getVertex(w)+" -> ");
					isVisited[w] = true;
					queue.addLast(w);
				}
				// ����Ѿ������ʹ��ˣ�������һ���ڽӽڵ�
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

	// ���ؽڵ�ĸ���
	public int countVertex(){
		return vertexList.size();
	}

	// ���رߵ�����
	public int countEdge(){
		return numOfEdges;
	}

	// ����ָ���±��Ӧ�Ľڵ�
	public String getVertex(int i){
		return vertexList.get(i);
	}

	// ��ȡ�ߵ�Ȩֵ
	public int getWeight(int v1,int v2){
		return edges[v1][v2];
	}

	// ��ʾ����
	public void showGraph(){
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
		}
	}
}
