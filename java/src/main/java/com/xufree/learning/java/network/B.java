package com.xufree.learning.java.network;

import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.parser.UnicodeReader;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangmingxu ON 16:29 2019-04-15
 **/
public class B {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Map<String, String> heads = new HashMap<>();
//        heads.put("Host", "passport.jd.com");
//        heads.put("X-Requested-With", "XMLHttpRequest");
//        heads.put("Connection", "keep-alive");
//        heads.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
//        Document doc = Jsoup.parse(get("http://passport.jd.com/new/login.aspx", heads, null, null));
//        String uuid = doc.getElementById("uuid").val();
//        String eid = doc.getElementById("eid").val();
//        String fp = doc.getElementById("sessionId").val();
//        String _t = doc.getElementById("token").val();
//        String loginType = doc.getElementById("loginType").val();
//        String pubKey = doc.getElementById("pubKey").val();
//        String sa_token = doc.getElementById("sa_token").val();
//
//        Map<String, String> params = new HashMap<>();
//        params.put("uuid", uuid);
//        params.put("eid", eid);
//        params.put("fp", fp);
//        params.put("loginname", "sq01");
//        params.put("_t", _t);
//        params.put("loginType", loginType);
//        params.put("nloginpwd", "123456");
//        params.put("sa_token", sa_token);
//        params.put("pubKey", pubKey);
//        params.put("chkRememberMe", "");
//        String loginUrl = "http://passport.jd.com/uc/loginService?uuid=" + uuid + "&r=0.6335209684602039&version=2015";
//        String response = post(loginUrl, heads, null, params);
        Document doc = Jsoup.parse(get("http://passport.jd.com/new/login.aspx"));
        Thread.sleep(1000);
        Elements elements = doc.select("form[id=formlogin] input[type=hidden]");
        Map<String, String> map = new HashMap<>();
        String k, v;
        for (Element input : elements) {
            k = input.attr("name");
            v = input.attr("value");
            if (StringUtils.isNotBlank(k)) {
                map.put(k, v);
            }
        }
        map.put("loginname", "jd_49659cf4dc915");
        map.put("nloginpwd", "Zhangapple0");
        map.put("eid", "RXI4RZELZBUFTKRYDTWZHA734GHXAKCA5SJQSPGLJ2RXCIZUT3PAYXSRGSC57USYLK6432Z7WJW36ZEEGVWMLILT3E");
        map.put("fp", "ccbe046c9993c80f4838e591159ce563");
        String result = get("http://seq.jd.com/jseqf.html?bizId=passport_jd_com_login_pc&platform=js&version=1");
        Thread.sleep(1000);
        String pattern = "sessionId=.+_jdtdseq_config_data";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(result);
        if (m.find()) {
            map.put("seqSid", m.group().substring(11, 29));
        }
        String url = "https://passport.jd.com/uc/loginService?";
        url = url + "&uuid=" + map.get("uuid") + "&r=" + Math.random() + "&version=2015";
        System.out.println(post(url, null, null, map));


    }

    public static String get(String url) throws IOException {
        return get(url, null, null, null);
    }

    public static String get(String url, Map<String, String> heads
            , Map<String, String> cookies, Map<String, String> params) throws IOException {
        if (!emptyMap(params)) {
            StringBuilder paramStringBuilder = new StringBuilder(url).append("?");
            params.forEach((k, v) -> {
                paramStringBuilder.append(k).append("=").append(v).append("&");
            });
            String paramUrl = paramStringBuilder.toString();
            url = paramUrl.substring(0, paramUrl.length() - 1);
        }
        HttpURLConnection connection = getConnection(url, "GET", heads, cookies);
        return getResponse(connection);
    }


    public static String post(String url, Map<String, String> heads
            , Map<String, String> cookies, Map<String, String> params) throws IOException {
        HttpURLConnection connection = getConnection(url, "POST", heads, cookies);
        if (!emptyMap(params)) {
            StringBuilder stringBuilder = new StringBuilder();
            params.forEach((k, v) -> stringBuilder.append(k).append("=").append(v));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(stringBuilder.toString());
            outputStreamWriter.close();
        }

        return getResponse(connection);
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
        StringBuilder responseString = new StringBuilder();
        String line;
        while (StringUtils.isNotBlank(line = reader.readLine())) {
            responseString.append(line);
        }
        connection.getHeaderFields();
        inputStream.close();
        reader.close();
        return responseString.toString();
    }

    private static HttpURLConnection getConnection(String url, String method, Map<String, String> heads
            , Map<String, String> cookies) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod(method);
        setHeads(connection, heads);
        setCookies(connection, cookies);
        return connection;
    }


    private static void setHeads(HttpURLConnection connection, Map<String, String> heads) {
        if (emptyMap(heads)) {
            return;
        }
        heads.forEach(connection::setRequestProperty);
    }

    private static void setCookies(HttpURLConnection connection, Map<String, String> cookies) {
        if (emptyMap(cookies)) {
            return;
        }
        StringBuilder cookieString = new StringBuilder();
        cookies.forEach((k, v) -> cookieString.append(k).append("=").append(v).append("; "));
        connection.setRequestProperty("cookie", cookieString.toString());
    }

    private static boolean emptyMap(Map map) {
        return map == null || map.isEmpty();
    }

    private static void copyBytes(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
        if (inputStream == null || outputStream == null) {
            return;
        }
        byte[] buffer = new byte[bufferSize];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
    }

    public static String unicodeToString(String unicode) {
        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i], 16);
            sb.append((char) index);
        }
        return sb.toString();
    }
}
