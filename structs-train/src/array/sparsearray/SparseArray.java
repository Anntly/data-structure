package array.sparsearray;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * ϡ������
 * ��һ����ά�����д������ظ�Ԫ�ص�ʱ�򣬶��������ѹ��
 *
 * ѹ�������� sparseArr[0][0] ԭ���� arr.length    sparseArr[0][1] arr[1].length   sparseArr[0][2] ���ظ�Ԫ�صĸ���
 * ����ÿһ�� sparseArr[i][0] �ڼ���               sparseArr[i][1] �ڼ���           sparseArr[i][2] Ԫ��ֵ
 */
public class SparseArray {

    // ϡ������
    private int[][] sparseArr;

    public SparseArray(int[][] arr,int num){
        // ��ʼ��ϡ������
        /*
           1. ���ҷ�numֵ������
           2. ��ʼ��ϡ����������
         */
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length;j++){
                if (arr[i][j] != num){
                    sum++;
                }
            }
        }

        sparseArr = new int[sum+1][3];
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[1].length;
        sparseArr[0][2] = sum;

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length;j++){
                if (arr[i][j] != num){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }
    }

    public void printArray(){
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n",ints[0],ints[1],ints[2]);
        }
    }

    public int[][] unSparse(){
        if(sparseArr.length < 1){
            return null;
        }
        /*
            1. ��ȡϡ�������һ�У���ʼ����ά����
            2. ����ϡ�����飬��ԭ����
         */

        int[][] result = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            result[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return result;
    }

    public String save(){
        // ���ش洢·��
        return "";
    }

    public int[][] load(){
        return null;
    }


    public static void main(String[] args) {
        int [][] arr = new int[5][6];
        arr[0][3] = 1;
        arr[2][4] = 2;
        arr[3][1] = 1;

        SparseArray sparseArray = new SparseArray(arr,0);
        sparseArray.printArray();
        System.out.println("==================");
        int[][] ints = sparseArray.unSparse();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.printf("%d\t",ints[i][j]);
            }
            System.out.println();
        }
    }
}
