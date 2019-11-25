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
		AvlNode target = searchNode(value); // 要删除的节点
		if(target != null){
			// 如果要删除的节点就是root节点，且root左右节点为空
			if(root.getLeft() == null && root.getRight() == null){
				root = null;
			}
			AvlNode parent = searchParent(value); // 要删除节点的父节点
			// 如果删除的节点左右节点为空,即要删除的节点为叶子结点
			if(target.getLeft() == null && target.getRight() == null){
				// 如果要删除的节点是parent左节点
				if(parent.getLeft() != null && parent.getLeft().getValue() == value){
					parent.setLeft(null);
				}
				// 如果要删除的节点是parent的右节点
				if(parent.getRight() != null && parent.getRight().getValue() == value){
					parent.setRight(null);
				}
				// 当要删除的节点左右子树不为空的时候
			} else if(target.getLeft() != null && target.getRight() != null){
				// 可以使用右子树的最小节点代替删除的节点
				int min = getMin(target.getRight());
				remove(min); // 删除最小的节点
				target.setValue(min);
				// 也可以使用左子树最大的节点代替删除的节点
			}else { // 当要删除的节点有一个子节点的时候
				// 当要删除的节点只有左子节点的时候
				if (target.getLeft() != null){
					if(parent != null) { // 删除到最后只有两个节点的时候，删除顶部节点，查到的parent会为空，需要进行判断
						// 当要删除的是左子节点
						if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
							parent.setLeft(target.getLeft());
						} else { // 要删除的是右节点
							parent.setRight(target.getLeft());
						}
					}else {
						root = target.getLeft(); // 当其为空的时候，只需要将root指向要删除的节点的下一个即可
					}
				} else { // 要删除的节点只有右节点
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

	// 获取该节点的子树中最小的节点的值
	public int getMin(AvlNode node){
		AvlNode temp = node;
		while (temp.getLeft() != null){
			temp = temp.getLeft();
		}
		return temp.getValue();
	}

	// 获取高度
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

	// 获取当前节点的高度，即以当前节点为根节点的树的高度
	public int getHeight(){
		return Math.max(left == null ? 0 : left.getHeight(),
				this.right == null ? 0 : this.right.getHeight()) + 1;
	}

	// 返回左子树的高度
	public int getLeftHeight(){
		if (this.left == null){
			return 0;
		}
		return this.left.getHeight();
	}

	// 返回右子树的高度
	public int getRightHeight(){
		if(this.right == null){
			return 0;
		}
		return this.right.getHeight();
	}

	// 左旋转
	public void leftRotate(){
		// 新建一个临时节点
		AvlNode temp = new AvlNode(this.value);
		// 设置temp的left与当前节点的left一致
		temp.left = this.left;
		// 设置temp的右节点
		temp.right = this.right.left;
		// 将当前节点的值设置为右子节点的值
		this.setValue(this.right.value);
		// 当前节点右子节点指向右子节点的右子节点
		this.setRight(this.right.right);
		// 将当前节点的左子节点指向临时节点
		this.left = temp;
	}

	// 右旋转
	public void rightRotate(){
		AvlNode temp = new AvlNode(this.value);
		temp.right = this.right;
		temp.left = this.left.right;
		this.setValue(this.left.value);
		this.left = this.left.left;
		this.right = temp;
	}

	// 添加节点
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
		// 添加后进行判断
		// 如果当前节点右子树高度大于左子树高度需要进行左旋
		if(this.getRightHeight() - this.getLeftHeight() > 1){
			// 如果当前节点的右子节点左子树高度大于右子树的高度
			if(this.right != null && this.right.getLeftHeight() > this.right.getRightHeight()){
				this.right.rightRotate();
				this.leftRotate();
			}else {
				leftRotate();
			}
			return; // 如果进入这个判断直接return，就不用进入下述判断了
		}

		// 如果当前节点左子树高度大于右子树高度需要进行右旋
		if(this.getLeftHeight() - this.getRightHeight() > 1){
			// 如果左子节点的右子树高度大于左子树的高度
			if (this.left != null && this.left.getRightHeight() > this.left.getLeftHeight()){
				this.left.leftRotate(); // 将左子节点左旋
				this.rightRotate(); // 再将当前节点右旋
			}else {
				rightRotate();
			}
		}
	}

	// 中序遍历
	public void infixOrder(){
		if(this.left != null){
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null){
			this.right.infixOrder();
		}
	}

	// 查找节点
	public AvlNode searchNode(int value){
		if(this.value == value){
			return this;
		}else if(this.value > value){ // 如果查询的值小于当前节点的值就向左子树遍历
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

	// 查找节点的父节点
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
