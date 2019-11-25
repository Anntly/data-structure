package stack;

public class ArrayStackDemo {

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(10);
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		stack.pop();
		stack.print();
	}
}

class ArrayStack{
	private int maxSize;
	private int top = -1;
	private int[] arr;

	public ArrayStack(int size) {
		arr = new int[size];
		maxSize = size;
	}

	public boolean isFull(){
		return top == (maxSize-1);
	}

	public boolean isEmpty(){
		return top == -1;
	}

	public void push(int value){
		if(isFull()){
			System.out.println("ջ����");
			return;
		}
		top++;
		arr[top] = value;
	}

	public int pop(){
		if(isEmpty()){
			throw new RuntimeException("ջ�ѿ�");
		}
		int value = arr[top];
		top --;
		return value;
	}

	public void print(){
		// �������
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}
}