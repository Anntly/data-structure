package recursion;

/**
 * �˻ʺ�����
 * 1. ʹ��һλ�����������ά������д洢,�±����ڼ��м��ڼ�������-1��value����ڼ���-���ڷŵ�λ��-1
 */
public class EightQueue {

	private int MAX; // max*max�������
	private int[] arr;
	private int count; // ���ж����ֽⷨ

	public static void main(String[] args) {
		EightQueue eightQueue = new EightQueue(8);
		eightQueue.play(0); // �ӵ�һ�����ӿ�ʼ��
		System.out.println("����:"+eightQueue.count+"�ֽⷨ");
	}

	public EightQueue(int n){
		MAX = n;
		arr = new int[MAX];
	}

	// ����
	public void play(int n){
		if(n == MAX){ // ��n=8��ʱ�򣬴���֮ǰ8�������Ѿ�����
			print(); //��ӡ���
			count++;
			return;
		}
		// ���ڵ�n�����ӣ����н��б���
		for (int i = 0; i < MAX; i++) {
			arr[n] = i; // �ڷ����ӣ���һ�ξ��ڵ�һ�У�֮���ۼ�
			if(isSuccess(n)){ // �жϰڷŵ����ӣ�λ���Ƿ��ͻ
				play(n+1); // �������ͻ���Ͱڷ���һ������
			}
			// �����ͻ���ͼ�������������ͻ��λ��
			// ��ջ����add(n+1)ִ�е��˵�ʱ�򣬼���ѭ��ִ��֮���ѭ���ڵ��ж�����λ�ã��Ƿ��ͻ
			// ����������������ǰ�Ľڵ���л��ݣ��Ϳ��Ի�ȡ�����в��ظ��Ľ���
		}

	}

	// �жϵ�n�����Ӱڷ��Ƿ��ͻ(�Ƿ�ͬһ��/��/б��)
	public boolean isSuccess(int n){
		// ���ڸ������Ӷ�����һ���±꣬����Ҫ�ж��Ƿ�ͬһ��
		// arr[i] == arr[n] ������֮ǰ��������ͬһ��
		// Math.abs(n-i) == Math.abs(arr[n]-arr[i]) ������֮ǰ��������ͬһб����(������֮�����������֮��)
		for (int i = 0; i < n; i++) {
			if(arr[i] == arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
				return false;
			}
		}
		return true;
	}

	// ��ӡ���
	public void print(){
		for (int i = 0; i < arr.length; i++) {
			System.out.print((arr[i]+1)+"  ");
		}
		System.out.println();
	}

	public int getCount() {
		return count;
	}
}
