package com.hzj.util.md5;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	public static String MD5Encode(String src) {
		
		return DigestUtils.md5Hex(src);

	}

}
