package com.xufree.learning.util;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class NetworkUtil {
    private static Logger logger = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     */
    public static String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        System.out.println("netWorkUtils getIp:" + ip);
//        if (logger.isInfoEnabled()) {
////            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
//        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
//                if (logger.isInfoEnabled()) {
////                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//                if (logger.isInfoEnabled()) {
//                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
//                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
//                if (logger.isInfoEnabled()) {
//                 logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
//                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 发送POST请求方法
     */
    public static String sendPost(String url, String param) {
        String response = null;
        logger.info("发送POST请求URL:{}", url);
        logger.info("发送POST请求Param:{}", param);
        HttpPost httpPost = new HttpPost(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            StringEntity stringEntity = new StringEntity(param, "UTF-8");
            httpPost.setEntity(stringEntity);
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            int code = httpResponse.getStatusLine().getStatusCode();
            response = EntityUtils
                    .toString(httpResponse.getEntity());
            logger.info("发送POST请求Response:{}", response);
            if (code != 200) {
                return null;
            }
        } catch (Exception e) {
            logger.error("发送POST请求异常", e);
        }
        return response;
    }

    public static String sendGet(String url) {
        String response = null;
        logger.info("发送GET请求URL:{}", url);
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
            int code = httpResponse.getStatusLine().getStatusCode();
            response = EntityUtils
                    .toString(httpResponse.getEntity());
            logger.info("发送GET返回结果Response:{}", response);
            if (code != 200) {
                return null;
            }
        } catch (Exception e) {
            logger.error("发送GET请求异常", e);
        }
        return response;
    }

}
