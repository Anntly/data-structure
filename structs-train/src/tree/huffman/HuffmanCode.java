package tree.huffman;

import java.io.*;
import java.util.*;

/**
 * ����������Ҫ��������������ı䳤����
 */
public class HuffmanCode {

	public static void main(String[] args) {
		String str = "i like like like java do you like a java";
		byte[] bytes = str.getBytes();
		List<HNode> list = getHNodes(bytes);
		// ���ݹ�����list������������
		HNode huffmanCodeTree = HuffmanCodeTree.createHuffmanCodeTree(list);
		// ���ݹ����������ɹ���������
		Map<Byte, String> codes = HuffmanCodeTree.getCodes(huffmanCodeTree);
		// ���ݹ��������������滻ԭbyte����
		byte[] zip = HuffmanCodeTree.zip(bytes, codes);
		byte[] decode = decode(codes, zip);
		System.out.println(new String(decode));
//		String path = "G:\\leyou-new.sql";
//		String dstPath = "G:\\dst.zip";
//		String originPath = "G:\\origin.sql";
//		//zipFile(path,dstPath);
//		unzip(dstPath,originPath);
	}

	// ����ÿ���ַ����ֵ�Ƶ��(Ȩֵ)���������й�������Ҷ�ӽ���list
	private static List<HNode> getHNodes(byte[] bytes) {
		// ��bytes�������map�����ڼ���Ȩֵ(���ظ��Ĵ���)
		Map<Byte, Integer> map = new HashMap<>();
		for (int i = 0; i < bytes.length; i++) {
			if (map.containsKey(bytes[i])) {
				map.put(bytes[i], map.get(bytes[i]) + 1);
			} else {
				map.put(bytes[i], 1);
			}
		}
		// ��mapתΪnode��list
		List<HNode> list = new ArrayList<>();
		for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
			list.add(new HNode(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	public static byte[] huffmanZip(byte[] bytes){
		// ��bytes�������map�����ڼ���Ȩֵ(���ظ��Ĵ���)
		List<HNode> list = getHNodes(bytes);
		// ���ݹ�����list������������
		HNode huffmanCodeTree = HuffmanCodeTree.createHuffmanCodeTree(list);
		// ���ݹ����������ɹ���������
		Map<Byte, String> codes = HuffmanCodeTree.getCodes(huffmanCodeTree);
		// ���ݹ��������������滻ԭbyte����
		byte[] zip = HuffmanCodeTree.zip(bytes, codes);
		return zip;
	}

	/**
	 * ��byteת��Ϊ��Ӧ�Ķ������ַ���
	 * @param flag ��ʾ�Ƿ���Ҫ����λ
	 * @param b �����byte
	 * @return
	 */
	public static String byteToBitSTr(boolean flag,byte b){
		int temp = b; //�ñ�������
		if(flag){ // ���������������1����ֻ��Ϊ1����Ҫ��λ�򲹸�λ(������λ) 2^8 ����256
			temp |= 256;
		}
		String s = Integer.toBinaryString(temp);
		if(flag){
			return s.substring(s.length()-8); // ����intΪ32λ��ֻ��Ҫȡ����λ����
		}else {
			return s;
		}
	}

	/**
	 * ��ԭѹ�����ֽ�����
	 * @param map
	 * @param bytes
	 * @return
	 */
	public static byte[] decode(Map<Byte,String> map,byte[] bytes){
		StringBuilder sb = new StringBuilder();
		boolean flag;
		// ���ֽ�����ת��Ϊ�ַ���
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			flag = (i == (bytes.length - 1));
			sb.append(byteToBitSTr(!flag,b));
		}
		// ������������key��value������ת
		Map<String,Byte> codes = new HashMap<>();
		for (Map.Entry<Byte,String> entry : map.entrySet()){
			codes.put(entry.getValue(),entry.getKey());
		}

		// �����ַ���
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
	 * ѹ���ļ�
	 * @param srcPath Ҫѹ�����ļ�·��
	 * @param dstPath ѹ���ļ��Ĵ洢·��
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
			// ���ݹ�����list������������
			HNode huffmanCodeTree = HuffmanCodeTree.createHuffmanCodeTree(list);
			// ���ݹ����������ɹ���������
			Map<Byte, String> codes = HuffmanCodeTree.getCodes(huffmanCodeTree);
			byte[] zip = HuffmanCodeTree.zip(bytes, codes);
			oos.writeObject(zip);
			oos.writeObject(codes); // ������������Ҳд�룬���ڽ�ѹ

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ѹ�ļ�
	 * @param srcFile ѹ���ļ�·��
	 * @param dstFile ��ԭ�ļ����·��
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
	 * �б�ת��������
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
	 * ���ݸ����Ĺ��������Ľڵ������������Ĺ�����code
	 * @param node ���ڵ�
	 * @param code 0/1 ���������� 0  ���������� 1
	 * @param sb Ҫƴ�ӵ�code
	 * @return
	 */
	public static void getCodes(HNode node,String code,StringBuilder sb){
		StringBuilder str = new StringBuilder(sb);
		str.append(code);
		if (node.getData() == null){ // ���Ϊ�մ����Ǹ��ڵ�
			// ����ݹ�
			getCodes(node.getLeft(),"0",str);
			// ���ҵݹ�
			getCodes(node.getRight(),"1",str);
		}else { // �����Ҷ�ӽ�㣬ֱ�Ӽ���Map
			codes.put(node.getData(),str.toString());
		}
	}

	/**
	 * ���ݹ�����code����ԭ����byte�����е��ֽ��滻������е�
	 * @param origin ԭ�����ֽ�����
	 * @param codes ���������
	 * @return ����ѹ������ֽ�����
	 */
	public static byte[] zip(byte[] origin,Map<Byte, String> codes){

		// ���ֽ�����������ת��Ϊ�ַ���
		StringBuilder sb = new StringBuilder();
		for (byte b : origin) {
			sb.append(codes.get(b));
		}

		int len; //Ҫ���ص��ֽ����鳤��
		if(sb.length()%8 == 0){
			len = sb.length()/8;
		}else {
			len = sb.length()/8 + 1;
		}
		byte[] bytes = new byte[len];
		int index = 0;
		// ÿ�˸�ת��Ϊһ���ֽ�
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
	private Byte data; // ������ݣ�����ĸ��ASCALL��ֵ
	private int weight; // Ȩ�أ�������ĸ�ظ��Ĵ���
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