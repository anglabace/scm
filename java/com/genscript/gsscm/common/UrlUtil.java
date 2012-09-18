package com.genscript.gsscm.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class UrlUtil {

    /**
     * get请求
     */
    public static String getReq(String url) throws Exception {
        String resultStr = null;
        StringBuffer readOneLineBuff = new StringBuffer();
        URL getUrl = new URL(url);
        if (url.startsWith("https://")) {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

            sslContext.init(null, tm, new java.security.SecureRandom());

            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();


            HostnameVerifier hv = new HostnameVerifier()

            {
   
                public boolean verify(String urlHostName, SSLSession session)

                {

                    System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());

                    return true;

                }

            };

            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            HttpsURLConnection connection = (HttpsURLConnection) getUrl
                    .openConnection();
            connection.setSSLSocketFactory(ssf);
            connection.connect();


            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = "";

            while ((line = reader.readLine()) != null) {
                readOneLineBuff.append(line);
            }
            connection.disconnect();
        } else {
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();
            connection.connect();
            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = "";

            while ((line = reader.readLine()) != null) {
                readOneLineBuff.append(line);
            }
            connection.disconnect();
        }
        resultStr = readOneLineBuff.toString();
        return resultStr;
    }


    /*
   判断当前字符是何种编码格式
    */

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
            System.out.println("1");
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
            System.out.println("2");
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
            System.out.println("3");
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
            System.out.println("4");
        }
        return "unknow";
    }


    /**
     * post请求
     */
    public static String postReq(String url, String param) throws Exception {
        String resultStr = null;
        StringBuffer readOneLineBuff = new StringBuffer();
        URL postUrl = new URL(url);
        // 打开连接   
        if (url.startsWith("https://")) {
            HttpsURLConnection connection = (HttpsURLConnection) postUrl
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty(" Content-Type ",
                    " application/x-www-form-urlencoded ");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                readOneLineBuff.append(line);
            }
            connection.disconnect();
        } else {
            HttpURLConnection connection = (HttpURLConnection) postUrl
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty(" Content-Type ",
                    " application/x-www-form-urlencoded ");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                readOneLineBuff.append(line);
            }
            connection.disconnect();
        }
        resultStr = readOneLineBuff.toString();
        return resultStr;
    }

}
