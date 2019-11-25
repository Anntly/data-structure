package queue;

/**
 * 数组队列
 */
public class ArrayQueue {

    private int maxSize; // 最大容量
    private int tail = -1; // 尾部指针，指向尾部节点
    private int front = -1; // 头部指针,指向头部节点前一个节点
    private int arr[];

    public ArrayQueue(int size){
        arr = new int[size];
        maxSize = size;
    }

    // 判断队列是否已满
    public boolean isFull(){
        return tail == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return tail == front;
    }

    // 元素入队
    public void add(int num){
        if (isFull()){ // 如果满了，判断顶部节点是否在-1(即队列是否真正full)，如果不是就将向前移动队列元素
            if(front==-1){
                System.out.println("队列已满");
                return;
            }
            // 移动队列
            int move = front + 1; // 头节点下标
            int moveNum = tail - front; // 需要移动的元素个数+1
            int moveTmp = front + 1; // tail需要移动的个数,front - (-1)
            for (int i = 0; i < moveNum && move <= tail; i++) {
                arr[i] = arr[move];
                move++;
            }
            front = -1;
            tail -= moveTmp;
        }
        tail++;
        arr[tail] = num;
    }

    // 元素出队
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    // 查看队列头部元素
    public int head(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }

    // 打印队列
    public void printQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        // 获取有效个数
        int count = tail - front;
        for (int i = front+1; i <= tail; i++) {
            System.out.printf("%d\t",arr[i]);
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        for (int i = 0; i < 3; i++) {
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
