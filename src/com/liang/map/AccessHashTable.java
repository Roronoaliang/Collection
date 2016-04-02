package com.liang.map;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Description 
 *              HashTable是HashMap的线程安全版本，与HashMap不同的是HashTable不允许key和value都不能是null
 *              ，并且增加了使用Enumeration方式遍历map
 * @Date 2016年3月30日 下午9:38:58
 */
public class AccessHashTable {

	/**
	 * 使用Enumeration遍历值集合
	 * 
	 * @param map
	 */
	public static void accessWithEnumeration(Map<Integer, Object> map) {
		Hashtable<Integer, Object> table = (Hashtable<Integer, Object>) map;
		Enumeration<Object> en = table.elements();
		while (en.hasMoreElements()) {
			System.out.print("value = " + en.nextElement() + "\t");
		}
	}

	public static void main(String[] args) {
		Map<Integer, Object> map = new Hashtable<Integer, Object>();
		for (int i = 1; i < 100; i++) {
			map.put(i, i * 2);
		}
		accessWithEnumeration(map);
		System.out.println();
		try {
			map.put(11111, null); //value不能为null
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			map.put(null, 100); //key不能为null
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
