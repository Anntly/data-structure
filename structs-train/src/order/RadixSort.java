package order;

import java.util.Arrays;

/**
 * ��������(��Ҫ�ŵ�������ʱ������Ҫ����9���ȳ������飬���ܻ�����)
 * ʹ��9��һλ���飬��0-9�ź�
 * ��Ҫ����ĵ������Ԫ�أ��Ӹ�λ��ʼ����λ��9������ƥ��ͷ�������
 * һ�ֽ����󣬰�������Ÿ����飬�����Ż�ԭ���飬��λ�������
 * ������ʮλ����λ������ֱ���������ֵ�λ��Ϊֹ���������
 */
public class RadixSort {

	public static void main(String[] args) {
		int[] arr = {1,2,9,8,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// ֻ֧������(��С���)
	public static void sort(int[] arr){
		// �ҳ��������������֣������λ��(��������Ҫѭ���Ĵ���)
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(max < arr[i]){
				max = arr[i];
			}
		}
		int loopCount = (max+"").length();

		// ����һ����ά������ΪͰ��0-9���洢����Ҫ�����Ԫ�أ����ո�/ʮ/�١�����λ��
		int [][] bucket = new int[10][arr.length];
		// ָ��Ͱ�ĳ��ȵ�ָ��
		int [] bucketIndex = new int[10];
		//
		int bit = 0;
		for (int i = 0,n=1; i < loopCount; i++,n*=10) {
			for (int j = 0; j < arr.length; j++) { // ����һ��arr����Ԫ�ط��ڶ�Ӧ��Ͱ��
				// ���Ԫ����һ��λ��
				bit = (arr[j]/n)%10;
				// ��Ԫ�ط����±����λ��ֵ��ȵ�λ��
				bucket[bit][bucketIndex[bit]] = arr[j];
				bucketIndex[bit]++; // ָ�����
			}

			// �����ַ���Ͱ��Ԫ�طŻ����飬������ǰλ�ź���
			int index = 0;
			for (int j = 0; j < bucket.length; j++) {
				// ����Ͱ����ֵ��ʱ����Ż�����
				if(bucketIndex[j] > 0){
					for (int k = 0; k < bucketIndex[j]; k++) {
						arr[index] = bucket[j][k];
						index++;
					}
				}
				bucketIndex[j] = 0; // ��ָ�����㣬��֤��һ�ΰ�λ����
			}
		}
	}
}
