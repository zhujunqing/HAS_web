package utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DateUtil.class);
	
	private static DateFormat normal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	private static DateFormat simple = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /**
     * yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
	public static String getNormalDate(Date date) {
		if (date == null) {
			return "";
		}
		return normal.format(date);
	}

    /**
     * yyyy-MM-dd HH:mm:ss long time
     * @param time
     * @return
     * @throws ParseException
     */
    public static long getNormalTimeLong(String time) throws ParseException {
        Date date = normal.parse(time);
        return date.getTime();
    }

    /**
     * yyyy-MM-dd
     * @param date
     * @return
     */
    public static String getSimpleDate(Date date) {
        if (date == null) {
            return "";
        }
        return simple.format(date);
    }
    /**
     * yyyy-MM-dd now
     * @return
     */
	public static String getSimpleNow() {
		return simple.format(new Date());
	}




    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");



    public static boolean compareDateTime(Date d1,Date d2) {
        return d1.getTime() > d2.getTime();
    }

    /**
     * 日期转字符串，格式为2015-04-10
     * @param collectDate
     * @return
     */
    public static String dateToString(Date collectDate) {
        if(collectDate != null){
            return sdf2.format(collectDate);
        }
        return null;
    }

    /**
     * 日期转字符串，格式为2015-04-10 11:11
     * @param collectDate
     * @return
     */
    public static String dateHourToString(Date collectDate) {
        if(collectDate != null){
            return sdf.format(collectDate);
        }
        return "";
    }

    /**
     * 字符串转日期，格式为2015-04-10
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr){
        try {
            return sdf2.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {

		return date2String(date, DEFAULT_FORMAT);
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date, String format) {
		if (date == null) {
			return null;
		}
		if (StringUtils.isBlank(format)) {
			format = DEFAULT_FORMAT;
		}
		return date2String(date, new SimpleDateFormat(format));
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @param df
	 * @return
	 */
	public static String date2String(Date date, DateFormat df) {
		if (date == null) {
			return null;
		}
		if (df == null) {
			df = new SimpleDateFormat(DEFAULT_FORMAT);
		}
		return df.format(date);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 *            日期字符串
	 * @return
	 */
	public static Date string2Date(String date) {

		return string2Date(date,DEFAULT_FORMAT);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date string2Date(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		if (StringUtils.isBlank(format)) {
			format = DEFAULT_FORMAT;
		}
		return string2Date(date, new SimpleDateFormat(format));
	}

	/**
	 * 字符串转日期
	 * 
	 * @param date
	 * @param df
	 * @return
	 */
	public static Date string2Date(String date, DateFormat df) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		if (df == null) {
			df = new SimpleDateFormat(DEFAULT_FORMAT);
		}
		Date d = null;
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			logger.error("parse date error {},cause {}",
					new Object[] { date, e.getMessage() });
			e.printStackTrace();
		}
		return d;
	}



}
