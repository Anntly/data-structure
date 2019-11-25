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
//		System.out.println("������Ϊ:"+size(singleLinkedList.getHead()));
//		System.out.println("������2���ڵ���:"+getNode(singleLinkedList.getHead(),2));
	}

	// ��ȡ������k��Ԫ��
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

	// ��ȡ�����е���Ч�ڵ���
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

	// �ϲ�������������
	public static Node unionList(Node list1,Node list2){
		Node cur1 = list1.next;
		Node cur2 = list2.next;
		Node newList = null;

		if(cur1 == null){ // ��list1Ϊ�յ�ʱ��ֱ�ӷ���list2����֮��Ȼ
			return list2;
		}
		if(cur2 == null){
			return list1;
		}

		if(cur1.no < cur2.no){ // ��һ�ζԱȣ�Ϊ�˻�ȡ�������ͷ���
			newList = cur1;
			cur1 = cur1.next;
		}else {
			newList = cur2;
			cur2 = cur2.next;
		}
		Node temp = newList; // ��ΪҪ����ͷ��㣬ʹ����ʱ�ڵ㣬���б���
		while (cur1 != null && cur2 != null){
			if(cur1.no <= cur2.no){
				temp.next = cur1;
				cur1 = cur1.next;
			}else {
				temp.next = cur2;
				cur2 = cur2.next;
			}
			temp = temp.next; // tempҲҪ����һλ
		}
		if(cur1 == null){
			temp.next = cur2;
		}else {
			temp.next = cur1;
		}

		return newList; // ���ص���������û�� �յ�head��
	}
}


class SingleLinkedList{
	// ͷ���
	private Node head = new Node(-1,"");

	public Node getHead() {
		return head;
	}

	// ��ӽڵ�
	public void add(Node node){
		// ͷ��㲻�ܽ����ƶ���ʹ��temp�ڵ������б���
		Node temp = head;
		while(true){
			// next Ϊ�մ����������
			if(temp.next == null){
				break;
			}
			temp = temp.next;
		}
		temp.next = node;
	}

	// ˳����ӣ���no����
	public void addSorted(Node node){
		Node temp = head;
		boolean flag = false; // �Ƿ��Ѿ�����Ҫ��ӵĽڵ�
		while(true){
			if(temp.next == null){
				break;
			}else if(temp.next.no > node.no){ // �ҵ�ǰһ���ڵ�
				break;
			}else if(temp.next.no == node.no){
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag){
			System.out.println("�ýڵ��Ѿ������"+temp.toString());
		}else{
			node.next = temp.next;
			temp.next = node;
		}
	}

	// ����no�޸Ľڵ���Ϣ
	public void update(Node node){
		boolean flag = false;
		Node temp = head.next;
		if (temp == null){
			System.out.println("����Ϊ��");
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
			System.out.println("δ�ҵ���Ҫ�޸ĵĽڵ�");
		}
	}
	// ɾ���ڵ�---�ҵ���Ҫɾ���Ľڵ��ǰһ���ڵ�
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
			System.out.println("δ�ҵ���ɾ���Ľڵ�");
		}
	}

	// ��ת����
	// ����һ����ʱ����ʹ��ͷ�巨�����head��nextָ����ʱ�����next
	public void reverseList(Node head){
		Node cur = head.next;
		if(cur == null || cur.next == null){ // ��û�нڵ㣬��ֻ��һ���ڵ��ʱ��Ͳ��ñ���
			return;
		}
		Node next = null; // �ݴ�cur����һ���ڵ�
		Node tempList = new Node(-1,""); // ��ʱ����
		while(cur != null){
			next = cur.next; // �ݴ���һ���ڵ㣬�����Ͽ�
			cur.next = tempList.next;
			tempList.next = cur;
			cur = next;
		}
		// ������ɺ�head��nextָ��tempList��next
		head.next = tempList.next;
	}

	// �����ӡ ʹ��stack
	public void reversePrint(Node head){
		Node cur = head.next;
		if(cur == null){
			System.out.println("����Ϊ��");
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

	// ������ӡlist
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