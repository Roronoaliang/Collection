package com.liang.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @Description TreeMap是jdk提供的唯一有序Map实现类,是利用红黑树来实现的,所以其插入、删除、查找的时间复杂度是O(logn);
 *              默认情况下是根据key的自然顺序进行排序，也可以在构造函数中指定自己的比较器,同时它也是线程非安全的，支持fail-fast机制。
 *              
 *              TreeMap的键值不能为空，value值可以为空
 * @Date 2016年4月1日 上午11:02:01
 */
public class TreeMapDemo {

	public static Map<Integer, Object> map0 = new HashMap<Integer, Object>();
	public static Map<Integer, Object> map1 = new TreeMap<Integer, Object>(); // 默认TreeMap按键的自然顺序进行排序
	public static Map<Integer, Object> map2 = new TreeMap<Integer, Object>(
			new MyComparator()); // 键值根据指定比较器排序规则排序
	public static Map<Integer, Object> map3;
	public static Map<Integer, Object> map4;

	static {
		map0.put(666, "hashMap");
		map0.put(333, "hello");
		map0.put(999, "java");
		for (int i = 1, j = 0; j < 20 ; i*=2, j++) {
			map1.put(i, i * 2 + 1);
			map2.put(i, i * 2 + 1);
		}
		map3 = new TreeMap<Integer, Object>(map0);// 创建包含无序map-map0的TreeMap，内部是将无序map中的entry逐个添加到TreeMap
		map4 = new TreeMap<Integer, Object>(map1); // 创建包含有序map-map1的TreeMap，内部是通过递归关联转换成TreeMap
	}

	public static void main(String[] args) {

		// TreeMap与HashMap一样提供了操作key集合、value集合、key-value集合的方法
		System.out
				.println("\n-------------------------Map0-------------------------");
		accessWithKeySet(map0);
		System.out
				.println("\n-------------------------Map1-------------------------");
		accessWithEntrySet(map1);
		System.out
				.println("\n-------------------------Map2-------------------------");
		accessWithEntrySet(map2);
		System.out
				.println("\n-------------------------Map3-------------------------");
		accessWithEntrySet(map3);
		System.out
				.println("\n-------------------------Map4-------------------------");
		accessWithEntrySet(map4);

		TreeMap<Integer, Object> treeMap = (TreeMap<Integer, Object>) map1;
		treeMap.put(110, null);
		treeMap.put(111, null); // TreeMap的value可以为空，此处不抛出异常
		try {
			treeMap.put(null, "value"); // TreeMap的key不能为空，此处抛出异常
		} catch (Exception e) {
			System.out.println("\ntreeMap's key can not be null\n");
		}
		
		// TreeMap还提供了一些特有的操作，如获取第一个entry、获取最后一个entry、获取子树、以键的逆序遍历等
		System.out.println("the first entry is: " + treeMap.firstEntry()); // 获取第一个entry，firstEntry调用了内部的getFirstEntry方法，在此基础上屏蔽了setValue()方法，防止被用户修改
		System.out.println("the last entry is: " + treeMap.lastEntry()); // 与firstEntry方法类似
		
		System.out.println("\n--------------截取第key取值范围在8到100之间的entry--------------");
		accessWithEntrySet(treeMap.subMap(8, 100)); // 截取子树
		
		System.out.println("\n\n---------------返回key值比传入值小但最接近传入值的Entry------------");
		System.out.println(treeMap.lowerEntry(110));
		System.out.println("\n---------------返回key值比传入值大但最接近传入值的key------------");
		System.out.println(treeMap.higherKey(5)); 
		
		NavigableMap<Integer, Object> navigableMap = treeMap.descendingMap(); //返回TreeMap的逆序视图，即获得一棵反向树
		System.out.println("------------逆序遍历map1-------------------");
		accessWithEntrySet(navigableMap);
		
	}

	public static void accessWithKeySet(Map<Integer, Object> map) {
		Iterator<Integer> it = map.keySet().iterator();
		System.out.print("access with keySet: ");
		while (it.hasNext()) {
			while (it.hasNext()) {
				Integer key = it.next();
				System.out.print(" key = " + key + ", value = " + map.get(key)
						+ "\t");
			}
		}
	}

	public static void accessWithValues(Map<Integer, Object> map) {
		Iterator<Object> it = map.values().iterator();
		System.out.print("access with values: ");
		while (it.hasNext()) {
			System.out.print("value = " + it.next() + "\t");
		}
	}

	public static void accessWithEntrySet(Map<Integer, Object> map) {
		Iterator<Entry<Integer, Object>> it = map.entrySet().iterator();
		System.out.print("access with entrySet: ");
		while (it.hasNext()) {
			Entry<Integer, Object> e = it.next();
			System.out.print(" key = " + e.getKey() + ", value = "
					+ e.getValue() + "\t");
		}
	}

}
