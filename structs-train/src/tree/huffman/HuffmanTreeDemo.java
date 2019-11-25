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
 * ������������Ҷ�ӽ��Ĵ�Ȩ·��֮����С�Ķ�����
 * 1. Ȩֵ���ڵ��ֵ
 * 2. ·����root�ڵ㵽�ýڵ�����Ҫ���������ٽڵ㣬root(1��)����3��ڵ�Ҫ����3-1=2
 * 3.
 */
class HuffmanTree{

	/**
	 * ������ת��Ϊ��������
	 *  1. ����������(��С����)
	 *  2. ȡ��ͷ�����ڵ㣬����һ���µĽڵ���Ϊ�����ڵ�ĸ��ڵ㣬ȨֵΪ�����ӽڵ�֮��
	 *  3. ���½��ĸ��ڵ�Ž����飬�������ظ�֮ǰ�Ĳ��裬ֱ��������ֻʣ��һ���ڵ�
	 * @param arr
	 * @return
	 */
	public static Node transferToHuffman(int[] arr){

		// �������е�Ԫ��תΪnode��������list��
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

	// ǰ�����
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