#### 稀疏数组
##### 基本介绍
当一个数组中==大部分元素为0==，或者为==同一个值==的数组时，可以使用稀疏数组来保存该数组。

#####  稀疏数组的处理方式是
1. 记录数组一共有几行几列，有多少个不同的值
2. 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模

```java
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

```