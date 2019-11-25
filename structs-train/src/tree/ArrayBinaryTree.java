package tree;

import java.util.Arrays;

/**
 * ˳��洢������
 * ʹ������洢����������������Ҫ��֤Ϊ��ȫ������
 * ��������±���м��㣺
 * 	      1(0)
 *   2(1)     3(2)
 * 4(3) 5(4) 6(5) 7(6)
 * �ڵ� n�����ӽڵ��±�Ϊ 2(n+1)-1
 * �ڵ� n�����ӽڵ��±�Ϊ 2(n+1)
 * �ڵ� n�ĸ��ڵ��±�Ϊ (n-1)/2
 */
public class ArrayBinaryTree {

	private int[] arr;

	public ArrayBinaryTree(int[] arr){
		this.arr = arr;
	}

	public static void main(String[] args) {
//		int[] arr = new int[]{1,2,3,4,5,6,7};
//		ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
//		arrayBinaryTree.postOrder(0);
	}


	// ǰ�����
	public void preOrder(int index){
		if(arr == null ||  arr.length < 1){
			return;
		}
		System.out.println(arr[index]);
		if((2*index+1) < arr.length){ // ������
			preOrder(2*index+1);
		}
		if((2*index+2) < arr.length){ // ������
			preOrder(2*index+2);
		}
	}

	// �������
	public void infixOrder(int index){
		if(arr == null ||  arr.length < 1){
			return;
		}
		if((2*index+1) < arr.length){ // ������
			infixOrder(2*index+1);
		}
		System.out.println(arr[index]);
		if((2*index+2) < arr.length){ // ������
			infixOrder(2*index+2);
		}
	}

	// �������
	public void postOrder(int index){
		if(arr == null ||  arr.length < 1){
			return;
		}
		if((2*index+1) < arr.length){ // ������
			postOrder(2*index+1);
		}
		if((2*index+2) < arr.length){ // ������
			postOrder(2*index+2);
		}
		System.out.println(arr[index]);
	}
}
