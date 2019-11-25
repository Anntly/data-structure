package order;

import java.util.Arrays;

/**
 * �鲢����(���η�)
 * 1. ������������ƣ���ʹ���˷��η���˼��
 * 2. ������ݹ���飬ֱ����������һ��
 * 3. ������->�ϲ���ֱ��ȫ���ϲ����
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {1,-2,9,8,5,7};
		int[] temp = new int[arr.length];
		mergeSort(arr,0,arr.length-1,temp);
		System.out.println(Arrays.toString(arr));
	}


	public static void mergeSort(int[] arr,int left,int right,int[] temp){
		if(left >= right){ // ��left���ڵ���right��ʱ�����
			return;
		}
		int mid = (left+right)/2;
		// ����ݹ�
		mergeSort(arr,left,mid,temp);
		// ���ҵݹ�
		mergeSort(arr,mid+1,right,temp);
		// �ϲ�
		merge(arr,left,right,mid,temp);
	}

	/**
	 * ���ֵ������������������ĺϲ�
	 * �����Ƚϣ�С�ķ���temp
	 * @param arr Ҫ���������
	 * @param left �������:����� -> mid
	 * @param right �ұ�����:mid+1 -> ���յ�
	 * @param mid ����������±�
	 * @param temp ���ڴ���������ݵ���ʱ����
	 */
	public static void merge(int[] arr,int left,int right,int mid,int[] temp){
		int i = left; // ���������α�
		int j = mid+1; // �ұ�������α�
		int t = 0; // temp������α�
		while (i <= mid && j <= right){
			if(arr[i] < arr[j]){ // ��С�ķ���temp
				temp[t] = arr[i];
				t++;
				i++;
			}else {
				temp[t] = arr[j];
				t++;
				j++;
			}
		}
		// ��ʣ���δ�Ƚϵļ���temp
		while (i <= mid){
			temp[t] = arr[i];
			i++;
			t++;
		}

		while (j <= right){
			temp[t] = arr[j];
			j++;
			t++;
		}

		t = 0;
		int tempLeft = left; // ����arr���α�
		// ��temp��ֵ��arr
		while (tempLeft <= right){
			arr[tempLeft] = temp[t];
			tempLeft++;
			t++;
		}
	}
}
