package order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Test {


	public static void main(String[] args) {
		ListNode listNode1 = new ListNode(5);
		ListNode listNode2 = new ListNode(4);
		ListNode listNode3 = new ListNode(3);
		ListNode listNode4 = new ListNode(2);
		ListNode listNode5 = new ListNode(1);
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;
		listNode4.next = listNode5;


		ListNode listNode = Test.ReverseList(listNode1);
		while (listNode.next != null){
			System.out.println(listNode.val);
			listNode = listNode.next;
		}
	}

	public static ListNode ReverseList(ListNode head) {
		// 创建一个新的Node使用头插法
		ListNode temp = new ListNode(-10);
		ListNode cur = head;
		ListNode next = head.next;
		while(cur != null){
			cur.next = temp.next;
			temp.next = cur;

			cur = next;
			if(next != null){
				next = next.next;
			}
		}
		ListNode res = temp.next;
		return res;
	}
}
class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}
}