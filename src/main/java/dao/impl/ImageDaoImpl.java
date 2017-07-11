package dao.impl;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import dao.ImageDao;
import util.MongoDBManager;

import java.io.File;

/**
 * Created by zzy on 2017/6/29.
 */
public class ImageDaoImpl extends BaseDaoImpl implements ImageDao{

    @Override
    public boolean saveImage(File myFile, String filenameMD5) {
        try{
        DB db = getMongo().getDB();
        GridFS gfsPhoto = new GridFS(db, "image");
        GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);		// get image file from local drive
        gfsFile.setFilename(filenameMD5);							// set a new filename for identify purpose
        gfsFile.save();
        return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GridFSDBFile getImageByName(String filenameMD5) {
        try{
            DB db = getMongo().getMongoClient().getDB("bookstore");
            GridFS gfsPhoto = new GridFS(db,"image");
            GridFSDBFile outputImage = gfsPhoto.findOne(filenameMD5);
            return outputImage;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
