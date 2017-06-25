package utils;

import java.io.*;

/** 
 * <p>文件名称: FileUtils.java</p>
 * 
 * <p>文件功能:文件操作工具类 </p>
 *
 * <p>编程者: 朱俊清</p>
 * 
 * <p>初作时间: 2016年9月3日 下午2:37:27</p>
 * 
 * <p>版本: version 1.0 </p>
 *
 * <p>输入说明: </p>
 *
 * <p>输出说明: </p>
 *
 * <p>程序流程: </p>
 * 
 * <p>============================================</p>
 * <p>修改序号:</p>
 * <p>时间:	 </p>
 * <p>修改者:  </p>
 * <p>修改内容:  </p>
 * <p>============================================</p>
 */
public class FileUtils {
    /** 
    * <p>方法名称：getBytes</p>
    * <p>方法描述： 获得指定文件的byte数组 </p>
    *<p> 创建时间：2016年9月3日下午2:37:15</p>
    * <p>@param filePath
    * <p>@return byte[]</p>
    *  
    * @author 朱俊清
     **/
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
    public static byte[] getBytes(InputStream in) {
        byte[] buffer = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = in.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            in.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /** 
     * 根据byte数组，生成文件 
     */
    /** 
    * <p>方法名称：writeFile</p>
    * <p>方法描述：将指定字节数组写出到本地磁盘</p>
    *<p> 创建时间：2016年9月3日下午2:37:55</p>
    * <p>@param bfile
    * <p>@param filePath
    * <p>@param fileName void</p>
    *  
    * @author 朱俊清
     **/
    public static void writeFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {//判断文件目录是否存在  
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /** 
    * <p>方法名称：deleteFileDir</p>
    * <p>方法描述：删除该目录下所有文件</p>
    *<p> 创建时间：2016年9月3日下午2:38:38</p>
    * <p>@param filePath void</p>
    *  
    * @author 朱俊清
     **/
    public static void deleteFileDir(String filePath) {
        File dir = new File(filePath);
        if (dir.exists() && dir.isDirectory()) {//判断文件目录是否存在  
            if(dir.listFiles().length>0){
                for(File f:dir.listFiles()){
                    deleteFileDir(f.getAbsolutePath());
                }
            }
            dir.delete();
        }
        dir.delete();
    }
}
