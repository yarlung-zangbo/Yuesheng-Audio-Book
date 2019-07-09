package yuesheng.tv.Utility;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

//mongodb 连接数据库工具类
public class MongoDBUtil {
    //不通过认证获取连接数据库对象
    public static MongoDatabase getConnect() {
        //连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("YueSheng");

        //返回连接数据库对象
        return mongoDatabase;
    }

}