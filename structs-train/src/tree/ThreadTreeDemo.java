package tree;

import java.util.Stack;

/**
 * 线索化二叉树
 * 1. 需要保证树为完全二叉树，且n个节点的空子节点要为n+1个【2n-(n-1)】
 * 2. 普通的二叉树最后一层的子节点的left与right为空，空间不能得到很好的利用
 * 3. 将为空的left指向遍历(中序/前序/后序)的时候的上一个节点，称为前驱节点；right指向下一个，称为后继节点
 * 4. 相当于把二叉树转换成为了一个双向链表
 */
public class ThreadTreeDemo {

	/**
	 *          1
	 *      2       3
	 *   4    5   6
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadTreeNode node1 = new ThreadTreeNode(1);
		ThreadTreeNode node2 = new ThreadTreeNode(2);
		ThreadTreeNode node3 = new ThreadTreeNode(3);
		ThreadTreeNode node4 = new ThreadTreeNode(4);
		ThreadTreeNode node5 = new ThreadTreeNode(5);
		ThreadTreeNode node6 = new ThreadTreeNode(6);

		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		ThreadTree threadTree = new ThreadTree(node1);
		threadTree.transferToPre();

		System.out.println(node3.hasLeftType()+":"+node3.getLeft());
		System.out.println(node6.hasRightType()+":"+node6.getRight());
		threadTree.printPre();
	}
}

class ThreadTree{
	private ThreadTreeNode root; // 根节点
	private ThreadTreeNode pre = null; // 指向当前节点的前一个节点，用于转换的时候设置前驱节点和后继节点


	public ThreadTree(ThreadTreeNode root){
		this.root = root;
	}

	public void transferToPre(){
		transferToPre(root);
	}

	public void transferToMid(){
		transferToMid(root);
	}
	public void transferToPost(){
		transferToPost(root);
	}


	// 转换为二叉树(前序遍历)
	public void transferToPre(ThreadTreeNode node){
		if(node == null){
			return;
		}
		if(node.getLeft() == null){
			node.setLeft(pre);
			node.setLeftType(true);
		}
		// pre != node 是为了防止最后一个节点的后续指针指向自己，造成遍历的时候死循环
		// pre != null 是为了防止第一次pre为null的时候报空指针
		if(pre != null && pre != node && pre.getRight() == null){
			pre.setRight(node);
			pre.setRightType(true);
		}
		pre = node; //pre指向当前节点
		if(!node.hasLeftType()){ // 如果当前节点已有前驱节点，再进行递归就会使用前驱节点进行运算就会死循环
			transferToPre(node.getLeft());
		}
		if(!node.hasRightType()){
			transferToPre(node.getRight());
		}
	}

	// 前序遍历
	public void printPre(){
		ThreadTreeNode node = root;
		while (node != null){
			while (!node.hasLeftType()){
				System.out.println(node);
				node = node.getLeft();
			}
			System.out.println(node);
			node = node.getRight();
		}

	}

	// 转换为线索二叉树(中序遍历的方式)
	public void transferToMid(ThreadTreeNode node){
		if (node == null){
			return;
		}
		transferToMid(node.getLeft()); // 先处理左子树
		if(node.getLeft() == null){
			node.setLeft(pre); // 将前一个节点设为前驱节点
			node.setLeftType(true); // 设置前序节点状态为true
		}
		if(pre != null && pre.getRight() == null){ // 因为是单向设置后继节点只有在该次递归的上次才能够进行
			pre.setRight(node);
			pre.setRightType(true);
		}
		pre = node; // 将pre指向当前节点
		transferToMid(node.getRight()); // 处理右子树
	}

	// 中序遍历
	public void infixOrder(){
		ThreadTreeNode node = root;
		while (node != null){
			// 遍历找到中序遍历的第一个节点，即最左节点
			while (!node.hasLeftType()){ // 由于是中序遍历，且为完全二叉树，除了子节点，其他都有左子树
				node = node.getLeft();
			}
			System.out.println(node); // 打印当前节点
			while (node.hasRightType()){ // 如果有后继节点，就遍历打印
				node = node.getRight();
				System.out.println(node);
			}
			node = node.getRight(); // 当没有后继节点，即有右子树，移动一位
		}
	}

	// 转换为二叉树(后序遍历)
	public void transferToPost(ThreadTreeNode node){
		if(node == null){
			return;
		}
		transferToPost(node.getLeft());
		transferToPost(node.getRight());
		if(node.getLeft() == null){
			node.setLeft(pre);
			node.setLeftType(true);
		}
		if(pre != null && pre.getRight() == null){
			pre.setRight(node);
			pre.setRightType(true);
		}
		pre = node;
	}

	// 后序遍历
	public void postOrder(){
		// TODO 后序遍历需要在Node中添加一个指向父节点的对象
	}
}

class ThreadTreeNode{
	private int no;
	private ThreadTreeNode left;
	private ThreadTreeNode right;
	private boolean leftType; // true 有前驱节点 false 没有前驱节点(即为左子树)
	private boolean rightType; // true 有后继节点 false 没有后继节点(即为右子树)


	public ThreadTreeNode(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public ThreadTreeNode getLeft() {
		return left;
	}

	public void setLeft(ThreadTreeNode left) {
		this.left = left;
	}

	public ThreadTreeNode getRight() {
		return right;
	}

	public void setRight(ThreadTreeNode right) {
		this.right = right;
	}

	public boolean hasLeftType() {
		return leftType;
	}

	public void setLeftType(boolean leftType) {
		this.leftType = leftType;
	}

	public boolean hasRightType() {
		return rightType;
	}

	public void setRightType(boolean rightType) {
		this.rightType = rightType;
	}

	@Override
	public String toString() {
		return "ThreadTreeNode{" +
				"no=" + no +
				'}';
	}
}