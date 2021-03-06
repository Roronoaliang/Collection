package com.liang.list;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @Description 1. Vector是ArrayList的线程安全版本，同时也可作为顺序结构的队列来使用
 * 
 *              2. 遍历Vector有四种方式：for-each循环、迭代器、下标索引、使用elements()
 *              方法返回Enumeration对象遍历
 * 
 *              3.不考虑线程安全的情况下使用ArrayList，需要考虑线程安全的情况下使用CopyOnWriteArrayList
 * 
 *              如果使用一个或多个读取器以及一个或多个编写器，则同步包装不提供线程安全的访问。java.util 包中的集合类都返回
 *              fail-fast 迭代器，这意味着它们假设线程在集合内容中进行迭代时，集合不会更改它的内容,
 *              这里的线程安全指多个线程同时往线程安全的集合对象写入数据时能保证同步
 * 
 * @Date 2016年3月30日 下午2:08:00
 */
public class AccessVector {

	/**
	 * 使用for-each遍历vector
	 * 
	 * @param vec
	 */
	@SuppressWarnings("unused")
	public static void accessWithForEach(List<Integer> vec) {
		long begin = System.currentTimeMillis();
		for (Integer i : vec) {

		}
		System.out.println("use for each: "
				+ (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用迭代器遍历
	 * 
	 * @param vec
	 */
	public static void accessWithIterator(List<Integer> vec) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> it = vec.iterator();
		while (it.hasNext()) {
			it.next();
		}
		System.out.println("use Iterator: "
				+ (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用下标遍历
	 * 
	 * @param vec
	 */
	public static void accessWithIndex(List<Integer> vec) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < vec.size(); i++) {
			vec.get(i);
		}
		System.out
				.println("use Index: " + (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用Enumeration对象遍历
	 * 
	 * @param vec
	 */
	public static void accessWithEnumeration(List<Integer> vec) {
		Vector<Integer> v = (Vector<Integer>) vec;
		long begin = System.currentTimeMillis();
		Enumeration<Integer> e = v.elements();
		while (e.hasMoreElements()) {
			e.nextElement();
		}
		System.out.println("use Enumeration: "
				+ (System.currentTimeMillis() - begin));
	}

	public static void main(String[] args) {
		List<Integer> vec = new Vector<Integer>();
		for (int i = 0; i < 1000000; i++) {
			vec.add(i);
		}
		accessWithForEach(vec);
		accessWithIterator(vec);
		accessWithEnumeration(vec);
		accessWithIndex(vec);
	}
}
