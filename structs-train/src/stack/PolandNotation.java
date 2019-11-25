package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * �沨�����ʽ������
 */
public class PolandNotation {

	private static final int ADD = 1;
	private static final int SUBTRACT = 1;
	private static final int MULTIPLY = 2;
	private static final int DIVIDE = 2;

	public static void main(String[] args) {
//		String expression = "30 4 + 5 * 6 -";
//		List<String> strings = handleExp(expression);
//		int cal = cal(strings);
//		System.out.println("������Ϊ:"+cal);

		String expression = "1+((2+3)*4)-5";
		System.out.println(toList(expression));
		System.out.println(transferTo(toList(expression)));
		System.out.println(expression+"="+cal(transferTo(toList(expression))));
	}

	// ��׺���ʽתlist
	public static List<String> toList(String exp){
		char[] chars = exp.toCharArray();
		List<String> result = new ArrayList<>();
		int i = 0;
		StringBuilder str = new StringBuilder();
		while(i < chars.length){
			if(chars[i] < 48 || chars[i] > 57){ // ����Ƿ���ֱ�Ӽ���
				result.add(String.valueOf(chars[i]));
				i++;
			}else {
				str.delete(0,str.length());  // ���str
				while(i < chars.length && chars[i] <= 57 && chars[i] >= 48){
					str.append(chars[i]);
					i++;
				}
				result.add(str.toString());
			}
		}
		return result;
	}

	// ��׺���ʽlistת��׺���ʽlist
	public static List<String> transferTo(List<String> exps){
		Stack<String> s1 = new Stack<>(); // ���ڴ洢�����
		List<String> s2 = new ArrayList<>(); // ���ڴ洢�м���(���ڲ���pop��ѡ��ArrayList)

		for (String exp : exps) {
			if (exp.matches("\\d+")){ // ���������ֱ�Ӽ���s2
				s2.add(exp);
			} else if(exp.equals("(")){ // ����������ţ�ֱ����ջs1
				s1.push(exp);
			} else if(exp.equals(")")){ // ����������ţ�s1����Ԫ�ؼ���s2��ֱ��ƥ�䵽������
				while (!s1.peek().equals("(")){
					s2.add(s1.pop());
				}
				// ����Ҫʹ�������ţ�ֱ�ӵ������ɣ�������Ҳ����Ҫ��ջ
				s1.pop();
			} else {
				// ���ȼ�����s1ջ��Ԫ�أ�ֱ����ջ
				// ���ȼ�С��s1ջ��Ԫ�أ�����s1��Ԫ�ؼ��뵽s2��ֱ�����ȼ���С��ջ��Ԫ�ؾ���ջ
				// Ҫע��s1���ܻᵯ��������Ԫ��Ϊ��
				while(s1.size() > 0 && getPriority(s1.peek()) >= getPriority(exp)){
					s2.add(s1.pop());
				}
				// ��exp��ջ
				s1.push(exp);
			}
		}
		// ��s1ʣ����������������s2
		while (s1.size() > 0){
			s2.add(s1.pop());
		}
		return s2;
	}

	// ��ȡ����������ȼ�
	public static int getPriority(String oper){
		switch (oper){
			case "+":
				return ADD;
			case "-":
				return SUBTRACT;
			case "*":
				return MULTIPLY;
			case "/":
				return DIVIDE;
			default:
				System.out.println("��������Ϸ�:"+oper);
				return 0;
		}
	}

	// ������ʽ����list
	public static List<String> handleExp(String exp){
		String[] arr = exp.split(" ");
		List<String> result = new ArrayList<>();
		for (String s : arr) {
			result.add(s);
		}
		return result;
	}

	// �����沨�����ʽ���
	public static int cal(List<String> list){
		Stack<String> stack = new Stack<>();
		for (String s : list) {
			if(s.matches("\\d+")){ // ���������ֱ����ջ
				stack.push(s);
			}else{// ������Ǿ͵����������ֽ��м���
				int num1 = Integer.valueOf(stack.pop());
				int num2 = Integer.valueOf(stack.pop()); // ����Ǽ������������num2-num1
				if("+".equals(s)){
					stack.push((num2+num1)+"");
				}else if("-".equals(s)){
					stack.push((num2-num1)+"");
				}else if("*".equals(s)){
					stack.push(num2*num1+"");
				}else if("/".equals(s)){
					stack.push(num2/num1+"");
				}else {
					throw new RuntimeException("���Ų��Ϸ�");
				}
			}
		}
		// ���ʣ��ļ����
		return Integer.valueOf(stack.pop());
	}
}
