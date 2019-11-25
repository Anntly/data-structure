package stack;

/**
 * ������
 */
public class Calculator {

	public static void main(String[] args) {
		String expression = "1000+100*3-2/2";

		CalStack numSTack = new CalStack(10);
		CalStack operSTack = new CalStack(10);
		int num1 = 0;
		int num2 = 0;
		char oper = ' ';
		int count = 1; // ��λ��һ���ж���λ-1
		String numTemp = ""; // ������Ϊ��λ��ʱ��
		char[] chars = expression.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if(isOper(chars[i])){
				if(!operSTack.isEmpty()){ // �����Ϊ�գ��Ա����ȼ������С�ڵ��ڲ���ջջ�����ȼ�������ջ��ȡ������������ջ
					if(getPriority(chars[i]) <= getPriority((char) operSTack.peek())){
						num1 = numSTack.pop();
						num2 = numSTack.pop();
						oper = (char) operSTack.pop();
						int cal = cal(num1, num2, oper);

						numSTack.push(cal);
						operSTack.push(chars[i]);
					}else { // ���ȼ��ϴ��ֱ����ջ
						operSTack.push(chars[i]);
					}
				}else{ // ���Ϊ��ֱ����ջ
					operSTack.push(chars[i]);
				}
			}else { // ���ǲ�����ֱ������ջ
				// ��������һλ��ֱ������ջ
				if(i == chars.length-1){
					// �ж�����λ��
					numSTack.push(chars[i]-48); // char�����ASCLL��ֵ
				}else {
					numTemp+=chars[i];
					while (!isOper(chars[i+count])){
						numTemp+=chars[i+count];
						count++;
					}
					numSTack.push(Integer.valueOf(numTemp));
					i = i + count - 1; // -1����Ϊ���count++�ᵼ�¶��1����count��ʼֵΪ1

					numTemp = "";  // ����
					count = 1;
				}
			}
		}

		// ��ջʣ���Ԫ�ؽ��м���
		while(true){
			// ��������ջΪ����ֹ
			if(operSTack.isEmpty()){
				break;
			}
			num1 = numSTack.pop();
			num2 = numSTack.pop();
			oper = (char) operSTack.pop();
			int cal = cal(num1, num2, oper);
			numSTack.push(cal);
		}
		// ������ջ��ֻʣһ��
		System.out.println("������:"+expression+"="+numSTack.pop());
	}

	// �ж��Ƿ�Ϊ�����
	public static boolean isOper(char ch){
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	// ����
	public static int cal(int num1,int num2,char oper){
		switch (oper){
			case '+':
				return num1 + num2;
			case '-': // ����ջ���Ƚ���������Ժ����num2Ϊ֮ǰ�ļ���ֵ
				return num2 - num1;
			case '*':
				return num1 * num2;
			case '/':
				return num2/num1;
			default:
				System.out.println("���Ŵ���");
				return 0;
		}
	}

	// ��ȡ���ȼ�
	public static int getPriority(char ch){
		if(ch == '+' || ch == '-'){
			return 1;
		}else if(ch == '*' || ch == '/'){
			return 2;
		}
		return 0;
	}
}
class CalStack{
	private int maxSize;
	private int top = -1;
	private int[] arr;

	public CalStack(int size) {
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

	public int peek(){
		return arr[top];
	}

	public void print(){
		// �������
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}
}