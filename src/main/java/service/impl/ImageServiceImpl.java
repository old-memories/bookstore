package service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import dao.ImageDao;
import service.ImageService;
import util.MD5Generator;

import java.io.File;
import java.util.Date;

/**
 * Created by zzy on 2017/6/29.
 */
public class ImageServiceImpl implements ImageService {
    private ImageDao imageDao;
    @Override
    public boolean saveImage(File myFile, String filenameMD5) {

        return imageDao.saveImage(myFile,filenameMD5);
    }

    @Override
    public GridFSDBFile getImageByName(String filename) {
        return imageDao.getImageByName(filename);
    }

    public ImageDao getImageDao(){
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao){
        this.imageDao=imageDao;
    }
}
