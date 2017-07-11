package dao;

import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;

/**
 * Created by zzy on 2017/6/29.
 */
public interface ImageDao extends BaseDao {
    boolean saveImage(File myFile,String filenameMD5);
    GridFSDBFile getImageByName(String filenameMD5);


}
