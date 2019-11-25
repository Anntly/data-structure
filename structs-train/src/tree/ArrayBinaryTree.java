package tree;

import java.util.Arrays;

/**
 * 顺序存储二叉树
 * 使用数组存储二叉树，二叉树需要保证为完全二叉树
 * 以数组的下标进行计算：
 * 	      1(0)
 *   2(1)     3(2)
 * 4(3) 5(4) 6(5) 7(6)
 * 节点 n的左子节点下标为 2(n+1)-1
 * 节点 n的右子节点下标为 2(n+1)
 * 节点 n的父节点下标为 (n-1)/2
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


	// 前序遍历
	public void preOrder(int index){
		if(arr == null ||  arr.length < 1){
			return;
		}
		System.out.println(arr[index]);
		if((2*index+1) < arr.length){ // 左子树
			preOrder(2*index+1);
		}
		if((2*index+2) < arr.length){ // 右子树
			preOrder(2*index+2);
		}
	}

	// 中序遍历
	public void infixOrder(int index){
		if(arr == null ||  arr.length < 1){
			return;
		}
		if((2*index+1) < arr.length){ // 左子树
			infixOrder(2*index+1);
		}
		System.out.println(arr[index]);
		if((2*index+2) < arr.length){ // 右子树
			infixOrder(2*index+2);
		}
	}

	// 后序遍历
	public void postOrder(int index){
		if(arr == null ||  arr.length < 1){
			return;
		}
		if((2*index+1) < arr.length){ // 左子树
			postOrder(2*index+1);
		}
		if((2*index+2) < arr.length){ // 右子树
			postOrder(2*index+2);
		}
		System.out.println(arr[index]);
	}
}
