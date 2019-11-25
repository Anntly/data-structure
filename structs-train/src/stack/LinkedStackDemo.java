package stack;

public class LinkedStackDemo {

	public static void main(String[] args) {
		LinkedStack stack = new LinkedStack();
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		//stack.pop();
		stack.print();
	}
}
class LinkedStack{
	private Node head = new Node(-1);

	// Ê¹ÓÃÍ·²å
	public void push(int no){
		Node node = new Node(no);
		node.setNext(head.getNext());
		head.setNext(node);
	}

	public int pop(){
		Node temp = head.getNext();
		head.setNext(head.getNext().getNext());
		return temp.getNo();
	}

	public void print(){
		Node cur = head.getNext();
		while(cur != null){
			System.out.println(cur.getNo());
			cur = cur.getNext();
		}
	}

}

class Node{
	private int no;
	private Node next;

	public Node(int no){
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node{" +
				"no=" + no +
				'}';
	}
}