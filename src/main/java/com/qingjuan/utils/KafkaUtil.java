package utils;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class KafkaUtil {
	private static Logger logger = LoggerFactory.getLogger(KafkaUtil.class);
	 public final static String ZK =MyConst.ZK;
	public final static String GROUP_ID = MyConst.GROUP_ID;
	public final static String TOPIC = MyConst.TOPIC;
	 public final static String BROKER_LIST = MyConst.BROKER_LIST;
	public final static int BUFFER_SIZE = MyConst.BUFFER_SIZE;
	public final static int TIMEOUT = MyConst.TIMEOUT;
	public final static int INTERVAL = MyConst.INTERVAL;
	private static Properties properties = new Properties();
	static {
		properties.put("bootstrap.servers", BROKER_LIST);
		properties.put("metadata.broker.list", BROKER_LIST);
		properties.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		properties.put("request.required.acks", "1");

		// test
		// properties.put("zookeeper.connect", CopyOfKafkaDao.ZK);//声明zk
		// properties.put("serializer.class", "kafka.serializer.StringEncoder");
		// properties.put("metadata.broker.list", BROKER_LIST);
	}

	public static void main(String[] args) {
//		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(
//				properties);
//		for (int iCount = 0; iCount < 100; iCount++) {
//			String message = "My Test Message No " + iCount;
//			System.out.println(message);
//			ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>(
//					KafkaUtil.TOPIC, message);
//			producer.send(record);
//		}
//		producer.close();

		// test
//		Producer<Integer, String> producer1 = new Producer<Integer, String>(
//				new ProducerConfig(properties));
//		int offsetNo = 1;
//		while (true) {
//			String msg = new String("Message_" + offsetNo++);
//			System.out.println("Send->[" + msg + "]");
//			producer1.send(new KeyedMessage<Integer, String>(TOPIC, msg));
//			for (int i = 0; i < 10000000; i++) {
//			}
//		}
		//test my method
		writeToKafka("test my method1");
//		List<String> list = new ArrayList<String>();
//		list.add("a");
//		list.add("b");
//		batchWriteToKafka(list);
	}
	/**
	 * 向kafk写入单条记录
	 * @param msg
	 */
	public static void writeToKafka(String msg) {
		Producer<Integer, String> producer = new Producer<Integer, String>(
				new ProducerConfig(properties));
		logger.debug("writeToKafka======="+msg);
		producer.send(new KeyedMessage<Integer, String>(TOPIC, msg));
		producer.close();
	}
	/**
	 * 批量写入kafka
	 * @param list
	 */
	public static void batchWriteToKafka(List<String> list){
		Producer<Integer, String> producer = new Producer<Integer, String>(
				new ProducerConfig(properties));
		if(list==null)return;
		logger.debug("list.size======="+list.size());
		for (String msg : list) {
			producer.send(new KeyedMessage<Integer, String>(TOPIC, msg));
		}
		producer.close();
	}
}
