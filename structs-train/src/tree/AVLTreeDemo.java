package tree;

public class AVLTreeDemo {
	public static void main(String[] args) {
		int[] arr = new int[]{10,11,7,6,8,9};
		AvlTree avlTree = new AvlTree();
		for (int i = 0; i < arr.length; i++) {
			AvlNode node = new AvlNode(arr[i]);
			avlTree.add(node);
		}
		System.out.println(avlTree.getHeight());
		avlTree.infixOrder();
	}
}
class AvlTree{
	private AvlNode root;


	public void add(AvlNode node){
		if(node == null){
			return;
		}
		if(root == null){
			this.root = node;
			return;
		}
		root.add(node);
	}

	public void infixOrder(){
		if(root == null){
			return;
		}
		root.infixOrder();
	}

	public AvlNode searchNode(int value){
		if(root == null){
			return null;
		}
		return root.searchNode(value);
	}

	public AvlNode searchParent(int value){
		if(root == null){
			return null;
		}
		return root.searchParent(value);
	}

	public void remove(int value){
		if(root == null){
			return;
		}
		AvlNode target = searchNode(value); // Ҫɾ���Ľڵ�
		if(target != null){
			// ���Ҫɾ���Ľڵ����root�ڵ㣬��root���ҽڵ�Ϊ��
			if(root.getLeft() == null && root.getRight() == null){
				root = null;
			}
			AvlNode parent = searchParent(value); // Ҫɾ���ڵ�ĸ��ڵ�
			// ���ɾ���Ľڵ����ҽڵ�Ϊ��,��Ҫɾ���Ľڵ�ΪҶ�ӽ��
			if(target.getLeft() == null && target.getRight() == null){
				// ���Ҫɾ���Ľڵ���parent��ڵ�
				if(parent.getLeft() != null && parent.getLeft().getValue() == value){
					parent.setLeft(null);
				}
				// ���Ҫɾ���Ľڵ���parent���ҽڵ�
				if(parent.getRight() != null && parent.getRight().getValue() == value){
					parent.setRight(null);
				}
				// ��Ҫɾ���Ľڵ�����������Ϊ�յ�ʱ��
			} else if(target.getLeft() != null && target.getRight() != null){
				// ����ʹ������������С�ڵ����ɾ���Ľڵ�
				int min = getMin(target.getRight());
				remove(min); // ɾ����С�Ľڵ�
				target.setValue(min);
				// Ҳ����ʹ�����������Ľڵ����ɾ���Ľڵ�
			}else { // ��Ҫɾ���Ľڵ���һ���ӽڵ��ʱ��
				// ��Ҫɾ���Ľڵ�ֻ�����ӽڵ��ʱ��
				if (target.getLeft() != null){
					if(parent != null) { // ɾ�������ֻ�������ڵ��ʱ��ɾ�������ڵ㣬�鵽��parent��Ϊ�գ���Ҫ�����ж�
						// ��Ҫɾ���������ӽڵ�
						if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
							parent.setLeft(target.getLeft());
						} else { // Ҫɾ�������ҽڵ�
							parent.setRight(target.getLeft());
						}
					}else {
						root = target.getLeft(); // ����Ϊ�յ�ʱ��ֻ��Ҫ��rootָ��Ҫɾ���Ľڵ����һ������
					}
				} else { // Ҫɾ���Ľڵ�ֻ���ҽڵ�
					if(parent != null){
						if(parent.getLeft() != null && parent.getLeft().getValue() == value){
							parent.setLeft(target.getRight());
						}else {
							parent.setRight(target.getRight());
						}
					}else {
						root = target.getRight();
					}

				}
			}
		}
	}

	// ��ȡ�ýڵ����������С�Ľڵ��ֵ
	public int getMin(AvlNode node){
		AvlNode temp = node;
		while (temp.getLeft() != null){
			temp = temp.getLeft();
		}
		return temp.getValue();
	}

	// ��ȡ�߶�
	public int getHeight(){
		if(root == null){
			return 0;
		}
		return root.getHeight();
	}
}
class AvlNode{
	private int value;
	private AvlNode left;
	private AvlNode right;

	public AvlNode(int value) {
		this.value = value;
	}

