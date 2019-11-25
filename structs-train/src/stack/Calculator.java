package stack;

/**
 * 计算器
 */
public class Calculator {

	public static void main(String[] args) {
		String expression = "1000+100*3-2/2";

		CalStack numSTack = new CalStack(10);
		CalStack operSTack = new CalStack(10);
		int num1 = 0;
		int num2 = 0;
		char oper = ' ';
		int count = 1; // 多位数一共有多少位-1
		String numTemp = ""; // 当数字为多位的时候
		char[] chars = expression.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if(isOper(chars[i])){
				if(!operSTack.isEmpty()){ // 如果不为空，对比优先级，如果小于等于操作栈栈顶优先级，从数栈获取两个数，操作栈
					if(getPriority(chars[i]) <= getPriority((char) operSTack.peek())){
						num1 = numSTack.pop();
						num2 = numSTack.pop();
						oper = (char) operSTack.pop();
						int cal = cal(num1, num2, oper);

						numSTack.push(cal);
						operSTack.push(chars[i]);
					}else { // 优先级较大就直接入栈
						operSTack.push(chars[i]);
					}
				}else{ // 如果为空直接入栈
					operSTack.push(chars[i]);
				}
			}else { // 不是操作符直接入数栈
				// 如果是最后一位就直接入数栈
				if(i == chars.length-1){
					// 判断数字位数
					numSTack.push(chars[i]-48); // char传入的ASCLL码值
				}else {
					numTemp+=chars[i];
					while (!isOper(chars[i+count])){
						numTemp+=chars[i+count];
						count++;
					}
					numSTack.push(Integer.valueOf(numTemp));
					i = i + count - 1; // -1是因为最后count++会导致多出1，且count初始值为1

					numTemp = "";  // 归零
					count = 1;
				}
			}
		}

		// 对栈剩余的元素进行计算
		while(true){
			// 当操作符栈为空终止
			if(operSTack.isEmpty()){
				break;
			}
			num1 = numSTack.pop();
			num2 = numSTack.pop();
			oper = (char) operSTack.pop();
			int cal = cal(num1, num2, oper);
			numSTack.push(cal);
		}
		// 最终数栈就只剩一个
		System.out.println("计算结果:"+expression+"="+numSTack.pop());
	}

	// 判断是否为运算符
	public static boolean isOper(char ch){
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	// 计算
	public static int cal(int num1,int num2,char oper){
		switch (oper){
			case '+':
				return num1 + num2;
			case '-': // 由于栈是先进后出，所以后出的num2为之前的计算值
				return num2 - num1;
			case '*':
				return num1 * num2;
			case '/':
				return num2/num1;
			default:
				System.out.println("符号错误");
				return 0;
		}
	}

	// 获取优先级
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
			System.out.println("栈已满");
			return;
		}
		top++;
		arr[top] = value;
	}

	public int pop(){
		if(isEmpty()){
			throw new RuntimeException("栈已空");
		}
		int value = arr[top];
		top --;
		return value;
	}

	public int peek(){
		return arr[top];
	}

	public void print(){
		// 逆向便利
		for (int i = top; i >= 0; i--) {
			System.out.println(arr[i]);
		}
	}
}