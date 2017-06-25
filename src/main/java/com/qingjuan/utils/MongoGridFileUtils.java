package utils;

import org.springframework.stereotype.Component;

@Component
public class MongoGridFileUtils {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    private final String  defaultTableName = MyConst.fjTableName;
//
//    
//    private DB getDB() {
//        DB db = mongoTemplate.getDb();
//        return db;
//    }
//
//    public void saveFile(String id, byte[] data) throws Exception {
//
//        GridFSInputFile gfsInput;
//        gfsInput = new GridFS(getDB(), defaultTableName).createFile(data);
//        gfsInput.setFilename(id);  //filename还是文件名
//        gfsInput.setId(id);
//        gfsInput.save();
//    }
//
//    public void saveFile(String tableName, String id, byte[] data, String fileName)
//            throws Exception {
//
//        GridFSInputFile gfsInput;
//        gfsInput = new GridFS(getDB(), tableName).createFile(data);
//        gfsInput.setFilename(id);
//        gfsInput.setId(id);
//        gfsInput.save();
//    }
//
//    public void saveFile(String tableName, byte[] data, String fileName, String contentType)
//            throws Exception {
//
//        GridFSInputFile gfsInput;
//        gfsInput = new GridFS(getDB(), tableName).createFile(data);
//        gfsInput.setFilename(fileName);
//        gfsInput.setContentType(contentType);
//        gfsInput.save();
//    }
//
//    public GridFSDBFile findFileById(String tableName, String id) {
//        GridFSDBFile gfsFile;
//        gfsFile = new GridFS(getDB(), tableName).findOne(id);
//        return gfsFile;
//    }
//
//    public byte[] getFileBytes(String tableName, String id) {
//        GridFSDBFile findFileById = findFileById(tableName, id);
//        return FileUtils.getBytes(findFileById.getInputStream());
//    }
//
//    public byte[] getFileBytes(String id) {
//        GridFSDBFile findFileById = findFileById(defaultTableName, id);
//        return FileUtils.getBytes(findFileById.getInputStream());
//    }
//
//    public void deleteFile(String tableName, String id) {
//        new GridFS(getDB(), tableName).remove(id);
//    }
//    public void deleteFile(String tableName) {
//        new GridFS(getDB(), tableName).remove(new ObjectId());
//    }
//    
//
//    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("aa", "vvv");
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(jsonObject);
//        
//        JSONObject cc = new JSONObject();
//        cc.put("dd",jsonArray.toJSONString());
//        System.out.println(cc);
//    }
}
