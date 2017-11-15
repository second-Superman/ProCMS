package com.hzj.util.validate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ValidateUtil {

	/**
	 * 如果拥有一个空字符串或者null则返回true
	 * 
	 * @return
	 */
	public static boolean validateNullAndEmpty(HashMap<String, String> prams) {
		boolean temp = false;
		
		Set<String> sets = prams.keySet();
		Iterator<String> iterator = sets.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = prams.get(key);
			if (value == null || value.trim().equals("")) {
				temp = true;
				break;
			}
		}

		return temp;

	}

}
