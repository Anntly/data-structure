package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式计算器
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
//		System.out.println("计算结果为:"+cal);

		String expression = "1+((2+3)*4)-5";
		System.out.println(toList(expression));
		System.out.println(transferTo(toList(expression)));
		System.out.println(expression+"="+cal(transferTo(toList(expression))));
	}

	// 中缀表达式转list
	public static List<String> toList(String exp){
		char[] chars = exp.toCharArray();
		List<String> result = new ArrayList<>();
		int i = 0;
		StringBuilder str = new StringBuilder();
		while(i < chars.length){
			if(chars[i] < 48 || chars[i] > 57){ // 如果是符号直接加入
				result.add(String.valueOf(chars[i]));
				i++;
			}else {
				str.delete(0,str.length());  // 清空str
				while(i < chars.length && chars[i] <= 57 && chars[i] >= 48){
					str.append(chars[i]);
					i++;
				}
				result.add(str.toString());
			}
		}
		return result;
	}

	// 中缀表达式list转后缀表达式list
	public static List<String> transferTo(List<String> exps){
		Stack<String> s1 = new Stack<>(); // 用于存储运算符
		List<String> s2 = new ArrayList<>(); // 用于存储中间结果(由于不用pop，选用ArrayList)

		for (String exp : exps) {
			if (exp.matches("\\d+")){ // 如果是数字直接加入s2
				s2.add(exp);
			} else if(exp.equals("(")){ // 如果是左括号，直接入栈s1
				s1.push(exp);
			} else if(exp.equals(")")){ // 如果是右括号，s1弹出元素加入s2，直到匹配到左括号
				while (!s1.peek().equals("(")){
					s2.add(s1.pop());
				}
				// 不需要使用左括号，直接弹出即可，右括号也不需要入栈
				s1.pop();
			} else {
				// 优先级大于s1栈顶元素，直接入栈
				// 优先级小于s1栈顶元素，弹出s1的元素加入到s2，直到优先级不小于栈顶元素就入栈
				// 要注意s1可能会弹出完所有元素为空
				while(s1.size() > 0 && getPriority(s1.peek()) >= getPriority(exp)){
					s2.add(s1.pop());
				}
				// 将exp入栈
				s1.push(exp);
			}
		}
		// 将s1剩余的所有运算符加入s2
		while (s1.size() > 0){
			s2.add(s1.pop());
		}
		return s2;
	}

	// 获取运算符的优先级
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
				System.out.println("运算符不合法:"+oper);
				return 0;
		}
	}

	// 处理表达式返回list
	public static List<String> handleExp(String exp){
		String[] arr = exp.split(" ");
		List<String> result = new ArrayList<>();
		for (String s : arr) {
			result.add(s);
		}
		return result;
	}

	// 计算逆波兰表达式结果
	public static int cal(List<String> list){
		Stack<String> stack = new Stack<>();
		for (String s : list) {
			if(s.matches("\\d+")){ // 如果是数字直接入栈
				stack.push(s);
			}else{// 如果不是就弹出两个数字进行计算
				int num1 = Integer.valueOf(stack.pop());
				int num2 = Integer.valueOf(stack.pop()); // 如果是减法与除法，由num2-num1
				if("+".equals(s)){
					stack.push((num2+num1)+"");
				}else if("-".equals(s)){
					stack.push((num2-num1)+"");
				}else if("*".equals(s)){
					stack.push(num2*num1+"");
				}else if("/".equals(s)){
					stack.push(num2/num1+"");
				}else {
					throw new RuntimeException("符号不合法");
				}
			}
		}
		// 最后剩余的即结果
		return Integer.valueOf(stack.pop());
	}
}
