package tree;


/**
 * ������
 */
public class BinaryTreeDemo {

	public static void main(String[] args) {


		TreeNode treeNode1 = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode5 = new TreeNode(5);
		TreeNode treeNode6 = new TreeNode(6);
		TreeNode treeNode7 = new TreeNode(7);
		TreeNode treeNode8 = new TreeNode(8);

//		treeNode2.setLeft(treeNode1);
//		treeNode2.setRight(treeNode4);
//		treeNode4.setLeft(treeNode3);
//		treeNode4.setRight(treeNode5);
//		treeNode1.setLeft(treeNode6);
//		treeNode1.setRight(treeNode7);
		/**
		 *           2
		 *       1       4
		 *    6    7   3   5
		 *
		 */

		treeNode1.setLeft(treeNode2);
		treeNode1.setRight(treeNode3);
		treeNode2.setLeft(treeNode4);
		//treeNode2.setRight(treeNode7);
		treeNode4.setLeft(treeNode7);
		treeNode3.setLeft(treeNode5);
		treeNode3.setRight(treeNode6);
		treeNode6.setLeft(treeNode8);

		BinaryTree tree = new BinaryTree(treeNode1);


		//tree.preOrder();
		//tree.infixOrder();
		//tree.postOrder();

		//System.out.println(tree.postFind(4));
		tree.preOrder();
//		tree.remove(6);
//		tree.preOrder();
	}
}

class BinaryTree{
	private TreeNode root; // ���ڵ�

	public BinaryTree(TreeNode root){
		this.root = root;
	}

	// ǰ�����
	public void preOrder(){
		if(root == null){
			System.out.println("��Ϊ��");
			return;
		}
		root.preOrder();
	}

	// �������
	public void infixOrder(){
		if(root == null){
			System.out.println("��Ϊ��");
			return;
		}
		root.infixOrder();
	}

	// �������
	public void postOrder(){
		if(root == null){
			System.out.println("��Ϊ��");
			return;
		}
		root.postOrder();
	}

	// ǰ�����
	public TreeNode preFind(int no){
		if(root == null){
			System.out.println("��Ϊ��");
			return null;
		}
		return root.preFind(no);
	}

	// �������
	public TreeNode infixFind(int no){
		if(root == null){
			System.out.println("��Ϊ��");
			return null;
		}
		return root.infixFind(no);
	}

	// �������
	public TreeNode postFind(int no){
		if(root == null){
			System.out.println("��Ϊ��");
			return null;
		}
		return root.postFind(no);
	}

	// ɾ��
	public void remove(int no){
		if(root == null){
			System.out.println("��Ϊ��");
			return ;
		}
		if(root.getNo() == no){
			root = null;
			return;
		}
		root.remove(no);
	}
}

class TreeNode{
	private int no;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(int no) {
		this.no = no;
	}

	// ǰ�����(���ڵ��ȱ���->������->������)
	public void preOrder(){
		System.out.println(this);
		// ����������
		if(this.left != null){
			this.left.preOrder();
		}

		// ����������
		if(this.right != null){
			this.right.preOrder();
		}
	}

	// �������(������->���ڵ�-> ������)
	public void infixOrder(){
		if(this.left != null){
			this.left.infixOrder();
		}

		System.out.println(this);

		if(this.right != null){
			this.right.infixOrder();
		}
	}

	// �������(������->������->���ڵ�)
	public void postOrder(){
		if(this.left != null){
			this.left.postOrder();
		}

		if(this.right != null){
			this.right.postOrder();
		}

		System.out.println(this);
	}

	// ǰ�����
	public TreeNode preFind(int no){
		System.out.println("1");
		TreeNode result = null;
		if(this.getNo() == no){
			return this;
		}

		// ����������
		if(this.left != null){
			result = this.left.preFind(no);
		}
		if(result != null){
			return result;
		}

		// ����������
		if(this.right != null){
			result = this.right.preFind(no);
		}
		return result;
	}

	// �������
	public TreeNode infixFind(int no){
		System.out.println("1");
		TreeNode result = null;
		if(this.left != null){
			result = this.left.infixFind(no);
		}
		if(result != null){
			return result;
		}
		if(this.getNo() == no){
			return this;
		}

		// ����������
		if(this.right != null){
			result = this.right.preFind(no);
		}
		return result;
	}

	// �������
	public TreeNode postFind(int no){
		System.out.println("1");
		TreeNode result = null;
		if(this.left != null){
			result = this.left.postFind(no);
		}
		if(result != null){
			return result;
		}
		// ����������
		if(this.right != null){
			result = this.right.postFind(no);
		}
		if(result != null){
			return result;
		}
		if(this.getNo() == no){
			return this;
		}
		return result; // δ�ҵ��ͷ��ؿ�
	}

	// ɾ���ڵ�,��Ҫ�õ�Ҫɾ���ڵ��ǰһ���ڵ����ɾ��
	public void remove(int no){
		if(this.left != null && this.left.getNo() == no){
			// ʹ����ڵ����ڵ����Ҫɾ���Ľڵ�
			this.left = null;
			return;
		}
		if(this.right != null && this.right.getNo() == no){
			this.right = null;
			return;
		}
		if (this.left != null){
			this.left.remove(no);
		}
		if(this.right != null){
			this.right.remove(no);
		}
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "TreeNode{" +
				"no=" + no +
				'}';
	}
}