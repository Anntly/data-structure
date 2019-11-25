package array.sparsearray;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 稀松数组
 * 当一个二维数组有大量的重复元素的时候，对数组进行压缩
 *
 * 压缩的数组 sparseArr[0][0] 原数组 arr.length    sparseArr[0][1] arr[1].length   sparseArr[0][2] 不重复元素的个数
 * 下面每一列 sparseArr[i][0] 第几行               sparseArr[i][1] 第几列           sparseArr[i][2] 元素值
 */
public class SparseArray {

    // 稀松数组
    private int[][] sparseArr;

    public SparseArray(int[][] arr,int num){
        // 初始化稀松数组
        /*
           1. 查找非num值的数量
           2. 初始化稀松数组数据
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
            1. 读取稀疏数组第一行，初始化二维数组
            2. 遍历稀疏数组，还原数据
         */

        int[][] result = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            result[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return result;
    }

    public String save(){
        // 返回存储路径
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
