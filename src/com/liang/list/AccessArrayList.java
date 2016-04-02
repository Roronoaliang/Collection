package com.liang.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description 遍历ArrayList有三种方式：迭代器访问、数组下标访问、for-each循环（底层也是迭代器）
 * @Date 2016年3月29日 下午3:11:48
 */
public class AccessArrayList {
	/**
	 * 使用迭代器遍历
	 * 
	 * @param list
	 */
	public static void access1(List<Integer> list) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> i = list.iterator();
		while (i.hasNext()) {
			i.next();

		}
		System.out.println("迭代器" + (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用list迭代器,专门用于遍历list，相比Iterator它新增了额添加、是否存在上一个元素，获取上一个元素等方法
	 * 
	 * @param list
	 */
	public static void access4(List<Integer> list) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> i = list.listIterator();
		while (i.hasNext()) {
			i.next();
		}
		System.out.println("list迭代器" + (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用数组下标遍历
	 * 
	 * @param list
	 */
	public static void access2(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			// 这里改变list内容不会产生fail-fast事件
		}
		System.out.println("数组下标" + (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用for-each循环遍历
	 * 
	 * @param list
	 */
	@SuppressWarnings("unused")
	public static void access3(List<Integer> list) {
		long begin = System.currentTimeMillis();

		for (Integer i : list) {
		}
		System.out.println("for-each" + (System.currentTimeMillis() - begin));

	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(10000000);
		for (int i = 0; i < 10000000; i++) {
			list.add(i);
		}
		access1(list); // 迭代器
		access2(list); // 数组下标遍历
		access3(list); // for-each遍历
		access4(list); // list迭代器
	}
}
