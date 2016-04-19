package com.liang.map;

import java.util.LinkedHashMap;

/**
 * @Description LinkedHashMap是HashMap的子类，它默认迭代时使用用户插入的顺序输出,
 * 
 *              LinkedHashMap在执行get和put方法的时候会调用recordAccess方法,
 *              如果设置了LinkedHashMap访问的时候按照访问的顺序迭代,也就是将accessOrder属性设置为true,
 *              那么在recordAccess方法中将会从原来的链表中删除掉已存在的entry，然后将新entry使用头插法插入链表,
 *              这样就能保证链表尾部的entry是最久未使用的。<br/>
 *              而如果使用LinkedHashMap实现LRU算法,首先就要依靠这个特点,因为每次删除元素的时候刚好是删除链表的最后一个结点,
 *              LinkedHashMap维持的是双向链表
 *              ,可以直接通过头结点访问最后一个元素因此很容易就能删除最后一个结点。那么什么时候删除呢？
 *              也就是如果我们想要用LinkedHashMap实现LRU算法该怎么做呢
 *              ？还是回到源码,在调用put方法添加元素,执行到addEntry()方法时,
 *              会判断removeEldestEntry()方法是否访问true
 *              ,即是否超过容量删除“最老”元素,默认该方法是返回false，不删除，即让LinkedHashMap的元素永远不过期，
 *              实现LRU算法就是要重写removeEldestEntry方法。
 * 
 * @Date 2016年4月19日 下午12:25:39
 */
public class LinkedHashMapDemo {

	public static void main(String[] args) {
		// 指定初始容量是16，负载因子是0.75，最后一个参数false表示迭代时按插入顺序访问
		LinkedHashMap<Integer, Object> map = new LinkedHashMap<Integer, Object>(
				16, 0.75f, false);
		map.put(5, "5");
		map.put(3, "3");
		map.put(4, "4");
		map.put(2, "2");
		map.put(1, "1");
		AccessHashMap.accessWithEntrySet(map);

		// accessOrder属性指定为true，按照访问顺序排序，先被访问的先输出,即从链表尾部开始输出
		map = new LinkedHashMap<Integer, Object>(16, 0.75f, true);
		map.put(5, "5");
		map.put(3, "3");
		map.put(4, "4");
		map.put(2, "2");
		map.put(1, "1");
		map.put(3, "33"); // 修改与获取都是访问了该元素
		map.get(5);
		map.get(2);
		map.get(4);
		map.get(1);
		AccessHashMap.accessWithEntrySet(map);
	}
}
