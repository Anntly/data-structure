### Trie
* 每个节点有若干个指向下个节点的指针
* 除根节点外每一个节点都只包含一个字符
* 从根节点到叶子节点，路径上经过的字符连接起来，构成一个词
* 节点定义

```java
class Node{
    //char c;  不需要存储本节点数据，只需要判断节点是否为空
    boolean isWord; //当前节点是否为单词结尾
    Map<char,Node> next;
}
```

* 代码实现

```java
import java.util.TreeMap;

public class Trie {   //不设置泛型

    private class Node{

        public boolean isWord;   //判断单词是否结束
        public TreeMap<Character,Node> next;   //map中character是本节点的字母

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    /**
     * 获取Trie中存储的单次数量
     */
    public int getSize(){
        return size;
    }

    /**
     * 向trie中添加一个新的单词word
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for(int i =0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
               cur.next.put(c, new Node()) ;
            }
            cur = cur.next.get(c);

        }
        if(!cur.isWord) {   //防止添加重复单词
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词word是否在Trie中
     */
    public boolean contains(String word){

        Node cur = root;
        for(int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询是否在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){

        Node cur = root;
        for(int i = 0;i< prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
```

* 练习

实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

* 示例

```
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
```

* 说明
    * 你可以假设所有的输入都是由小写字母 a-z 构成的。
    * 保证所有输入均为非空字符串。

* 练习2

设计一个支持以下两种操作的数据结构：

```
void addWord(word)
bool search(word)
```

search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。

* 示例

```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```

* 说明

```
你可以假设所有单词都是由小写字母 a-z 组成的。
```
* 代码

```java
import java.util.TreeMap;

public class WordDictionary {

    private class Node{

        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        Node cur = root;
        for(int i =0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node()) ;
            }
            cur = cur.next.get(c);

        }
        if(!cur.isWord) {   //防止添加重复单词
            cur.isWord = true;
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {

        return match(root, word, 0);
    }

    private boolean match(Node node,String word,int index){

        if(index == word.length()){
            return node.isWord;
        }

        char c = word.charAt(index);
        if(c != '.'){
            if(node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c), word, index+1);
        }else{
            for(char nextChar:node.next.keySet()){   //取出所有键的集合
                if(match(node.next.get(nextChar), word, index+1)){
                    return true;
                }
            }
            return false;
        }
    }
}
```

* 练习3

实现一个 MapSum 类里的两个方法，insert 和 sum。

对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

* 示例

```
输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5
```
* 代码

```java
import java.util.TreeMap;

public class MapSum {

    private class Node{

        private int value;
        public TreeMap<Character,Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {

        Node cur = root;
        for(int i =0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node()) ;
            }
            cur = cur.next.get(c);

        }
        cur.value = val;
    }

    public int sum(String prefix) {

        Node cur = root;
        for(int i =0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node){

//        if(node.next.size() == 0){
//            return node.value;
//        }

        int res = node.value;

        for(char c:node.next.keySet()){
            res += sum(node.next.get(c));
        }

        return res;
    }
}
```
