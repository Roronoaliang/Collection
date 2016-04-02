package com.liang.map;

import java.util.Comparator;

/**
 * @Description 自定义比较器
 * @Date 2016年4月2日 上午9:19:50
 */
public class MyComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		if (o1 > o2) {
			return -1;
		} else if (o1 < 01) {
			return 1;
		}
		return 0;
	}

}
