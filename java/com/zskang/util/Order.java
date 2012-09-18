package com.zskang.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Order {

	public static void main(String[] args) {
		String[] input = { "you", "are", "my", "super", "star" };
		List<String> list = (List<String>) Arrays.asList(input);
		int i;

		System.out.println(list);

		Collections.sort(list);
		int j;

		System.out.println(list);

	}

}