	// ��ȡ��ǰ�ڵ�ĸ߶ȣ����Ե�ǰ�ڵ�Ϊ���ڵ�����ĸ߶�
	public int getHeight(){
		return Math.max(left == null ? 0 : left.getHeight(),
				this.right == null ? 0 : this.right.getHeight()) + 1;
	}

	// �����������ĸ߶�
	public int getLeftHeight(){
		if (this.left == null){
			return 0;
		}
		return this.left.getHeight();
	}

	// �����������ĸ߶�
	public int getRightHeight(){
		if(this.right == null){
			return 0;
		}
		return this.right.getHeight();
	}

	// ����ת
	public void leftRotate(){
		// �½�һ����ʱ�ڵ�
		AvlNode temp = new AvlNode(this.value);
		// ����temp��left�뵱ǰ�ڵ��leftһ��
		temp.left = this.left;
		// ����temp���ҽڵ�
		temp.right = this.right.left;
		// ����ǰ�ڵ��ֵ����Ϊ���ӽڵ��ֵ
		this.setValue(this.right.value);
		// ��ǰ�ڵ����ӽڵ�ָ�����ӽڵ�����ӽڵ�
		this.setRight(this.right.right);
		// ����ǰ�ڵ�����ӽڵ�ָ����ʱ�ڵ�
		this.left = temp;
	}

	// ����ת
	public void rightRotate(){
		AvlNode temp = new AvlNode(this.value);
		temp.right = this.right;
		temp.left = this.left.right;
		this.setValue(this.left.value);
		this.left = this.left.left;
		this.right = temp;
	}

	// ��ӽڵ�
	public void add(AvlNode node){
		if(node == null){
			return;
		}
		if(node.getValue() < this.getValue()){
			if(this.left == null){
				this.left = node;
			}else {
				this.left.add(node);
			}
		}else {
			if(this.right == null){
				this.right = node;
			}else {
				this.right.add(node);
			}
		}
		// ��Ӻ�����ж�
		// �����ǰ�ڵ��������߶ȴ����������߶���Ҫ��������
		if(this.getRightHeight() - this.getLeftHeight() > 1){
			// �����ǰ�ڵ�����ӽڵ��������߶ȴ����������ĸ߶�
			if(this.right != null && this.right.getLeftHeight() > this.right.getRightHeight()){
				this.right.rightRotate();
				this.leftRotate();
			}else {
				leftRotate();
			}
			return; // �����������ж�ֱ��return���Ͳ��ý��������ж���
		}

		// �����ǰ�ڵ��������߶ȴ����������߶���Ҫ��������
		if(this.getLeftHeight() - this.getRightHeight() > 1){
			// ������ӽڵ���������߶ȴ����������ĸ߶�
			if (this.left != null && this.left.getRightHeight() > this.left.getLeftHeight()){
				this.left.leftRotate(); // �����ӽڵ�����
				this.rightRotate(); // �ٽ���ǰ�ڵ�����
			}else {
				rightRotate();
			}
		}
	}

	// �������
	public void infixOrder(){
		if(this.left != null){
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null){
			this.right.infixOrder();
		}
	}

	// ���ҽڵ�
	public AvlNode searchNode(int value){
		if(this.value == value){
			return this;
		}else if(this.value > value){ // �����ѯ��ֵС�ڵ�ǰ�ڵ��ֵ��������������
			if(this.left != null){
				return this.left.searchNode(value);
			}else {
				return null;
			}
		}else{
			if(this.right != null){
				return this.right.searchNode(value);
			}else {
				return null;
			}
		}
	}

	// ���ҽڵ�ĸ��ڵ�
	public AvlNode searchParent(int value){
		if(this.left != null && this.left.value == value
				|| this.right != null && this.right.value == value){
			return this;
		}
		if (this.value > value && this.left != null){
			return this.left.searchParent(value);
		} else if (this.value <= value && this.right != null){
			return this.right.searchParent(value);
		} else {
			return null;
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public AvlNode getLeft() {
		return left;
	}

	public void setLeft(AvlNode left) {
		this.left = left;
	}

	public AvlNode getRight() {
		return right;
	}

	public void setRight(AvlNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "AvlNode{" +
				"value=" + value +
				'}';
	}
}
