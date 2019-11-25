package tree;

import java.util.Stack;

/**
 * ������������
 * 1. ��Ҫ��֤��Ϊ��ȫ����������n���ڵ�Ŀ��ӽڵ�ҪΪn+1����2n-(n-1)��
 * 2. ��ͨ�Ķ��������һ����ӽڵ��left��rightΪ�գ��ռ䲻�ܵõ��ܺõ�����
 * 3. ��Ϊ�յ�leftָ�����(����/ǰ��/����)��ʱ�����һ���ڵ㣬��Ϊǰ���ڵ㣻rightָ����һ������Ϊ��̽ڵ�
 * 4. �൱�ڰѶ�����ת����Ϊ��һ��˫������
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
	private ThreadTreeNode root; // ���ڵ�
	private ThreadTreeNode pre = null; // ָ��ǰ�ڵ��ǰһ���ڵ㣬����ת����ʱ������ǰ���ڵ�ͺ�̽ڵ�


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


	// ת��Ϊ������(ǰ�����)
	public void transferToPre(ThreadTreeNode node){
		if(node == null){
			return;
		}
		if(node.getLeft() == null){
			node.setLeft(pre);
			node.setLeftType(true);
		}
		// pre != node ��Ϊ�˷�ֹ���һ���ڵ�ĺ���ָ��ָ���Լ�����ɱ�����ʱ����ѭ��
		// pre != null ��Ϊ�˷�ֹ��һ��preΪnull��ʱ�򱨿�ָ��
		if(pre != null && pre != node && pre.getRight() == null){
			pre.setRight(node);
			pre.setRightType(true);
		}
		pre = node; //preָ��ǰ�ڵ�
		if(!node.hasLeftType()){ // �����ǰ�ڵ�����ǰ���ڵ㣬�ٽ��еݹ�ͻ�ʹ��ǰ���ڵ��������ͻ���ѭ��
			transferToPre(node.getLeft());
		}
		if(!node.hasRightType()){
			transferToPre(node.getRight());
		}
	}

	// ǰ�����
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

	// ת��Ϊ����������(��������ķ�ʽ)
	public void transferToMid(ThreadTreeNode node){
		if (node == null){
			return;
		}
		transferToMid(node.getLeft()); // �ȴ���������
		if(node.getLeft() == null){
			node.setLeft(pre); // ��ǰһ���ڵ���Ϊǰ���ڵ�
			node.setLeftType(true); // ����ǰ��ڵ�״̬Ϊtrue
		}
		if(pre != null && pre.getRight() == null){ // ��Ϊ�ǵ������ú�̽ڵ�ֻ���ڸôεݹ���ϴβ��ܹ�����
			pre.setRight(node);
			pre.setRightType(true);
		}
		pre = node; // ��preָ��ǰ�ڵ�
		transferToMid(node.getRight()); // ����������
	}

	// �������
	public void infixOrder(){
		ThreadTreeNode node = root;
		while (node != null){
			// �����ҵ���������ĵ�һ���ڵ㣬������ڵ�
			while (!node.hasLeftType()){ // �����������������Ϊ��ȫ�������������ӽڵ㣬��������������
				node = node.getLeft();
			}
			System.out.println(node); // ��ӡ��ǰ�ڵ�
			while (node.hasRightType()){ // ����к�̽ڵ㣬�ͱ�����ӡ
				node = node.getRight();
				System.out.println(node);
			}
			node = node.getRight(); // ��û�к�̽ڵ㣬�������������ƶ�һλ
		}
	}

	// ת��Ϊ������(�������)
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

	// �������
	public void postOrder(){
		// TODO ���������Ҫ��Node�����һ��ָ�򸸽ڵ�Ķ���
	}
}

class ThreadTreeNode{
	private int no;
	private ThreadTreeNode left;
	private ThreadTreeNode right;
	private boolean leftType; // true ��ǰ���ڵ� false û��ǰ���ڵ�(��Ϊ������)
	private boolean rightType; // true �к�̽ڵ� false û�к�̽ڵ�(��Ϊ������)


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