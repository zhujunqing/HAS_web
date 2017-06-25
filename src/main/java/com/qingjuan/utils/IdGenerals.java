package utils;

import java.util.Date;
import java.util.Random;

public class IdGenerals {

    public static final String numberChar = "0123456789";

    /** 
    * <p>方法名称：getRandomId</p>
    * <p>方法描述：获取随机ID</p>
    * <p>@return long</p>
    **/
    public static long getRandomId() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        long time = date.getTime();
        time = time >> 8;
        String suffix = generateRandomString(5);
        StringBuilder sb = new StringBuilder();
        sb.append(time).append(suffix);
        return Long.valueOf(sb.toString());
    }

    public static String getRandomIdStr() {
        return getRandomId() + "";
    }

    /** 
    * <p>方法名称：generateRandomString</p>
    * <p>方法描述：生成指定位数随时数</p>
    *<p> 创建时间：2016年5月11日上午10:47:55</p>
    * <p>@param length
    * <p>@return String</p>
    *  
    * @author 拜力文
     **/
    public static String generateRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            long randomId = IdGenerals.getRandomId();

            String a = randomId + "";
            Thread.currentThread().sleep(100L);
            System.out.println(randomId);
            System.out.println(a.length());
        }
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }
}
