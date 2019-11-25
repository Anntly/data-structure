package queue;

/**
 * �������
 */
public class ArrayQueue {

    private int maxSize; // �������
    private int tail = -1; // β��ָ�룬ָ��β���ڵ�
    private int front = -1; // ͷ��ָ��,ָ��ͷ���ڵ�ǰһ���ڵ�
    private int arr[];

    public ArrayQueue(int size){
        arr = new int[size];
        maxSize = size;
    }

    // �ж϶����Ƿ�����
    public boolean isFull(){
        return tail == maxSize - 1;
    }

    // �ж϶����Ƿ�Ϊ��
    public boolean isEmpty(){
        return tail == front;
    }

    // Ԫ�����
    public void add(int num){
        if (isFull()){ // ������ˣ��ж϶����ڵ��Ƿ���-1(�������Ƿ�����full)��������Ǿͽ���ǰ�ƶ�����Ԫ��
            if(front==-1){
                System.out.println("��������");
                return;
            }
            // �ƶ�����
            int move = front + 1; // ͷ�ڵ��±�
            int moveNum = tail - front; // ��Ҫ�ƶ���Ԫ�ظ���+1
            int moveTmp = front + 1; // tail��Ҫ�ƶ��ĸ���,front - (-1)
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

    // Ԫ�س���
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("����Ϊ��");
        }
        front++;
        return arr[front];
    }

    // �鿴����ͷ��Ԫ��
    public int head(){
        if(isEmpty()){
            throw new RuntimeException("����Ϊ��");
        }
        return arr[front+1];
    }

    // ��ӡ����
    public void printQueue(){
        if(isEmpty()){
            throw new RuntimeException("����Ϊ��");
        }
        // ��ȡ��Ч����
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
        System.out.println("����ͷ��:"+queue.head());
        queue.pop();
        queue.printQueue();
        System.out.println("����ͷ��:"+queue.head());
        queue.add(1);
        queue.printQueue();
        System.out.println("����ͷ��:"+queue.head());

    }
}
