package queue;

/**
 * �����������
 * 1. �ж϶����Ƿ�Ϊ��: tail == front
 * 2. �ж϶����Ƿ�����: (tail+1)%maxsize == front
 * 3. ��ȡ��Ч����: (maxsize + tail - front)%maxsize   maxsize-front Ϊͷ��㵽����β���ĸ���  tailΪ����β�ĸ���
 */
public class CircleArrayQueue {

    private int maxSize; // �������+1������һ��Ԥ����λ

    private int front; // ͷ�ڵ�ָ�룬ָ���һ��Ԫ��

    private int tail; // β�ڵ�ָ�룬ָ�����һ��Ԫ�غ�һ��(Ԥ��һ��λ��)

    private int[] arr;

    public CircleArrayQueue(int size){
        arr = new int[size];
        maxSize = size;
    }

    // �ж��Ƿ�����
    public boolean isFull(){
        // ��maxSizeΪ4��tail�±�Ϊ3��ʱ�򣬼���βԪ���±�Ϊ2���ʹ��������������Чλ��Ϊ3���������һ��λ��ΪԤ��λ��
        return (tail+1)%maxSize == front;
    }

    // �ж��Ƿ�Ϊ��
    public boolean isEmpty(){
        // ��tail׷��front��ʱ���Ϊ��
        return tail == front;
    }

    // Ԫ�����
    public void add(int num){
        if (isFull()){
            System.out.println("��������");
            return;
        }
        /*
            1. ����ֵ
            2. �ƶ�tail
         */
        arr[tail] = num;
        tail = (++tail)%maxSize;
    }

    // Ԫ�س���
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("����Ϊ��");
        }
        /*
            1. ��ȡͷ����ֵ
            2. �ƶ�ͷ���
         */
        int result = arr[front];
        front = (++front)%maxSize;
        return result;
    }

    // �鿴ͷ���
    public int head(){
        if (isEmpty()){
            throw new RuntimeException("����Ϊ��");
        }
        return arr[front];
    }

    // ��ȡ���е���Ч����
    public int getSize(){
        // ֻ�� 0 1 2 ����λ��������
        return (maxSize - front + tail)%maxSize;
    }

    public void printQueue(){
        for (int i = front; i < front + getSize(); i++) {
            System.out.print(arr[i%maxSize]+" ");// ��ֹԽ��
        }
    }

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(6);// ʵ����Чλ��Ϊ3
        for (int i = 0; i < 5; i++) {
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
