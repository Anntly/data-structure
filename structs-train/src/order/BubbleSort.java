package order;

import java.util.Arrays;

/**
 * ð������
 * ÿ�ν����(��С)�������ŵ����
 * ʱ�临�Ӷ�ΪO(n^2)
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr){
		boolean flag = false; // �����ж��Ƿ��Ѿ��ź���
		// ֻ��Ҫ����arr.length - 1�־Ϳ�������
		// ��Ϊarr.length - 1�����źú�ʣ�µ��Ǹ����־�����С(���)�ģ��Ͳ���������
		for (int i = 0; i < arr.length - 1; i++) { // ��С����
			for (int j = 0; j < arr.length - 1 - i; j++) {
				// �����Աȣ���һ��ֻ��Ҫ�Ա�arr.length -1 -0 ��
				// �ڶ����������������Ѿ�������������Ҫ�Ա� arr.length -1 - 1�Σ��Դ�����
				if(arr[j] > arr[j+1]){
					arr[j] = arr[j]^arr[j+1];
					arr[j+1] = arr[j]^arr[j+1];
					arr[j] = arr[j]^arr[j+1];
					flag = true; // ����������Ϊ true
				}
			}
			if(!flag){ // ��һ������δ���й��������ʹ����Ѿ��ź���
				break;
			}else {
				flag = false;
			}
		}
	}
}
