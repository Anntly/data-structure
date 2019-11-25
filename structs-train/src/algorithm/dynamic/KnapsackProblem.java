package algorithm.dynamic;

import java.util.Arrays;

/**
 * �򵥱�������
 * 1. �����Ʒ �������Լ���ֵ
 * 2. �����̶�����������뱳������Ʒ����߼�ֵ����Ʒ�����ظ�
 */
public class KnapsackProblem {
	public static void main(String[] args) {
		int[] val = {1500,3000,2000}; // ��Ʒ��ֵ���±�ڼ�����Ʒ
		int[] w = {1,4,3}; // ��Ʒ����
		int m = 4; // ��������
		int n = val.length;
		// v[i][j]������� j������ʱ���ܴ�ŵ����ļ�ֵ
		// i �ڼ�����Ʒ  j ����������,�����������������
		int[][] v = new int[n + 1][m + 1];
		// ��ά����ĵ�һ�к͵�һ�в���ż�ֵ��Ĭ��Ϊ0��Ϊ�˱�֤�㷨��Խ��
		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < v[0].length; j++) {
				if(w[i-1] > j){ // �����ǰ��Ʒ�����ȵ�ǰ������ֱ��ȡ��һ����Ʒ��ʱ�������ֵ
					v[i][j] = v[i - 1][j];
				}else { // С�ڵ�ʱ����Ҫ����ǰ���ֵ��֮ǰ������ֵ���бȽ�
					v[i][j] = Math.max(v[i-1][j],val[i - 1] + v[i-1][j - w[i - 1]]);
				}
			}
		}

		for (int[] ints : v) {
			System.out.println(Arrays.toString(ints));
		}
	}
}
