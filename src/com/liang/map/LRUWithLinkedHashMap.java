package com.liang.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 利用LinkedHashMap实现LRU算法
 * 
 *              当参数accessOrder为true时，即会按照访问顺序排序，最近访问的放在最前，最早访问的放在后面<br/>
 *              public LinkedHashMap(int initialCapacity, float loadFactor,
 *              boolean accessOrder){ <br/>
 *              super(initialCapacity, loadFactor); <br/>
 *              this.accessOrder = accessOrder;<br/>
 *              }
 * 
 *              LinkedHashMap自带的判断是否删除最老的元素方法，默认返回false，即不删除老数据
 *              我们要做的就是重写这个方法，当满足一定条件时删除老数据 <br/>
 *              protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
 *              return false; <br/>
 *              }
 * @Date 2016年4月19日 下午1:01:05
 */
public class LRUWithLinkedHashMap extends LinkedHashMap<Integer, Object> implements Map<Integer, Object>{// 如果是多线程下使用还需要覆盖其他相关的方法加上同步措施

	private static final long serialVersionUID = -8924449929940397365L;

	private static final int MAX_COUNT = 10; // 最大缓存容量

	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Object> eldest) {
		return size() > MAX_COUNT; // 如果当前size大于最大缓存容量时删除最老的元素
	}

	// 覆盖构造方法保证按照访问顺序维护链表中结点的顺序
	public LRUWithLinkedHashMap() {
		super(16, 0.75f, true);
	}

	public LRUWithLinkedHashMap(int capacity, float loadFactor,
			boolean accessOrder) {
		super(capacity, loadFactor, true);
	}
	
	public static void main(String[] args) {
		LRUWithLinkedHashMap map = new LRUWithLinkedHashMap();
		for(int i = 0; i < 9; i++) {
			map.put(i, "value_" + i);
		}
		AccessHashMap.accessWithEntrySet(map);
		map.put(1, "newValue1"); //key为1的结点被放置在链表第一个结点位置
		map.put(0, "newValue0");//key为0的结点被放置在链表第一个结点位置
		map.put(9, "value_9"); //头插法插入key=9的结点
		map.put(10, "value_10"); //头插法插入key=10的结点，此时结点数11超过最大缓存数,所以执行删除最后一个结点即key=2的结点
		map.put(11, "value_11");//插入key=11的结点，删除key=3的结点
		AccessHashMap.accessWithEntrySet(map);
	}
}
