package order;

import java.util.Arrays;

/**
 * ѡ������
 * ÿ��������Сֵ��ÿ�������һ��Ԫ�ؽ��н���
 * ��һ����Сֵ�������±�0���ڶ��ִ洢���±�1...
 */
public class SelectSOrt {
	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr){
		int index = 0; // ���ڴ洢��Сֵ���±�
		// �������һ��ֻ���������֣��Ͳ��ý�������ֻ��Ҫ����arr.length - 1��
		for (int i = 0; i < arr.length - 1; i++) {
			index = i; // �ٶ���СֵΪ��һ��
			for (int j = i + 1; j < arr.length; j++) { // Ҫ�����һ�������бȽϣ���ҪС��arr.length
				if (arr[j] < arr[index]){
					index = j; // ���б���СֵС�ģ����޸���Сֵ���±�
				}
			}
			if(index != i){ // �����Сֵ���±�ı䣬��ʾ�бȸ��ֵ�һ��ֵ��С�������ͽ����±�
				arr[i] = arr[i]^arr[index];
				arr[index] = arr[i]^arr[index];
				arr[i] = arr[i]^arr[index];
			}
		}
	}
}
