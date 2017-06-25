package utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;


/**
 * log4j日志Util
 */
public class LogUtil {
    private static Logger logger=Logger.getLogger("access.log");
    private static  String clazzName=LogUtil.class.getName();
	
	private LogUtil(){}
	
	//---------------------error---------------------------------
    public static void error(String msg){
        //必须用这种方法调用才能获取调用者的正确代码行号????
        //上面的这种做法好像并没有什么用
        //但也不影响什么
        logger.log(clazzName, Level.ERROR, msg, null);
    }
    
    public static void error(Throwable e){
        if(e instanceof InvocationTargetException){
            e=((InvocationTargetException) e).getTargetException();
        }
        error("Exception: "+e.toString());
        for(int i=0;i<e.getStackTrace().length;i++){
            error(e.getStackTrace()[i].toString());
        }
    }
    
    public static void error(String msg,Throwable e){
        error(msg);
        if(e instanceof InvocationTargetException){
            e=((InvocationTargetException) e).getTargetException();
        }
        error("Exception: "+e.toString());
        for(int i=0;i<e.getStackTrace().length;i++){
            error(e.getStackTrace()[i].toString());
        }
    }
    
    //---------------------warn----------------------------------
    public static void warn(String msg){
        //必须用这种方法调用才能获取调用者的正确代码行号
        logger.log(clazzName, Level.WARN, msg, null);
    }
    
    //---------------------info----------------------------------
    public static void info(String msg){
        //必须用这种方法调用才能获取调用者的正确代码行号
        logger.log(clazzName, Level.INFO, msg, null);
    }
    //---------------------debug----------------------------------
    public static void debug(String msg){
        //必须用这种方法调用才能获取调用者的正确代码行号
        logger.log(clazzName, Level.DEBUG, msg, null);
    }
    
	
	
}
