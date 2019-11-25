package hash;

/**
 * 哈希表简单案例
 * 1. 数据结构由数组+链表实现,即数组存放的是链表
 * 2. 每个元素 对应桶位置由 取余实现
 * 3. 可以使用泛型，使用hashCode方法获取hashCode,用hashCode与(数组长度-1)做 & 运算，也可以获取数组的下标，
 *    不过需要保证数组长度尽量为偶数(最大下标为基数)，不然只会分布在偶数下标
 */
public class HashTableDemo {

	public static void main(String[] args) {
		HashTable hashTable = new HashTable(10);
		int no;
		String name;
		Emp emp;
		for (int i = 0; i < 50; i++) {
			emp = new Emp(1000+i,"工人"+i);
			hashTable.add(emp);
		}
		System.out.println(hashTable.find(1049));
		hashTable.remove(1049);
		System.out.println(hashTable.find(1049));
	}
}

class HashTable{
	private int size; // 数组长度
	private EmpLinkedList[] arr;

	public HashTable(int size) {
		this.size = size;
		arr = new EmpLinkedList[size];
		// 初始化每个链表的头结点
		for (int i = 0; i < size; i++) {
			arr[i] = new EmpLinkedList();
		}
	}

	public void add(Emp emp){
		int index = index(emp.getNo());
		arr[index].add(emp);
	}

	public Emp find(int no){
		int index = index(no);
		Emp emp = arr[index].find(no);
		return emp;
	}

	public void remove(int no){
		int index = index(no);
		arr[index].remove(no);
	}

	// 通过员工编号取余获取存放位置的下标
	public int index(int no){
		return no % size;
	}
}

class EmpLinkedList{
	private Emp head; // 虚拟头指针

	public void add(Emp emp){ // 添加到尾部
		if (head == null) {
			head = emp;
			return;
		}
		Emp cur = head;
		while (true){
			if(cur.getNext() == null){
				break;
			}
			cur = cur.getNext();
		}
		cur.setNext(emp);
	}

	public Emp find(int no){
		if(head == null){
			return null;
		}
		Emp cur = head;
		while (true){
			if(cur == null){
				return null;
			}
			if (cur.getNo() == no){
				return cur;
			}
			cur = cur.getNext();
		}
	}

	public void remove(int no){
		if(head == null){
			System.out.println("未找到要删除的元素");
			return;
		}
		Emp cur = head;
		if(cur.getNext().getNo() == no){ // 当第一个为节点时
			cur.setNext(cur.getNext().getNext());
		}
		while (cur.getNext() != null){ // 找到要删除的前一个
			if(cur.getNext().getNo() == no){
				cur.setNext(cur.getNext().getNext());
				break;
			}
			cur = cur.getNext();
		}
	}

	public void print(){
		if(head == null){
			return;
		}
		Emp cur = head;
		while(cur != null){
			System.out.println(cur.getNo()+":"+cur.getName()+" ");
			cur = cur.getNext();
		}
	}
}

class Emp{
	private int no;
	private String name;
	private Emp next;

	public Emp(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public Emp getNext() {
		return next;
	}

	public void setNext(Emp next) {
		this.next = next;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Emp{" +
				"no=" + no +
				", name='" + name + '\'' +
				'}';
	}
}