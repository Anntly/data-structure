package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort {

	public static void main(String[] args) {
//		int[] arr = {1,7,2,9,8,5,7};
//		heapSort(arr);
//		System.out.println(Arrays.toString(arr));
	}

	/**
	 * ÿ�ι����������֮�����Ľڵ�ͻύ���������һ��λ��
	 * ֻ��Ҫ����һ��Ԫ�ؽ���������λ�ã��ٽ�arr.length-1 ��Ԫ���ٽ��й����ͻ�õ��ڶ�������Ԫ��
	 * @param arr
	 */
	public static void heapSort(int[] arr){
		buildMaxHeap(arr); // �����ö����
		for (int i = arr.length - 1; i >= 1 ; i--) {
			// �������һ��Ԫ�أ�������Ԫ��������Ԫ�ؽ���(��ǰ��������Ԫ��)
			arr[i] = arr[0]^arr[i];
			arr[0] = arr[0]^arr[i];
			arr[i] = arr[0]^arr[i];
			maxHeapify(arr,0,i); // ��Ϊ0��������Ԫ���ˣ��ٽ�����Ԫ�������ἴ��
		}
	}

	// ���������
	public static void buildMaxHeap(int[] arr){
		// ��ȫ���������ڵ��±�� arr.length/2��arr.length-1 �Ľڵ㶼��Ҷ�ӽڵ�
		// Ҷ�ӽ�㣬�����ӽڵ㶼Ϊ�գ���Ҷ�ӽ�����һ������
		// �ӵ����Ͻ��б��������ѣ��ӵ�һ����ΪҶ�ӽڵ�Ľڵ㿪ʼ
		for (int i = arr.length/2 - 1; i >= 0 ; i--) {
			maxHeapify(arr,i,arr.length);
		}
	}

	/**
	 *	����ǰ�ڵ��Լ��������ӽڵ�����ֵ���ڵ�ǰ�ڵ�
	 * @param arr ���������
	 * @param i   ���ڵ���±�
	 * @param length ����
	 */
	public static void maxHeapify(int[] arr,int i,int length){
		int left = 2*i+1;
		int max;

		if(left < length && arr[left] > arr[i]){ // �����ӽڵ��뵱ǰ�ڵ��ҳ����
			max = left;
		}else {
			max = i;
		}
		if(left+1 < length && arr[left+1] > arr[max] ){ // �����ӽڵ��뵱ǰ�ڵ��ҳ����
			max = left+1;
		}

		if(max != i){
			arr[max] = arr[max]^arr[i];
			arr[i] = arr[max]^arr[i];
			arr[max] = arr[max]^arr[i];
			maxHeapify(arr,max,length); // ��Ҫ�����ݹ�鿴��������ӽڵ��Ƿ������
		}
	}
}
