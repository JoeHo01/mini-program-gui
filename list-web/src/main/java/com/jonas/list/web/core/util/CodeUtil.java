package com.jonas.list.web.core.util;

import org.springframework.util.Base64Utils;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/28
 */
public class CodeUtil {

	public static String base64Decode(String secret) throws IllegalArgumentException {
		return new String(Base64Utils.decodeFromString(secret));
	}
}
