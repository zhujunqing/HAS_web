package utils;

/**
 *Create by junqing.zhu
 */
public class MyConst {
	//kafkaInfo
	public final static String ZK = ConfigUtil.getInstance().get("ZK");
	public final static String GROUP_ID = ConfigUtil.getInstance().get("GROUP_ID");
	public final static String TOPIC = ConfigUtil.getInstance().get("TOPIC");
	 public final static String BROKER_LIST = ConfigUtil.getInstance().get("BROKER_LIST");
	public final static int BUFFER_SIZE = Integer.valueOf(ConfigUtil.getInstance().get("BUFFER_SIZE"));
	public final static int TIMEOUT = Integer.valueOf(ConfigUtil.getInstance().get("TIMEOUT"));
	public final static int INTERVAL = Integer.valueOf(ConfigUtil.getInstance().get("INTERVAL"));

	//计数器
	public static int count = 0; 
	
	/**
	 * 同步开始日期
	 */
	public final static String start_day = ConfigUtil.getInstance().get("start_day");

}
