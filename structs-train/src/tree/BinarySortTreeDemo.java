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
		BSTNode target = searchNode(value); // 要删除的节点
		if(target != null){
			// 如果要删除的节点就是root节点，且root左右节点为空
			if(root.getLeft() == null && root.getRight() == null){
				root = null;
			}
			BSTNode parent = searchParent(value); // 要删除节点的父节点
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

	// 添加节点
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
	public BSTNode searchNode(int value){
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