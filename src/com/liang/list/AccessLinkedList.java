package com.liang.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Description LinkedList内部封装的Entry是一个双向链表结点的数据结构，即LinkedList实现了双向链表，
 *              因此我们可以把LinkedList当成栈、普通队列、双向队列来使用。<br/>
 *              1. LinkedList提供了根据索引下标来访问的方式，那是通过一个计数索引值来实现根据下标获取元素的
 *              ，即使用get(index)时，是通过比较"index"与"链表元素个数/2"的大小来选择从第一个元素遍历到
 *              中间位置还是从最后一个元素遍历到中间位置 <br/>
 * 
 *              2. 当成栈使用时可使用的等效方法：push/addFirst、pop/removeFirst、peek/peekFirst
 *              当成普通队列时可使用的等效方法
 *              ：add/addLast/offer/offerLast、remove/removeFirst/poll/pollFirst
 * 
 *              3.遍历LinkedList的方式较多，可以使用for-each循环（其实也就是迭代器），可以使用迭代器，可以通过下标索引，
 *              可以根据出栈操作，也可以根据出队列操作;推荐使用for-each和迭代器遍历
 *              但我们知道根据下标访问其实每次都会根据下标值重新从头或从尾再遍历一遍，所以这种访问方式效率是很低的。
 * @Date 2016年3月30日 上午10:11:34
 */
public class AccessLinkedList {

	/**
	 * 使用for-each循环
	 * 
	 * @param list
	 */
	@SuppressWarnings("unused")
	public static void accessWithForEach(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for (Integer i : list) {
		}
		System.out.println("use for-each: "
				+ (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用迭代器遍历
	 * 
	 * @param list
	 */
	public static void accessWithIterator(List<Integer> list) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			it.next();
		}
		System.out.println("use Iterator: "
				+ (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用下标索引遍历
	 * 
	 * @param list
	 */
	public static void accessWithIndex(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
		}
		System.out
				.println("use index: " + (System.currentTimeMillis() - begin));
	}

	/**
	 * 以下三种方式遍历LinkedList都会从原始LinkedList对象中删除掉遍历到的数据，因此单纯的只读操作绝不能使用这几种方式。
	 */

	/**
	 * 使用removeFirst或removeLast方式,此时不能使用List接口来调用,并且是通过抛出异常来判断是否到达末尾
	 * 
	 * @param list
	 */
	public static void accessWithRemove(List<Integer> list) {
		LinkedList<Integer> linkedList = (LinkedList<Integer>) list;
		long begin = System.currentTimeMillis();
		try {
			while (linkedList.removeFirst() != null)
				;
			// while(linkedList.removeLast() != null);
		} catch (NoSuchElementException e) {
		}

		System.out.println("use removeFirst: "
				+ (System.currentTimeMillis() - begin));
		// System.out.println("use removeLast: "
		// + (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用pollFirst或pollLast方式
	 * 
	 * @param list
	 */
	public static void accessWithPoll(List<Integer> list) {
		LinkedList<Integer> linkedList = (LinkedList<Integer>) list;
		long begin = System.currentTimeMillis();
		while (linkedList.pollFirst() != null)
			;
		System.out.println("use pollFirst: "
				+ (System.currentTimeMillis() - begin));
		// while(linkedList.pollLast() != null);
		// System.out
		// .println("use pollLast: " + (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用pop方式
	 * 
	 * @param list
	 */
	public static void accessWithPop(List<Integer> list) {
		long begin = System.currentTimeMillis();
		LinkedList<Integer> linkedList = (LinkedList<Integer>) list;
		try {
			while (linkedList.pop() != null)
				;
		} catch (NoSuchElementException e) {
		}
		System.out.println("use pop: " + (System.currentTimeMillis() - begin));
	}

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 100000; i++) {
			list.add(i);
		}
		accessWithForEach(list);
		accessWithIterator(list);
		accessWithIndex(list);
		accessWithRemove(list);
//		accessWithPoll(list);
//		accessWithPop(list);
		try {
			list.get(1); 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
