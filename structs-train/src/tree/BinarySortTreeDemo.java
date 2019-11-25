package tree;

public class BinarySortTreeDemo {

	/**
	 *         7
	 *     5      9
	 *   3   6  8
	 * 1   4
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{7,5,3,1,4,9,8,6};
		BinarySortTree binarySortTree = new BinarySortTree();
		for (int i = 0; i < arr.length; i++) {
			BSTNode node = new BSTNode(arr[i]);
			binarySortTree.add(node);
		}
		binarySortTree.remove(7);
		binarySortTree.infixOrder();
	}
}

class BinarySortTree{
	private BSTNode root;

	public void add(BSTNode node){
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

	public BSTNode searchNode(int value){
		if(root == null){
			return null;
		}
		return root.searchNode(value);
	}

	public BSTNode searchParent(int value){
		if(root == null){
			return null;
		}
		return root.searchParent(value);
	}

	public void remove(int value){
		if(root == null){
			return;
		}
		BSTNode target = searchNode(value); // Ҫɾ���Ľڵ�
		if(target != null){
			// ���Ҫɾ���Ľڵ����root�ڵ㣬��root���ҽڵ�Ϊ��
			if(root.getLeft() == null && root.getRight() == null){
				root = null;
			}
			BSTNode parent = searchParent(value); // Ҫɾ���ڵ�ĸ��ڵ�
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
	public int getMin(BSTNode node){
		BSTNode temp = node;
		while (temp.getLeft() != null){
			temp = temp.getLeft();
		}
		return temp.getValue();
	}
}

class BSTNode{
	private int value;
	private BSTNode left;
	private BSTNode right;

	// ��ӽڵ�
	public void add(BSTNode node){
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
	public BSTNode searchNode(int value){
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
	public BSTNode searchParent(int value){
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

	public BSTNode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public BSTNode getLeft() {
		return left;
	}

	public void setLeft(BSTNode left) {
		this.left = left;
	}

	public BSTNode getRight() {
		return right;
	}

	public void setRight(BSTNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "BSTNode{" +
				"value=" + value +
				'}';
	}
}