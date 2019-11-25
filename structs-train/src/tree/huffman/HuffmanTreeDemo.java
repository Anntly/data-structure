package tree.huffman;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeDemo {

	public static void main(String[] args) {
		int[] arr = new int[]{13,7,8,3,29,6,1};
		Node node = HuffmanTree.transferToHuffman(arr);
		node.preOrder();
	}
}

/**
 * 哈夫曼树：即叶子结点的带权路径之和最小的二叉树
 * 1. 权值：节点的值
 * 2. 路径：root节点到该节点所需要经过的最少节点，root(1层)到第3层节点要经过3-1=2
 * 3.
 */
class HuffmanTree{

	/**
	 * 将数组转换为哈夫曼树
	 *  1. 将数组排序(有小到大)
	 *  2. 取出头两个节点，创建一个新的节点作为两个节点的父节点，权值为两个子节点之和
	 *  3. 将新建的父节点放进数组，再依次重复之前的步骤，直到数组中只剩下一个节点
	 * @param arr
	 * @return
	 */
	public static Node transferToHuffman(int[] arr){

		// 将数组中的元素转为node，并放入list中
		ArrayList<Node> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			Node node = new Node(arr[i]);
			list.add(node);
		}

		while (list.size() > 1){
			Collections.sort(list);
			Node leftNode = list.get(0);
			Node rightNode = list.get(1);
			Node parent = new Node(leftNode.getValue() + rightNode.getValue());
			parent.setLeft(leftNode);
			parent.setRight(rightNode);
			list.remove(leftNode);
			list.remove(rightNode);
			list.add(parent);
		}
		return list.get(0);
	}

}

class Node implements Comparable<Node>{
	private int value;
	private Node left;
	private Node right;

	public Node(int value) {
		this.value = value;
	}

	// 前序遍历
	public void preOrder(){
		System.out.println(this);
		if(this.getLeft() != null){
			this.getLeft().preOrder();
		}
		if(this.getRight() != null){
			this.getRight().preOrder();
		}
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
	}
}