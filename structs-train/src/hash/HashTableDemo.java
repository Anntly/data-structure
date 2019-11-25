package hash;

/**
 * ��ϣ��򵥰���
 * 1. ���ݽṹ������+����ʵ��,�������ŵ�������
 * 2. ÿ��Ԫ�� ��ӦͰλ���� ȡ��ʵ��
 * 3. ����ʹ�÷��ͣ�ʹ��hashCode������ȡhashCode,��hashCode��(���鳤��-1)�� & ���㣬Ҳ���Ի�ȡ������±꣬
 *    ������Ҫ��֤���鳤�Ⱦ���Ϊż��(����±�Ϊ����)����Ȼֻ��ֲ���ż���±�
 */
public class HashTableDemo {

	public static void main(String[] args) {
		HashTable hashTable = new HashTable(10);
		int no;
		String name;
		Emp emp;
		for (int i = 0; i < 50; i++) {
			emp = new Emp(1000+i,"����"+i);
			hashTable.add(emp);
		}
		System.out.println(hashTable.find(1049));
		hashTable.remove(1049);
		System.out.println(hashTable.find(1049));
	}
}

class HashTable{
	private int size; // ���鳤��
	private EmpLinkedList[] arr;

	public HashTable(int size) {
		this.size = size;
		arr = new EmpLinkedList[size];
		// ��ʼ��ÿ�������ͷ���
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

	// ͨ��Ա�����ȡ���ȡ���λ�õ��±�
	public int index(int no){
		return no % size;
	}
}

class EmpLinkedList{
	private Emp head; // ����ͷָ��

	public void add(Emp emp){ // ��ӵ�β��
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
			System.out.println("δ�ҵ�Ҫɾ����Ԫ��");
			return;
		}
		Emp cur = head;
		if(cur.getNext().getNo() == no){ // ����һ��Ϊ�ڵ�ʱ
			cur.setNext(cur.getNext().getNext());
		}
		while (cur.getNext() != null){ // �ҵ�Ҫɾ����ǰһ��
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