package algorithm.dynamic;

/**
 * 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
 * 1. 可以逆向思维，当最后已有一阶台阶的时候只有一种走法
 * 2. 两阶台阶有两种走法
 * 3. f(10) = f(9) + f(8),由于最后一阶台阶与最后两阶台阶可以分为两部分即f(9)与f(8),十个台阶的走法就为8个台阶月9个台阶走法之和
 * 4. 一次类推
 */
public class StepProblem {

	public static void main(String[] args) {
		System.out.println(step2(10));
	}

	// 用递归解决
	// 使用这种方式会进行多次不必要的计算
	public static int step1(int n){
		if(n < 1){
			return 0;
		}
		if(n == 1){
			return 1;
		}
		if(n == 2){
			return 2;
		}
		return step1(n-1)+step1(n-2);
	}

	// 使用动态规划
	public static int step2(int n){
		if(n < 1){
			return 0;
		}
		if(n == 1){
			return 1;
		}
		if(n == 2){
			return 2;
		}
		int a = 1;
		int b = 2;
		int temp = 0;
		for (int i = 3; i <= n; i++) {
			temp = a + b;
			a = b;
			b = temp;
		}
		return temp;
	}
}
