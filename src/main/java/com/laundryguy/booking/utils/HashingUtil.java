package com.laundryguy.booking.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

	// This method checks if the hash stored in DB is of type MD5 or
	// MD5+Becrypt, then accordingly verifies the hash
	public static boolean doesHashMatch(String plainText, String hashToMatchWith) {

		if (StringUtils.isEmpty(plainText)
				|| StringUtils.isEmpty(hashToMatchWith)) {
			return false;
		}
		// if it matches with MD5+Becrypt hash
		if (hashToMatchWith.startsWith("$2")) {
			return verifyMD5AndBecryptHash(plainText, hashToMatchWith);
		}
		// if it matches with MD5 hash
		else {
			return hashToMatchWith
					.equalsIgnoreCase(getMD5Hash(new String[] { plainText }));
		}

	}

	public static String encodeMD5AndBecrypt(String plainText) {
		String md5Hash = getMD5Hash(new String[] { plainText });
		String becryptHash = BCrypt.hashpw(md5Hash, BCrypt.gensalt(10));
		return becryptHash;
	}

	public static boolean verifyMD5AndBecryptHash(String plainText,
			String hashToMatchWith) {

		String md5Hash = getMD5Hash(new String[] { plainText });
		return BCrypt.checkpw(md5Hash, hashToMatchWith);

	}

	public static String getMD5Hash(String[] inputs) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// Should never happen...
			throw new RuntimeException(e);
		}
		for (String input : inputs) {
			md.update(input.getBytes());
		}
		byte[] res = md.digest();
		byte[] b64 = Base64.encodeBase64(res);
		return new String(b64);
	}

}
