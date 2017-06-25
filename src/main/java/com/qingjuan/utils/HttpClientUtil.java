package utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <P>File name : HttpclientUtil.java </P>
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static PoolingClientConnectionManager connectionManager;
    private static HttpParams httpParams;
    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 500;
    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 500;
    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 15000;
    /**
     * 获取连接的最大等待时间
     */
    public final static int WAIT_TIMEOUT = 30000;

    private static HttpClient client;

    private static String[] UserAgent = {
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0"

    };
    static {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(
                new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        connectionManager = new PoolingClientConnectionManager();
        // 设置最大连接数
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        // 设置每个路由最大连接数
        connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, WAIT_TIMEOUT);
        if(client==null){
            client = new DefaultHttpClient(connectionManager, httpParams);
        	client.getParams().setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET,"utf-8");
        }
    }
    public static HttpClient getClient(){
        return client;
    }
    public static String httpGetBody(String url){
        String body = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);

            httpGet.setHeader("User-Agent", UserAgent[RandomUtils.nextInt(UserAgent.length)]);
            httpGet.setHeader("Cookie","PREF=hl=zh-CN&f1=50000000&fv=15.0.0&f5=20030&fms1=10000&fms2=10000&al=zh-CN&gl=HK");
            httpGet.setHeader("accept-language","zh-CN,zh;q=0.8");
            HttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    body = new String(bytes,"UTF-8");

                }
            }
        } catch (Exception e) {
            logger.error("httpGetBody error url:{}",url,e);
        } finally {

            if(httpGet!=null){
                httpGet.releaseConnection();
            }
        }
        return body;
    }
    public static String httpGetTestBody(String url){
        String body = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", UserAgent[RandomUtils.nextInt(UserAgent.length)]);
            httpGet.setHeader("Cookie","PREF=hl=zh-CN&f1=50000000&fv=15.0.0&f5=20030&fms1=10000&fms2=10000&al=zh-CN&gl=HK");
            httpGet.setHeader("accept-language","zh-CN,zh;q=0.8");
            HttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    body = new String(bytes,"UTF-8");
                }
            }
        } catch (Exception e) {
            logger.error("httpGetTestBody  error url:{}",url,e);
        } finally {

            if(httpGet!=null){
                httpGet.releaseConnection();
            }
        }
        return body;
    }
    public static UrlEncodedFormEntity getparams(Map<String, String> map) {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> enter = iter.next();
            params.add(new BasicNameValuePair(enter.getKey(), enter.getValue()));
        }
        // 将参数进行编码
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
            uefEntity.setContentType("application/x-www-form-urlencoded");
        } catch (UnsupportedEncodingException e) {
            logger.error("getparams  error map:{}",map.toString());

        }
        return uefEntity;
    }
    public static String httpPostBody(String url,Map<String, String> map){
        String body = null;
        HttpPost httpPost = null;
        InputStream instream = null;
        try {
            httpPost = new HttpPost(url);
            UrlEncodedFormEntity uefEntity = getparams(map);
            httpPost.setEntity(uefEntity);
            httpPost.setHeader("User-Agent", UserAgent[RandomUtils.nextInt(UserAgent.length)]);
            HttpResponse response = client.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    instream = entity.getContent();
                    BufferedReader read = new BufferedReader(new InputStreamReader(instream));
                    String line = null;
                    StringBuffer buffer = new StringBuffer();
                    while((line=read.readLine())!=null){
                        buffer.append(line);
                    }
                    body = buffer.toString();
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            Object[] objects = {url,map.toString(),e};
            logger.error("httpPostBody  error url:{},map:{}",objects);
        } finally {
            if(instream!=null){
                try {
                    instream.close();
                } catch (IOException e) {
                    logger.error("httpPostBody close instream error url:{}",url,e);
                }
            }
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
        }
        return body;
    }
    public static byte[] httpGetData(String url){
        byte[] data = null;
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent", UserAgent[RandomUtils.nextInt(UserAgent.length)]);
            HttpResponse response = client.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                System.out.println(url+"  "+entity.getContentLength());
                if(entity!=null){
                    data = EntityUtils.toByteArray(entity);
                }
            }
        } catch (Exception e) {
            logger.error("httpGetData  error url:{}",url,e);
        } finally {
            if(httpGet!=null){
                httpGet.releaseConnection();
            }
        }
        return data;
    }

    public static String postJson(String url,JSONObject json){
        String body = null;
        HttpPost post = null;
        InputStream instream = null;
        try {
            post  = new HttpPost(url);

            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);

            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = res.getEntity();
                System.out.println(url+"  "+entity.getContentLength());
                if(entity!=null){
                    instream = entity.getContent();
                    BufferedReader read = new BufferedReader(new InputStreamReader(instream));
                    String line = null;
                    StringBuffer buffer = new StringBuffer();
                    while((line=read.readLine())!=null){
                        buffer.append(line);
                    }
                    body = buffer.toString();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return body;
    }
    
}

