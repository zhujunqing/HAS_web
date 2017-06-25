package utils;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by zhujunqing on 2017/5/17.
 */
public class DBUtils {
    private final String jdbcFilePath = "config/jdbc.properties";
    private static Properties props = new Properties();
    private static final DBUtils instance = new DBUtils();

    private static String driver;
    private static String url;
    private static String dbUser;
    private static String dbPassword;

    private DBUtils() {
        init(jdbcFilePath);
    }

    private void init(String fileName) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBUtils getInstance() {
        return instance;
    }

    public String get(String key) {
        return props.getProperty(key).toString().trim();
    }

    /**
     * 读入filename指定的文件并解析，取出键值对中的数据，给全局变量url,dbUser,dbPassword赋值
     */
    public static void getParam() {
        DBUtils dbUtils = new DBUtils();
        try {
            driver = dbUtils.get("driver");
            url = dbUtils.get("url");
            dbUser = dbUtils.get("dbUser");
            dbPassword = dbUtils.get("dbPassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用getParam方法获得参数，构造连接并返回
     */
    public static Connection getConnection() {
        getParam();
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接(connection)
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接(statement)
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接(resultset)
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String sql = "select * from dronedb.dbo.DroneJobs";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String droneJobID = rs.getString("DroneJobID");
                System.out.println(droneJobID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> convertList(ResultSet rs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public static Map<String, Object> convertMap(ResultSet rs) {
        Map<String, Object> map = new TreeMap<String, Object>();
        try {
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    map.put(md.getColumnName(i), rs.getObject(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return map;
        }
    }
}