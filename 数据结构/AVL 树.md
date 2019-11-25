### AVL树
#### 平衡二叉树
* 在AVL树当中，平衡二叉树的定义为:对于任意一个节点,左子树和右子树的高度差不能超过1
* 平衡二叉树的高度和节点数量之间的关系是O(logn)
* 标注节点的高度
* 计算平衡因子:左子树与右子树的高度差

#### AVL树
* 二分搜索树有退化为普通链表的可能性，AVL是对其进行优化满足以上平衡二叉树的条件

* 代码实现
```java
import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> {


    private class Node{

        public K key;
        public V value;
        public Node left,right;
        public int height;    //树高度

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;    //高度从叶子结点向上由1开始
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    /**
     * 获得节点高度值
     * @param node
     * @return
     */
    private int getHeight(Node node){

        if(node == null){
           return 0;
        }
        return node.height;
    }

    /**
     * 获得节点node的平衡因子
     * 平衡因子是左右子树的高度差,使用左子树减去右子树
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){

        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public void add(K key, V value) {
        root = add(root,key,value);
    }

    /**
     * 判断该二叉树是否是一棵二分搜索树
     * 中序遍历，如果结果不是由低到高的序列，代表不是二分搜索树
     * @return
     */
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for(int i = 1;i < keys.size();i++){
            if(keys.get(i-1).compareTo(keys.get(i))>0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该二叉树是否为一个平衡二叉树
     * 当平衡因子的绝对值大于1的时候代表已经不是平衡二叉树了
     * @return
     */
    public boolean isBalanced(){

        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {

        if(node == null){
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor)>1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, ArrayList<K> keys) {

        if(node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     *              y                       x
     *             / \                     / \
     *            x   T4  向右旋转(y)      z   y
     *           / \      ---------->    / \ / \
     *          z   T3                 T1 T2 T3 T4
     *         / \
     *        T1 T2
     * @param y
     * @return
     */
    private Node rightRoatate(Node y){

        Node x = y.left;
        Node T3 = x.right;

        //向右旋转过程
        x.right = y;
        y.left = T3;

        //更新节点height值
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;

        return x;
    }

    /**
     * 左旋转,对节点y进行左旋转操作，返回旋转后的新的根节点x
     *      y                       x
     *     / \       向左旋转(y)    / \
     *    T1  x   ----------->    y  z
     *       / \                 / \ / \
     *      T2  z              T1 T2 T3 T4
     *         / \
     *        T3 T4
     */
    private Node leftRotate(Node y){

        Node x = y.right;
        Node T2 = x.left;

        //向左旋转过程
        x.left = y;
        y.right = T2;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;

        return x;
    }

    /**
     * 向以node为根的二分搜索树中插新入元素
     * 返回以node为根的树
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {

        if(node == null){
            size ++;
            return new Node(key,value);   //新节点高度为1
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left, key, value) ;
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right, key, value);
        }else{
            node.value = value;   //当key相同的时候，替换value
        }

        // 对当前的node的height值进行更新,取子树中最大的高度加一
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            System.out.println("平衡因子:"+balanceFactor);
        }

        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            //右旋转
            return rightRoatate(node);
        }

        //右子树比左子树高
        //RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return  leftRotate(node);
        }

        /**
         * LR先转换为LL
         *              y                           y
         *             / \                         / \
         *            x   T4  先转换为LL           z   T4      再进行右旋转
         *           / \      ---------->        / \        ------------>
         *          T1  z                       x   T3
         *             / \                     / \
         *            T2  T3                  T1  T2
         */
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){

            node.left = leftRotate(node.left);  //将树转换为LL
            return rightRoatate(node);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){

            node.right = rightRoatate(node.right);  //转换为RR
            return rightRoatate(node);
        }

        return node;
    }

    /**
     * 返回以node为根节点的二分搜索树中，key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){

        if (node == null) {
            return null;
        }

        if(key.compareTo(node.key) == 0){
            return  node;
        }else if(key.compareTo(node.key)<0){
            return getNode(node.left, key);
        }else {
            return getNode(node.right, key);
        }
    }



    public V remove(K key) {
        Node node = getNode(root, key);
        if(node!=null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除以node为根的二分搜索树中key相同的节点
     * 返回删除后新的二分搜索树的根
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {

        if(node == null){
            return null;
        }

        Node retNode; //先不返回node节点，使用retNode节点暂存，再对树进行处理
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right, key);
            retNode = node;
        }else{
            //待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }//待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }else {

                //待删除节点左右子树均不为空
                //找到比待删除节点大的最小节点,即待删节点右子树的最小节点(左子树叶子结点)
                //用这个节点代替待删除节点的位置
                Node successor = minimum(node.right);   //找到替代的节点(待删除节点的后继)，即右子树的最小节点
                successor.right = remove(node.right, successor.key);  //将删除后的右子树添加到替代节点的右子树(使用递归进行删除)
                //size++;  在removeMin中执行了一次size--;
                successor.left = node.left;
                node.left = node.right = null;
                //size --;
                retNode = successor;
            }
        }

        if(retNode == null){   //避免之后操作出现空指针异常
            return null;
        }

        // 对当前的node的height值进行更新,去子树中最大的高度加一
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if(Math.abs(balanceFactor) > 1){
            System.out.println("平衡因子:"+balanceFactor);
        }

        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            //右旋转
            return rightRoatate(retNode);
        }

        //右子树比左子树高
        //RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return  leftRotate(retNode);
        }

        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){

            node.left = leftRotate(retNode.left);  //将树转换为LL
            return rightRoatate(retNode);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){

            node.right = rightRoatate(retNode.right);  //转换为RR
            return rightRoatate(retNode);
        }

        return retNode;
    }


    /**
     * 返回以Node为根的二分搜索树的最小值所在的节点
     * @param node
     */
    private Node minimum(Node node) {
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * 在AVL书中已经在remove方法中实现本操作，所以不需要此方法了
     * @param
     * @return
     */
//    private Node removeMin(Node node) {
//
//        if(node.left == null){
//            Node rightNode = node.right;
//            node.right = null;
//            size --;
//            return rightNode;
//        }
//        node.left = removeMin(node.left);
//        return node;
//    }

    public boolean contains(K key) {

        return getNode(root, key)!=null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null?null:node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException("节点不存在");
        }
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}
```

* 使用AVL树实现AVLMap
```java
public class AVLMap<K extends Comparable<K>,V> implements Map<K,V>{

    private AVLTree<K,V> map;

    public AVLMap(){
        map = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        map.add(key, value);
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return map.contains(key);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        map.set(key, newValue);
    }

    @Override
    public int getSize() {
        return map.getSize();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
```

* 使用AVL树实现AVLSet
```java
public class AVLSet<E extends Comparable<E>> implements Set<E>{

    private AVLTree<E,Object> avl;

    public AVLSet(){
        avl = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, null);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
```