package com.liang.list;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description 
 *              fail-fast机制是一种集合类的错误检测机制,直白的说就是当出现遍历和修改操作同时进行时能快速的报错，比如当多个线程对同一个集合进行操作时
 *              ，如果有线程对该集合进行添加或删除元素的操作 ,
 *              那么其他线程在访问该集合时就会抛出ConcurrentModificationException异常,产生fail-
 *              fast事件; 或者当使用Iterator遍历集合时对该集合进行添加或删除也会发生fail-fast事件。
 *              所以如果在多线程环境下使用fail-fast机制的集合，建议使用java.util.
 *              concurrent包下的线程安全类去取代java .util包下的类。
 * 
 *              fail-fast与是否线程安全无直接关系
 * 
 * @Theory AbstractList中定义了一个modCount和expectedModCount属性，都表示该集合对象元素个数修改的次数，
 *         只要涉及到修改集合中的元素个数时,都会改变他们的值,在使用迭代器遍历的时候需要判断他们是否相等，
 *         如果不相等就会抛出ConcurrentModificationException
 * 
 * @Solution 
 *           CopyOnWriteArrayList不会抛ConcurrentModificationException，是因为所有改变其内容的操作
 *           （add、remove、clear等），都会copy一份现有数据，在现有数据上修改好，再把原有数据的引用改成指向修改后的数据。
 *           而不是在读的时候copy。
 * @Date 2016年3月29日 下午11:03:14
 */
public class Fail_Fast {

	public static List<Integer> list = new ArrayList<Integer>();

	public static List<Integer> safeList = new CopyOnWriteArrayList<Integer>();

	public static Vector<Integer> vector = new Vector<Integer>();

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			list.add(i);
			safeList.add(i);
		}
		// 开启另一个线程修改list内容
		Thread t = new Thread(new ModifyList());
		t.start();

		// 如果使用数组下标访问list不会产生fail-fast事件
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i));
		// }

		// 使用迭代器遍历CopyOnWriteArrayList，不会产生fail-fast事件,但遍历的是未修改过的原始数组
		for (Integer in : safeList) {
			System.out.println(in);
		}

		// 使用Enumeration的方式遍历Vector不会发生Fail-fast事件，使用迭代器则仍会发生
		Enumeration<Integer> en = vector.elements();
		while (en.hasMoreElements()) {
			en.nextElement();
		}

		// 使用迭代器遍历ArrayList,此时会抛出ConcurrentModificationException异常
		try {
			for (Integer in : list) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/**
 * @Description 修改list中的内容
 */
class ModifyList implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			Fail_Fast.list.add(i);
			Fail_Fast.safeList.add(i);
			Fail_Fast.vector.add(i);
		}
		System.out.println("end modify");
	}

}
