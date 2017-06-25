package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujunqing on 2017/6/9.
 */
public class DruidUtils {

    private static String user = null;
    private static String password = null;
    private static String driver = null;
    private static String url = null;
    private static Connection conn = null;
    private static DataSource dataSource = null;

    //单利模式 --懒汉式(双重锁定)保证线程的安全性
    public static DruidUtils druidUtils = null;

    private DruidUtils() {
    }

    public static DruidUtils getInstance() {
        if (druidUtils == null) {
            synchronized (DruidUtils.class) {
                if (druidUtils == null) {
                    druidUtils = new DruidUtils();
                }
            }
        }
        return druidUtils;
    }

    //读取配置文件且加载数据库驱动
    /*static {
        try {
            //读取配置文件
            driver = ConfigUtil.getInstance().get("driver");
            url = ConfigUtil.getInstance().get("url");
            user = ConfigUtil.getInstance().get("dbUser");
            password = ConfigUtil.getInstance().get("dbPassword");
            Map<String, String> druidMap = new HashMap<String, String>();
            druidMap.put(DruidDataSourceFactory.PROP_USERNAME, user);
            druidMap.put(DruidDataSourceFactory.PROP_PASSWORD, password);
            druidMap.put(DruidDataSourceFactory.PROP_URL, url);
            druidMap.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driver);
            dataSource = DruidDataSourceFactory.createDataSource(druidMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    //建立数据库的连接
    public Connection getConnection(String driver, String url, String user, String password) {
        try {
            //读取配置文件
            Map<String, String> druidMap = new HashMap<String, String>();
            druidMap.put(DruidDataSourceFactory.PROP_USERNAME, user);
            druidMap.put(DruidDataSourceFactory.PROP_PASSWORD, password);
            druidMap.put(DruidDataSourceFactory.PROP_URL, url);
            druidMap.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driver);
            dataSource = DruidDataSourceFactory.createDataSource(druidMap);
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭资源
    public static void close(ResultSet rs,PreparedStatement pst,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(pst!=null){
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pst = null;
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public static void main(String[] args) throws SQLException {
        /*String sql = "select * from [dronedb].[dbo].[DroneJobs]";
        Connection conn = DruidUtils.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("DroneJobID"));
        }*/

        /*String sql = "insert into DroneJobs(DroneJobID) values(?)";
        Connection conn = DruidUtils.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setNString(1,11+"");
        stmt.execute();*/
    }

}
