package tree.huffman;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼树主要用于生成无歧义的变长编码
 */
public class HuffmanCode {

	public static void main(String[] args) {
		String str = "i like like like java do you like a java";
		byte[] bytes = str.getBytes();
		List<HNode> list = getHNodes(bytes);
		// 根据哈夫曼list创建哈夫曼树
		HNode huffmanCodeTree = HuffmanCodeTree.createHuffmanCodeTree(list);
		// 根据哈夫曼树生成哈夫曼编码
		Map<Byte, String> codes = HuffmanCodeTree.getCodes(huffmanCodeTree);
		// 根据哈夫曼编码重新替换原byte数组
		byte[] zip = HuffmanCodeTree.zip(bytes, codes);
		byte[] decode = decode(codes, zip);
		System.out.println(new String(decode));
//		String path = "G:\\leyou-new.sql";
//		String dstPath = "G:\\dst.zip";
//		String originPath = "G:\\origin.sql";
//		//zipFile(path,dstPath);
//		unzip(dstPath,originPath);
	}

	// 计算每个字符出现的频次(权值)，返回所有哈夫曼树叶子结点的list
	private static List<HNode> getHNodes(byte[] bytes) {
		// 将bytes数组放在map中用于计算权值(即重复的次数)
		Map<Byte, Integer> map = new HashMap<>();
		for (int i = 0; i < bytes.length; i++) {
			if (map.containsKey(bytes[i])) {
				map.put(bytes[i], map.get(bytes[i]) + 1);
			} else {
				map.put(bytes[i], 1);
			}
		}
		// 将map转为node的list
		List<HNode> list = new ArrayList<>();
		for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
			list.add(new HNode(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	public static byte[] huffmanZip(byte[] bytes){
		// 将bytes数组放在map中用于计算权值(即重复的次数)
		List<HNode> list = getHNodes(bytes);
		// 根据哈夫曼list创建哈夫曼树
		HNode huffmanCodeTree = HuffmanCodeTree.createHuffmanCodeTree(list);
		// 根据哈夫曼树生成哈夫曼编码
		Map<Byte, String> codes = HuffmanCodeTree.getCodes(huffmanCodeTree);
		// 根据哈夫曼编码重新替换原byte数组
		byte[] zip = HuffmanCodeTree.zip(bytes, codes);
		return zip;
	}

	/**
	 * 将byte转换为对应的二进制字符串
	 * @param flag 表示是否需要补高位
	 * @param b 传入的byte
	 * @return
	 */
	public static String byteToBitSTr(boolean flag,byte b){
		int temp = b; //用变量保存
		if(flag){ // 如果是正数，比如1，就只会为1，需要按位或补高位(补刀八位) 2^8 就是256
			temp |= 256;
		}
		String s = Integer.toBinaryString(temp);
		if(flag){
			return s.substring(s.length()-8); // 由于int为32位，只需要取最后八位即可
		}else {
			return s;
		}
	}

	/**
	 * 还原压缩的字节数组
	 * @param map
	 * @param bytes
	 * @return
	 */
	public static byte[] decode(Map<Byte,String> map,byte[] bytes){
		StringBuilder sb = new StringBuilder();
		boolean flag;
		// 将字节数组转换为字符串
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			flag = (i == (bytes.length - 1));
			sb.append(byteToBitSTr(!flag,b));
		}
		// 将哈夫曼编码key和value进行逆转
		Map<String,Byte> codes = new HashMap<>();
		for (Map.Entry<Byte,String> entry : map.entrySet()){
			codes.put(entry.getValue(),entry.getKey());
		}

		// 遍历字符串
		List<Byte> list = new ArrayList<>();
		int count;
		for (int i = 0; i < sb.length(); ) {
			count = 0;
			flag = true;
			while (flag){
				String str = sb.substring(i,i+count);
				if(codes.containsKey(str)){
					list.add(codes.get(str));
					flag = false;
					i += count;
				}else {
					count ++;
				}
			}
		}
		byte[] result = new byte[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	/**
	 * 压缩文件
	 * @param srcPath 要压缩的文件路径
	 * @param dstPath 压缩文件的存储路径
	 */
	public static void zipFile(String srcPath,String dstPath){
		File file = new File(srcPath);
		File dstFile = new File(dstPath);
		if(!file.exists()){
			return;
		}
		try(
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(dstFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				) {
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);


			List<HNode> list = getHNodes(bytes);
			// 根据哈夫曼list创建哈夫曼树
			HNode huffmanCodeTree = HuffmanCodeTree.createHuffmanCodeTree(list);
			// 根据哈夫曼树生成哈夫曼编码
			Map<Byte, String> codes = HuffmanCodeTree.getCodes(huffmanCodeTree);
			byte[] zip = HuffmanCodeTree.zip(bytes, codes);
			oos.writeObject(zip);
			oos.writeObject(codes); // 将哈夫曼编码也写入，便于解压

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压文件
	 * @param srcFile 压缩文件路径
	 * @param dstFile 还原文件存放路径
	 */
	public static void unzip(String srcFile,String dstFile){
		File file = new File(srcFile);
		File dst = new File(dstFile);
		if(!file.exists()){
			return;
		}

		try (
				InputStream is = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(is);
				FileOutputStream fos = new FileOutputStream(dstFile);
				){
			byte[] bytes = (byte[]) ois.readObject();
			Map<Byte,String> codes = (Map<Byte, String>) ois.readObject();
			byte[] decode = decode(codes, bytes);
			fos.write(decode);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class HuffmanCodeTree{

	/**
	 * 列表转哈夫曼树
	 * @param nodes
	 * @return
	 */
	public static HNode createHuffmanCodeTree(List<HNode> nodes){
		while (nodes.size() > 1){
			Collections.sort(nodes);
			HNode leftNode = nodes.get(0);
			HNode rightNode = nodes.get(1);
			HNode parent = new HNode(null,leftNode.getWeight()+rightNode.getWeight());
			parent.setLeft(leftNode);
			parent.setRight(rightNode);
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	public static Map<Byte,String> getCodes(HNode node){
		if(node == null){
			return null;
		}
//		getCodes(node.getLeft(),"0",stringBuilder);
//		getCodes(node.getRight(),"1",stringBuilder);
		getCodes(node,"",stringBuilder);
		return codes;
	}

	static Map<Byte,String> codes = new HashMap<>();
	static StringBuilder stringBuilder = new StringBuilder();
	/**
	 * 根据给出的哈夫曼树的节点生成其子树的哈夫曼code
	 * @param node 父节点
	 * @param code 0/1 左子树就是 0  右子树就是 1
	 * @param sb 要拼接的code
	 * @return
	 */
	public static void getCodes(HNode node,String code,StringBuilder sb){
		StringBuilder str = new StringBuilder(sb);
		str.append(code);
		if (node.getData() == null){ // 如果为空代表是父节点
			// 向左递归
			getCodes(node.getLeft(),"0",str);
			// 向右递归
			getCodes(node.getRight(),"1",str);
		}else { // 如果是叶子结点，直接加入Map
			codes.put(node.getData(),str.toString());
		}
	}

	/**
	 * 根据哈夫曼code，将原来的byte数组中的字节替换成码表中的
	 * @param origin 原来的字节数组
	 * @param codes 哈夫曼码表
	 * @return 返回压缩后的字节数组
	 */
	public static byte[] zip(byte[] origin,Map<Byte, String> codes){

		// 将字节数组根据码表转换为字符串
		StringBuilder sb = new StringBuilder();
		for (byte b : origin) {
			sb.append(codes.get(b));
		}

		int len; //要返回的字节数组长度
		if(sb.length()%8 == 0){
			len = sb.length()/8;
		}else {
			len = sb.length()/8 + 1;
		}
		byte[] bytes = new byte[len];
		int index = 0;
		// 每八个转换为一个字节
		for (int i = 0; i < sb.length(); i+=8) {
			String s;
			if(i+8 > sb.length()){
				s = sb.substring(i);
			}else {
				s = sb.substring(i, i + 8);
			}
			bytes[index] = (byte) Integer.parseInt(s,2);
			index++;
		}
		return bytes;
	}

}

class HNode implements Comparable<HNode>{
	private Byte data; // 存放数据，即字母的ASCALL码值
	private int weight; // 权重，即该字母重复的次数
	private HNode left;
	private HNode right;

	public HNode(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	public void preOrder(){
		System.out.println(this);
		if(this.getLeft() != null){
			this.getLeft().preOrder();
		}
		if(this.getRight() != null){
			this.getRight().preOrder();
		}
	}

	public Byte getData() {
		return data;
	}

	public void setData(byte data) {
		this.data = data;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public HNode getLeft() {
		return left;
	}

	public void setLeft(HNode left) {
		this.left = left;
	}

	public HNode getRight() {
		return right;
	}

	public void setRight(HNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HNode{" +
				"data=" + data +
				", weight=" + weight +
				'}';
	}

	@Override
	public int compareTo(HNode o) {
		return this.weight - o.weight;
	}
}