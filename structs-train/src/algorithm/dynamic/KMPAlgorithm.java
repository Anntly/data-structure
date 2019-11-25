package algorithm.dynamic;

import java.util.Arrays;

/**
 * �Ӵ�ƥ��
 */
public class KMPAlgorithm {

	public static void main(String[] args) {
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		int[] next = kmpNext(str2);
		System.out.println(Arrays.toString(next));
		int i = kmpSearch(str1, str2, next);
		System.out.println(i);
	}


	public static int kmpSearch(String str1,String str2,int[] next){
		for (int i = 0,j = 0; i < str1.length(); i++) {
			while (j > 0 && str1.charAt(i) != str2.charAt(j)){
				j = next[j - 1];
			}
			if(str1.charAt(i) == str2.charAt(j)){
				j++;
			}
			if(j == str2.length()){ // ������ȼ�Ϊ�ҵ�
				return i - j + 1;
			}
		}
		return -1;
	}

	/**
	 * ��ȡ�ַ���(��ѯ���Ӵ�)�Ĳ���ƥ��ֵ��
	 * �����ַ���
	 * ����������ĸ��֮ǰ���ַ���ɵ��ַ���
	 * �������һ��Ԫ�ص�˳�����ַ���Ϊ��ǰ׺����֮Ϊ��׺
	 * ǰ׺�ļ������׺�ļ��ϵĽ�����С��Ϊ��ǰ��ĸ����ƥ��ֵ
	 * @param dest  ABCDABD
	 * @return
	 */
	public static int[] kmpNext(String dest){
		// ���沿��ƥ��ֵ������
		int[] next = new int[dest.length()];
		next[0] = 0; // �ַ�������Ϊ1��ʱ�򣬲���ƥ��ֵ����0
		for (int i = 1,j = 0; i < dest.length(); i++) {
			// ��dest.charAt[i] != dest.charAt[j],��Ҫ��next[j-1]�л�ȡ�µ�j
			// ֱ��������dest.charAt[i] == dest.charAt[j]���������˳�
			while (j > 0 && dest.charAt(i) != dest.charAt(j)){
				j = next[j - 1];
			}
			if(dest.charAt(i) == dest.charAt(j)){
				j++;
			}
			next[i] = j;
		}
		return next;
	}
}
