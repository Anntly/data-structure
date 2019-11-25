package order;

import java.util.Arrays;

/**
 * ��������
 * ��Ҫ����������Ϊ�������δ����������
 * ÿ�δ�δ����ĵ�һ����������������һ����(������ǰ)��ʼ�Ƚϣ����δ���������С�ͽ���λ��
 * Ч�����ǽ�δ����������뵽����õĲ������棬����֮���Ԫ�غ���
 */
public class InsertSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		sort1(arr);
		System.out.println(Arrays.toString(arr));
	}

	// �����ķ�ʽ�����ܽϲ�
	public static void sort(int[] arr){
		for (int i = 1; i < arr.length; i++) { // ��һ�μٶ��±�Ϊ0�����ź���,length-i��Ϊδ����
			// ��δ����ĵ�һ��Ԫ�أ�����������õĽ��бȽϣ������С�ͽ���
			for (int j = i; j > 0 && arr[j] < arr[j-1] ; j--) {
				arr[j] = arr[j]^arr[j-1];
				arr[j-1] = arr[j]^arr[j-1];
				arr[j] = arr[j]^arr[j-1];
			}
		}
	}

	// ��λ�ķ�ʽ
	public static void sort1(int[] arr){
		int index; // ��Ҫ�������б��һ��Ԫ�ؽ��жԱȵ������б���±꣬�������б�����һ��Ԫ�ؿ�ʼ�ݼ�
		int value; // �����б��һ��Ԫ�ص�ֵ
		for (int i = 1; i < arr.length; i++) {
			index = i - 1;
			value = arr[i];
			while (index >= 0 && value < arr[index]){
				// ��������б��һ��ֵС�������index�±��Ԫ�أ��ͽ�index֮�������Ԫ�ض�����
				arr[index+1] = arr[index];
				index --;
			}
			if(index != i-1){ // ���index��ֵ�ı��˾ͽ�ֵ���뵽��λ��
				arr[index + 1] = value; // �����˳�while��ʱ�����1����Ҫ����
			}
		}
	}
}
