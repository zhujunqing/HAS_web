package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	//配置文件的路径，项目所有需要手动读取的配置参数均保存在config.properties中(还有一些其他配置文件，但其他配置文件都是不需要手动读取的)
	private final String configFilePath= "config/config.properties";
	
	private static Properties prop = new Properties();
	private static final ConfigUtil instance=new ConfigUtil();
	
	private  ConfigUtil() {
		init(configFilePath);
	}
	
	public static ConfigUtil getInstance(){
		return instance;
	}
	
	private void init(String fileName){
		try {
			InputStream in=this.getClass().getClassLoader().getResourceAsStream(fileName);
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		return prop.getProperty(key).toString().trim();
	}

	/**
	 * 获取String
	 * @param key
	 * @param defaultValue
     * @return
     */
	public String getString(String key,String defaultValue){
		if(prop.containsKey(key))
			return prop.getProperty(key).toString().trim();
		else
			return defaultValue;
	}

	/**
	 * 获取int
	 * @param key
	 * @param defaultValue
     * @return
     */
	public int getInt(String key,int defaultValue){
		if(prop.containsKey(key))
			return Integer.parseInt(prop.getProperty(key).toString().trim());
		else
			return defaultValue;
	}
	/** 
	* <p>方法名称：getBoolean</p>
	* <p>方法描述：獲取boolean值</p>
	*<p> 创建时间：2016年10月14日上午10:51:46</p>
	* <p>@param key
	* <p>@param defaultValue
	* <p>@return boolean</p>
	*  
	* @author 拜力文
	 **/
	public boolean getBoolean(String key,boolean defaultValue){
	    if(prop.containsKey(key))
	        return Boolean.parseBoolean(prop.getProperty(key).toString().trim());
	    else
	        return defaultValue;
	}
	
	//测试方法
	public static void main(String[] args) {
		String value =ConfigUtil.getInstance().getString("openoffice.ip","127.0.0.1");
		System.out.println(value);
	}
}
