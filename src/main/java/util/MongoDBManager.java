package util;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Created by zzy on 2017/6/5.
 */
public class MongoDBManager {
    private MongoClient mongo;
    private String hostname;
    private String database;
    private int port;

    public MongoClient getMongoClient(){
        if(mongo==null)
            mongo = new MongoClient(hostname,port);
        return mongo;
    }

    public DB getDB(){
        return getMongoClient().getDB(database);
    }

    public MongoClient getMongo(){
        return mongo;
    }

    public void setMongo(MongoClient mongo){
        this.mongo=mongo;
    }

    public String getHostname(){
        return  hostname;
    }

    public void setHostname(String hostname){
        this.hostname=hostname;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port=port;
    }

    public String getDatabase(){
        return database;
    }

    public void setDatabase(String database){
        this.database=database;
    }





}
