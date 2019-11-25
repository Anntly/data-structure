package algorithm.dynamic;

/**
 * ��һ���߶���10��̨�׵�¥�ݣ����������ߣ�ÿ��һ��ֻ������1������2��̨�ס�Ҫ���ó��������һ���ж������߷���
 * 1. ��������˼ά�����������һ��̨�׵�ʱ��ֻ��һ���߷�
 * 2. ����̨���������߷�
 * 3. f(10) = f(9) + f(8),�������һ��̨�����������̨�׿��Է�Ϊ�����ּ�f(9)��f(8),ʮ��̨�׵��߷���Ϊ8��̨����9��̨���߷�֮��
 * 4. һ������
 */
public class StepProblem {

	public static void main(String[] args) {
		System.out.println(step2(10));
	}

	// �õݹ���
	// ʹ�����ַ�ʽ����ж�β���Ҫ�ļ���
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

	// ʹ�ö�̬�滮
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
