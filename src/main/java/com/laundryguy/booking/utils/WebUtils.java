package com.laundryguy.booking.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class WebUtils {

	private static Logger logger = LogManager.getLogger(WebUtils.class);

	public static String getDomainName(String referrerUrl) {
		String domainName = "";
		try {
			domainName = (new URI(referrerUrl)).getHost();
		} catch (Exception e) {
			domainName = "";
		}
		return domainName;
	}
	public static String absoluteURL(HttpServletRequest request, String path) {
		try {
			int port = request.getServerPort();
			URL url;
			if (80 == port) {
				url = new URL(request.getScheme(), request.getServerName(), request.getContextPath() + "/" + path);
			} else {
				url = new URL(request.getScheme(), request.getServerName(), port,
						request.getContextPath() + "/" + path);
			}
			return (url.toString());
		} catch (MalformedURLException e) {
			return (null);
		}
	}


	public static String getClientIpAddress(HttpServletRequest request) {
		if (request == null) {
			return "";
		}
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
