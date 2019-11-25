package order;

import java.util.Arrays;

/**
 * ϣ������
 * 1. ���ڲ������򣬵������б�����������ʱ�򣬺���������б�����ֵ�ֺ�С��
 * 	  ��Ҫ������λ��Ԫ�����࣬�ͻ������಻��Ҫ����λ����
 * 2. ���ղ������з��飬��η����������������
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7,32,-10};
		sort2(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 1. ������ʵ��(Ч�ʵ�,��ð�ݻ���)
	 * ѭ����length/2�õ�gap,gapΪ����
	 * ���ղ�����������з��飬����֮���Ԫ�ؽ��н���
	 * ��lengthѭ����2��gapΪ1��ʱ�򣬻�������һ������
	 */
	public static void sort1(int[] arr){
		for (int gap = arr.length/2; gap > 0; gap/=2) { // ��ȡ����
			for (int i = gap; i < arr.length; i++) { // ���±�Ϊ(�±�0+gap����)��Ԫ�ؿ�ʼ�����Ƚ�
				for (int j = i - gap; j >= 0; j -= gap) { // ��֮ǰ�Ȳ�����Ԫ�ؽ��бȽ�
					if(arr[j] > arr[j+gap]){
						arr[j] = arr[j]^arr[j+gap];
						arr[j+gap] = arr[j]^arr[j+gap];
						arr[j] = arr[j]^arr[j+gap];
					}
				}
			}
		}
	}

	/**
	 * 2. ��λ��ʵ��(������)
	 *    ��ʹ�ý��������ǽ������Ҫ������λ�ã�����Ԫ�غ��ƣ�����ԽС��ʱ���ƶ�Խ��
	 *  ������е�   1 3 4 5 6 7 8 9 2 0  Ҫ��2�ŵ�3ǰ��
	 *  ʹ�ý����ķ�ʽ ÿ����һ�����������ִ�У���Ҫִ��24��
	 *  ��ʹ����λ�ķ�ʽ ��ֻ��Ҫ 8+1 = 9��
	 */
	 public static void sort2(int[] arr){
	 	 int index; // ��ű��Ƚϵ����򲿷ֵ��±�
	 	 int value; // ���Ҫ�Ƚϵ����򲿷ֵ�ֵ
		 for (int gap = arr.length/2; gap > 0 ; gap/=2) {  // ����
			 for (int i = gap; i < arr.length; i+=gap) {
				value = arr[i];
				index = i - gap;
				while(index >= 0 && arr[index] > value){
					// ���򲿷֣�index�±�֮���Ԫ�غ���
					arr[index+gap] = arr[index];
					index -= gap;
				}
				if(index != i-gap){ // ��Ҫ�����λ�ø�ֵ
					arr[index+gap] = value;
				}
			 }
		 }
	 }
}
