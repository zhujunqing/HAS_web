package utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 利用HttpClient进行post请求和get请求的工具类 
 * 
 * @author zjq
 */
public class HttpClientUtils {

	/**
	 * post的请求
	 * 
	 * @param url
	 * @param map
	 * @param charset
	 * @return
	 */
	public static String doPost(String url,Map<String,String> map,String charset) {
		String result = "";
		CloseableHttpClient httpClient = null;
		HttpPost post = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			post = new HttpPost(url);
			//拼接参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, String> element = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(element.getKey(), element.getValue()));
			}
			if(list.size()>0){
				//这个类是用来把输入数据编码成合适的内容
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				post.setEntity(entity);
			}
			response = httpClient.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == 200){
				HttpEntity httpEntity = response.getEntity();
				result = EntityUtils.toString(httpEntity);
				//消耗掉response
		        EntityUtils.consume(httpEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * get请求
	 * 
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String doGet(String url, String charset){
		String result = "";
		CloseableHttpClient httpClient = null;
		HttpGet get = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			get = new HttpGet(url);
			response = httpClient.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == 200){
				HttpEntity httpEntity = response.getEntity();
				result = EntityUtils.toString(httpEntity, charset);
				EntityUtils.consume(httpEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		//接口4
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("group_id", "1000110");
//		String aa = doPost("http://119.29.138.41:8080/system/admin/call/GroupInfo",map,"utf-8");
//		System.out.println(aa);
		
		//使用get的方法测接口4
//		String aa = doGet("http://119.29.138.41:8080/system/admin/call/GroupInfo?group_id=1000110", "utf-8");
//		System.out.println(aa);
		
		//接口3
		String jsonStr = "{\"dateTime\":\"2016-10-31 14:24:43\",\"data\":[{\"group_name\":\"20161031群组测试20161031\",\"group_remark\":null,\"group_id\":67,\"group_endtime\":\"2016-11-01 14:24:27\",\"list\":[{\"account_mobile\":\"18600409837\",\"account_id\":523,\"group_id\":67,\"account_zuanmaicode\":\"test1\",\"account_name\":\"测试1\"}],\"group_starttime\":\"2016-10-30 14:24:25\",\"group_regino\":\"88\"}],\"userName\":\"493156b8cc2b53beaf321ddc40df3c50664f1463ca6a16cdd783c82b00373cf0a60afb290a915f75fcd23b027ee498ca1b4700b04b536e169ec245aeedf759a9c52c95cab3df8950e8f478730c72ec39ce81aa29d609e28b2b63f0d74d22d459262f574025253d1f0ffa0707fccc500ff8bc57e3056a98d9bb999b74ebd7ff29\"}";
		Map<String,String> map = new HashMap<String,String>();
		map.put("json", jsonStr);
		doPost("http://119.29.138.41:8080/system/admin/call/CallGroup",map,"utf-8");
	}
}
