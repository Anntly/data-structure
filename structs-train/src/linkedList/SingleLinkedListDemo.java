package linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		Node node1 = new Node(1,"jack");
		Node node2 = new Node(2,"rose");
		Node node3 = new Node(3,"tom");
		Node node4 = new Node(4,"jerry");
		Node node7 = new Node(7,"jack");
		Node node8 = new Node(8,"rose");
		Node node9 = new Node(9,"tom");
		Node node10 = new Node(10,"jerry");

		SingleLinkedList singleLinkedList = new SingleLinkedList();
		SingleLinkedList singleLinkedList1 = new SingleLinkedList();

//		singleLinkedList.add(node1);
//		singleLinkedList.add(node2);
//		singleLinkedList.add(node3);
//		singleLinkedList.add(node4);

		singleLinkedList.addSorted(node1);
		singleLinkedList.addSorted(node4);
		singleLinkedList.addSorted(node2);
		singleLinkedList.addSorted(node3);

		singleLinkedList1.add(node7);
		singleLinkedList1.add(node8);
		singleLinkedList1.add(node9);
		singleLinkedList1.add(node10);

		Node temp = unionList(singleLinkedList1.getHead(),singleLinkedList.getHead());
		while(true){
			System.out.println(temp.toString());
			temp = temp.next;
			if(temp == null){
				break;
			}
		}
//		singleLinkedList.print();
//
//		singleLinkedList.reversePrint(singleLinkedList.getHead());

//		singleLinkedList.reverseList(singleLinkedList.getHead());
//		singleLinkedList.print();

//		Node node = new Node(5,"alibaba");
//		singleLinkedList.update(node);
//		singleLinkedList.print();
//
//		Node removeNode = new Node(1,"jack");
//		Node removeNode1 = new Node(4,"jack");
//		singleLinkedList.remove(removeNode);
//		singleLinkedList.remove(removeNode1);
//		singleLinkedList.print();
//		System.out.println("链表长度为:"+size(singleLinkedList.getHead()));
//		System.out.println("倒数第2个节点是:"+getNode(singleLinkedList.getHead(),2));
	}

	// 获取倒数第k个元素
	public static Node getNode(Node head,int num){
		Node cur = head.next;
		if(cur == null){
			return null;
		}
		int size  = size(head);
		if(size == 0 || num > size){
			return null;
		}
		int count = size - num;
		for (int i = 0; i < count; i++) {
			cur = cur.next;
		}
		return cur;
	}

	// 获取链表中的有效节点数
	public static int size(Node head){
		Node cur = head.next;
		if(cur == null){
			return 0;
		}
		int count = 0;
		while(true){
			if(cur == null){
				break;
			}
			count++;
			cur = cur.next;
		}
		return count;
	}

	// 合并两个有序链表
	public static Node unionList(Node list1,Node list2){
		Node cur1 = list1.next;
		Node cur2 = list2.next;
		Node newList = null;

		if(cur1 == null){ // 当list1为空的时候，直接返回list2，反之亦然
			return list2;
		}
		if(cur2 == null){
			return list1;
		}

		if(cur1.no < cur2.no){ // 第一次对比，为了获取新链表的头结点
			newList = cur1;
			cur1 = cur1.next;
		}else {
			newList = cur2;
			cur2 = cur2.next;
		}
		Node temp = newList; // 因为要返回头结点，使用临时节点，进行遍历
		while (cur1 != null && cur2 != null){
			if(cur1.no <= cur2.no){
				temp.next = cur1;
				cur1 = cur1.next;
			}else {
				temp.next = cur2;
				cur2 = cur2.next;
			}
			temp = temp.next; // temp也要后移一位
		}
		if(cur1 == null){
			temp.next = cur2;
		}else {
			temp.next = cur1;
		}

		return newList; // 返回的新链表是没有 空的head的
	}
}


class SingleLinkedList{
	// 头结点
	private Node head = new Node(-1,"");

	public Node getHead() {
		return head;
	}

	// 添加节点
	public void add(Node node){
		// 头结点不能进行移动，使用temp节点来进行遍历
		Node temp = head;
		while(true){
			// next 为空代表遍历结束
			if(temp.next == null){
				break;
			}
			temp = temp.next;
		}
		temp.next = node;
	}

	// 顺序添加，按no升序
	public void addSorted(Node node){
		Node temp = head;
		boolean flag = false; // 是否已经存在要添加的节点
		while(true){
			if(temp.next == null){
				break;
			}else if(temp.next.no > node.no){ // 找到前一个节点
				break;
			}else if(temp.next.no == node.no){
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag){
			System.out.println("该节点已经被添加"+temp.toString());
		}else{
			node.next = temp.next;
			temp.next = node;
		}
	}

	// 根据no修改节点信息
	public void update(Node node){
		boolean flag = false;
		Node temp = head.next;
		if (temp == null){
			System.out.println("链表为空");
			return;
		}
		while(true){
			if(temp == null){
				break;
			}else if(temp.no == node.no) {
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag){
			temp.name = node.name;
		}else {
			System.out.println("未找到需要修改的节点");
		}
	}
	// 删除节点---找到需要删除的节点的前一个节点
	public void remove(Node node){
		Node temp = head;
		boolean flag = false;
		while(true){
			if(temp.next == null){
				break;
			}else if(temp.next.no == node.no){
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag){
			temp.next = temp.next.next;
		}else{
			System.out.println("未找到待删除的节点");
		}
	}

	// 翻转链表
	// 创建一个临时链表，使用头插法，最后将head的next指向临时链表的next
	public void reverseList(Node head){
		Node cur = head.next;
		if(cur == null || cur.next == null){ // 当没有节点，或只有一个节点的时候就不用遍历
			return;
		}
		Node next = null; // 暂存cur的下一个节点
		Node tempList = new Node(-1,""); // 临时链表
		while(cur != null){
			next = cur.next; // 暂存下一个节点，否则会断开
			cur.next = tempList.next;
			tempList.next = cur;
			cur = next;
		}
		// 遍历完成后将head的next指向tempList的next
		head.next = tempList.next;
	}

	// 逆序打印 使用stack
	public void reversePrint(Node head){
		Node cur = head.next;
		if(cur == null){
			System.out.println("链表为空");
			return;
		}
		Stack<Node> stack = new Stack<>();
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		while (stack.size() > 0){
			System.out.println(stack.pop());
		}
	}

	// 遍历打印list
	public void print(){
		Node temp =head;
		while(true){
			temp = temp.next;
			if(temp == null){
				break;
			}
			System.out.println(temp.toString());
		}
	}

}

class Node{
	public int no;
	public String name;
	public Node next;

	public Node(int no, String name) {
		this.no = no;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Node{" +
				"no=" + no +
				", name='" + name + '\'' +
				'}';
	}
}