package act;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import service.ImageService;
import service.UserService;
import util.MD5Generator;
import util.MongoDBManager;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by zzy on 2017/6/6.
 */
public class ImageAct extends ActionSupport {
    private String filename;
    private File myFile;
    private String myFileFileName;
    private String myFileContentType;
    private Integer bookid;


    public String getFilename() {
    	return filename;
}

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }


    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    //@Autowired
    //private MongoDBManager mongo;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    public String viewImage(){

        try{
            if(filename==null||filename.equals(""))
                return null;
            System.out.println(filename);
            /*
            DB db = mongo.getMongoClient().getDB("bookstore");
            GridFS gfsPhoto = new GridFS(db,"image");
            GridFSDBFile outputImage = gfsPhoto.findOne(filename);
            */
            GridFSDBFile outputImage = imageService.getImageByName(filename);
            if(outputImage == null)
                return null;
            System.out.println("Image got.");
            InputStream in = outputImage.getInputStream();
            ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
            byte[] bytes = new byte[1024];
            while(-1!=in.read(bytes))
                out.write(bytes);
            System.out.println("Finished image_view.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String uploadUserImage(){
        try{
            User user = (User) ActionContext.getContext().getSession().get("user");
            System.out.println("saving image");
            /*
            DB db = mongo.getMongoClient().getDB("bookstore");
            GridFS gfsPhoto = new GridFS(db, "image");
            GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);		// get image file from local drive
            */


            /*
            gfsFile.setFilename(filenameMD5);							// set a new filename for identify purpose
            gfsFile.save();												// save the image file into mongoDB
            */
            Date dt = new Date();
            String newFileName = user.getUsername() + "-" + dt.getTime() + myFileFileName;
            String filenameMD5 = MD5Generator.stringMD5(newFileName);
            imageService.saveImage(myFile,filenameMD5);
            //user.setImageid(filenameMD5);
            userService.updateUserProfile(user,filenameMD5);
            return "upload_image";
        }catch (Exception e){
            e.printStackTrace();;
        }
        return "fail";
    }
}
