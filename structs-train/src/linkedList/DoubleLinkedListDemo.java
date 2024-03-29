package linkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		DNode node1 = new DNode(1,"jack");
		DNode node2 = new DNode(2,"rose");
		DNode node3 = new DNode(3,"tom");
		DNode node4 = new DNode(4,"jerry");
		DNode node5 = new DNode(5,"peter");

		doubleLinkedList.add(node1);
		doubleLinkedList.add(node2);
		doubleLinkedList.add(node3);
		doubleLinkedList.add(node4);
		doubleLinkedList.add(node5);

		doubleLinkedList.list();

		DNode dNode = new DNode(5,"小红");
		doubleLinkedList.update(dNode);
		doubleLinkedList.list();

		doubleLinkedList.remove(1);
		doubleLinkedList.remove(5);
		doubleLinkedList.list();
	}
}

class DoubleLinkedList{
	private DNode head = new DNode(-1,"");

	public void add(DNode node){
		DNode temp = head; // 从头结点开始进行添加
		while(true){
			if(temp.next == null){
				break;
			}
			temp = temp.next;
		}
		temp.next = node;
		node.pre = temp;
	}

	public void update(DNode node){
		DNode cur = head.next;
		if(cur == null){
			System.out.println("链表为空");
			return;
		}
		boolean flag = false;
		while(true){
			if(cur == null){
				break;
			}else if(cur.no == node.no){
				flag = true;
				break;
			}
			cur = cur.next;
		}
		if(flag){
			cur.name = node.name;
		}else {
			System.out.println("未找到要修改的节点");
		}
	}

	public void remove(int no){
		DNode cur = head.next;
		if(cur == null){
			System.out.println("链表为空");
			return;
		}
		boolean flag = false;
		while(true){
			if(cur == null){
				break;
			}else if(cur.no == no){ // 双向链表只需找到要删除的节点即可
				flag = true;
				break;
			}
			cur = cur.next;
		}
		if(flag){
			cur.pre.next = cur.next;
			if(cur.next != null){ // 删除最后一个节点的时候回出现空指针
				cur.next.pre = cur.pre;
			}
		}else {
			System.out.println("未能找到要删除的节点");
		}
	}

	public void list(){
		DNode cur = head.next;
		if(head == null){
			System.out.println("链表为空");
			return;
		}
		while(cur != null){
			System.out.println(cur);
			cur = cur.next;
		}
	}
}

class DNode {
	public int no;
	public String name;
	public DNode next;
	public DNode pre;

	public DNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	@Override
	public String toString() {
		return "DNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				'}';
	}
}