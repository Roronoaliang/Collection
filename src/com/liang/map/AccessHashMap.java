package com.liang.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Description 
 *              HashMap是一个数组链表的结构（说明了当发生哈希冲突时采用的是“拉链法”解决冲突）,即散列表的结构，其每个链表的结点都是一个键值对
 *              ，每一个键值对存放的位置由key的哈希值（与数组的长度-1）参与哈希函数运算决定。
 * 
 *              完整遍历Map有两种方式：1. 使用迭代器遍历keySet，然后根据key去获取value，实际上发生了两次查找遍历，不建议使用
 *              2. 使用迭代器遍历entrySet，建议使用这种方式。
 * 
 *              若只需要获取value集合，可以使用values()方法遍历
 * @Date 2016年3月30日 下午6:15:18
 */
public class AccessHashMap {

	/**
	 * 使用KeySet的迭代器遍历
	 * 
	 * @param map
	 */
	public static void accessWithKeySet(Map<Integer, Object> map) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> it = map.keySet().iterator();
		while (it.hasNext()) {
			Integer key = it.next();
			System.out.print(" key = " + key + ", value = " + map.get(key)
					+ "\t");
		}
		System.out.println("\n accessWithKeySet: "
				+ (System.currentTimeMillis() - begin));
	}

	/**
	 * 使用EntrySet的迭代器遍历
	 * 
	 * @param map
	 */
	public static void accessWithEntrySet(Map<Integer, Object> map) {
		long begin = System.currentTimeMillis();
		Iterator<Entry<Integer, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Object> e = it.next();
			System.out.print(" key = " + e.getKey() + ", value = "
					+ e.getValue() + "\t");
		}
		System.out.println("\n accessWithEntrySet: "
				+ (System.currentTimeMillis() - begin));
	}

	/**
	 * 只遍历value集合
	 * @param map
	 */
	public static void accessWithValues(Map<Integer, Object> map) {
		long begin = System.currentTimeMillis();
		Iterator<Object> it = map.values().iterator();
		while (it.hasNext()) {
			System.out.print("value = " + it.next() + "\t");
		}
		System.out.println("\n accessWithValues: "
				+ (System.currentTimeMillis() - begin));
	}

	public static void main(String[] args) {
		/**
		 * HashMap有两个参数影响其性能：初始容量与负载因子，当Map中的Entry数量大于初始容量与负载因子之积的时候会进行“再哈希”的操作，
		 * 扩大数组容量，并重新散列所有Entry。
		 */
		Map<Integer, Object> map = new HashMap<Integer, Object>(2000, 0.6f);// 负载因子默认是0.75
		for (int i = 1; i < 1000; i++) {
			map.put(i, i * 2);
		}
		map.put(null, null);

		accessWithEntrySet(map);
		accessWithKeySet(map);
		accessWithValues(map);
	}

}
