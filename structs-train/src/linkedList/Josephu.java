package linkedList;

public class Josephu {

	public static void main(String[] args) {
		CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
		linkedList.add(5);
		//linkedList.print();
		linkedList.count(1,2,5); // 2-4-1-5-3
	}
}

/**
 * ����ѭ������:
 * 		1. ��Ҫһ��first�ڵ�һֱָ��ͷ���
 * 		2. ��Ҫһ��cur�ڵ���б���
 * 		3. ����Ҫ�յ�head�ڵ㣬head.next����head����
 * 	    4. ��ӽڵ��ʱ�� cur.next = �½ڵ�, �½ڵ�.next = first ����
 * 	    5. ������ʱ��ֻ��Ҫ�ж�cur�Ƿ�Ϊhead����
 */
class CircleSingleLinkedList{

	private JNode first = null;

	/**
	 * ���ָ�������Ľڵ�
	 * @param num
	 */
	public void add(int num){
		if(num < 1){ // ��֤����һ���ڵ�
			System.out.println("��β��Ϸ�");
			return;
		}
		JNode cur = null;
		for (int i = 1; i <= num; i++) {
			JNode node = new JNode(i);
			if(i == 1){ // ����ӵ�һ���ڵ�
				first = node;
				first.setNext(first);
				cur = first; // ָ��first
			}else {
				cur.setNext(node);
				node.setNext(first);
				cur = cur.getNext();
			}
		}
	}

	/**
	 * 1. ���ڵ���������ԣ�����first�ڵ�֮�⣬��Ҫһ��helper�ڵ�ָ��first��ǰһ���ڵ㣬����ɾ��
	 * 2. ����firstָ��Ľڵ�ҲҪ������ÿ���ƶ�ֻ��Ҫ�ƶ� move - 1���ڵ�
	 * 3. ��һ�α�����ʼ��λ��Ҳֻ��Ҫ�ƶ� start-1 ��
	 * @param start  ��һ�α�����ʼ��λ��
	 * @param move   ÿ�α�����Ҫ�ƶ���λ��
	 * @param num    һ���ж��ٸ�node
	 */
	public void count(int start,int move,int num){
		if(first == null || start > num || move < 1){
			System.out.println("��β��Ϸ�");
			return;
		}
		// ��ȡhelper�ڵ�λ��
		JNode helper = first;
		while (true) {
			if (helper.getNext() == first){
				break;
			}
			helper = helper.getNext();
		}
		// ��helper��first�ƶ���start��λ��
		for (int i = 1; i < start; i++) {
			helper = helper .getNext();
			first = first.getNext();
		}

		// ��ʼ������Ȧ
		while (true) {
			if(helper == first){ // ��helper �� firstָ��ͨһ���ڵ��ʱ�������ֻʣ��һ���ڵ�
				System.out.println("����Ȧ�ı��:"+helper.getNo());
				break;
			}
			// ��ʼ�ƶ�
			for (int i = 1; i < move; i++) {
				helper = helper.getNext();
				first = first.getNext();
			}
			System.out.println("��Ȧ�ı��Ϊ:"+first.getNo());
			// ɾ��first�ڵ�   first����һλ��helper.nextָ��first����
			first = first.getNext();
			helper.setNext(first);
		}
	}

	/**
	 * ������ǰ����
	 */
	public void print(){
		if(first == null){
			System.out.println("����Ϊ��");
			return;
		}
		JNode cur = first;
		while(true){
			System.out.println("��ǰ���:"+cur.getNo());
			if(cur.getNext().equals(first)){
				break;
			}
			cur = cur.getNext();
		}
	}
}

class JNode {
	private int no;
	private JNode next;

	public JNode (int no){
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public JNode getNext() {
		return next;
	}

	public void setNext(JNode next) {
		this.next = next;
	}
}