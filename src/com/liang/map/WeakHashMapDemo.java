package com.liang.map;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Description 
 *              WeakHashMap与HashMap最大的不同是WeakHashMap的键是弱键（WeakReference）,当某个键不再被使用时
 *              ，会从WeakHashMap中自动移除。
 * 
 * @Theory WeakHashMap是通过 WeakReference 和 ReferenceQueue (队列)实现的：<br/>
 *         1). 新建WeakHashMap,将key-value添加进WeakHashMap中，即保存进WeakHashMap的table数组中<br/>
 *         2). 当某个键不再被其他对象引用后，在GC进行回收的时候会将该弱键回收，并同时将该弱键加入到ReferenceQueue中,
 *         即queue属性中<br/>
 *         3). 当我们再次操作WeakHashMap时，会先同步table和queue，table保存了全部的键值对，而queue中保存的是
 *         被GC回收的键值对，即删除table中被GC回收的键值对。
 * @Date 2016年4月2日 上午10:44:32
 */
public class WeakHashMapDemo {

	public static void main(String[] args) {
		
		//必须使用new关键字创建出的对象作为键值，不能使用常量
		Integer i0 = new Integer(0);
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(2);
	
		Map<Integer, Object> map = new WeakHashMap<Integer, Object>();
		map.put(i0, "hello");
		map.put(i1, "world");
		map.put(i2, "java");
		AccessHashMap.accessWithEntrySet(map);
		i2 = null; //i2引用置为空
		System.gc(); //进行垃圾回收，会将之前i2对应的WeakHashMap中的键值对回收
		AccessHashMap.accessWithEntrySet(map);
		
	}
}
