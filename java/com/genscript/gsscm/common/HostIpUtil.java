package com.genscript.gsscm.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 获得客户端真实IP地址方法
 * 
 * @author 刘娟
 */
public class HostIpUtil {
	/**
	 * 判断Windows操作系统
	 * 
	 * @return isWindowsOS
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 取当前系统站点本地地址 linux下 和 window下可用 add by RWW
	 * 
	 * @return
	 */
	public static String getLocalIP() {
		String sIP = "";
		InetAddress ip = null;
		try {
			// 如果是Windows操作系统
			if (isWindowsOS()) {
				ip = InetAddress.getLocalHost();
			}
			// 如果是Linux操作系统
			else {
				boolean bFindIP = false;
				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
						.getNetworkInterfaces();
				while (netInterfaces.hasMoreElements()) {
					if (bFindIP) {
						break;
					}
					NetworkInterface ni = (NetworkInterface) netInterfaces
							.nextElement();
					// ----------特定情况，可以考虑用ni.getName判断
					// 遍历所有ip
					Enumeration<InetAddress> ips = ni.getInetAddresses();
					while (ips.hasMoreElements()) {
						ip = (InetAddress) ips.nextElement();
						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
								&& ip.getHostAddress().indexOf(":") == -1) {
							bFindIP = true;
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.print("MultiThreadedTrapReceiver 453: " + e.getMessage());
		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	/**
	 * 获取Linux系统的真实IP
	 * 
	 * @return ip
	 * @throws SocketException
	 */
	public static String getLinuxIP() throws SocketException {
		// 根据网卡取本机配置的IP
		Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ipAddress = null;
		String ip = "";
		while (netInterfaces.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) netInterfaces
					.nextElement();
			if (!ni.getName().equals("eth0")) {
				continue;
			} else {
				Enumeration<?> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					ipAddress = (InetAddress) e2.nextElement();
					if (ipAddress instanceof Inet6Address)
						continue;
					ip = ipAddress.getHostAddress();
					System.out.println("getLinuxIp:" + ip);
				}
				break;
			}
		}
		return ip;
	}

	/**
	 * 获取通过代理或者反向代理的真实IP
	 * 
	 * @param request
	 * @return ip
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 对于windows系统,获取真实ip的方法
	 * 
	 * @param ip
	 * @return macAddress
	 */
	public String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") < 1) {
						macAddress = str.substring(
								str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

	public Integer getPort(HttpServletRequest request) {
		return request.getServerPort();

	}

	public static void main(String[] args) {
		HostIpUtil hu = new HostIpUtil();
		String mscadd = hu.getMACAddress("10.168.2.180");
		System.out.println(mscadd);
	}

}