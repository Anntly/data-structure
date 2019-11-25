package queue;

/**
 * 环形数组队列
 * 1. 判断队列是否为空: tail == front
 * 2. 判断队列是否已满: (tail+1)%maxsize == front
 * 3. 获取有效个数: (maxsize + tail - front)%maxsize   maxsize-front 为头结点到数组尾部的个数  tail为到队尾的个数
 */
public class CircleArrayQueue {

    private int maxSize; // 最大容量+1，会有一个预留空位

    private int front; // 头节点指针，指向第一个元素

    private int tail; // 尾节点指针，指向最后一个元素后一个(预留一个位置)

    private int[] arr;

    public CircleArrayQueue(int size){
        arr = new int[size];
        maxSize = size;
    }

    // 判断是否已满
    public boolean isFull(){
        // 当maxSize为4，tail下标为3的时候，即队尾元素下标为2，就代表队列已满，有效位置为3个，多出的一个位置为预留位置
        return (tail+1)%maxSize == front;
    }

    // 判断是否为空
    public boolean isEmpty(){
        // 当tail追上front的时候就为空
        return tail == front;
    }

    // 元素入队
    public void add(int num){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        /*
            1. 设置值
            2. 移动tail
         */
        arr[tail] = num;
        tail = (++tail)%maxSize;
    }

    // 元素出队
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        /*
            1. 获取头结点的值
            2. 移动头结点
         */
        int result = arr[front];
        front = (++front)%maxSize;
        return result;
    }

    // 查看头结点
    public int head(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    // 获取队列的有效个数
    public int getSize(){
        // 只有 0 1 2 三个位置有数据
        return (maxSize - front + tail)%maxSize;
    }

    public void printQueue(){
        for (int i = front; i < front + getSize(); i++) {
            System.out.print(arr[i%maxSize]+" ");// 防止越界
        }
    }

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(6);// 实际有效位置为3
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        queue.printQueue();
        System.out.println("队列头部:"+queue.head());
        queue.pop();
        queue.printQueue();
        System.out.println("队列头部:"+queue.head());
        queue.add(1);
        queue.printQueue();
        System.out.println("队列头部:"+queue.head());
    }
}
