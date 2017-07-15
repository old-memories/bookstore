package act;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import entity.Category;
import org.apache.struts2.json.JSONCleaner;
import org.apache.struts2.json.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.BookService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.Cart;
import service.ImageService;
import util.MD5Generator;
import util.MongoDBManager;


/**
 * Created by zzy on 2017/4/19.
 */
public class Item extends ActionSupport{
    private int bookid;
    private int rows;
    private int page;
    private Book book;
    private Category category;
    private String categoryName;
    private int categoryid;
    private int amount;
    private File myFile;
    private String myFileFileName;
    private String myFileContentType;
    private String searchName;
    private Map<String,Object> dataMap = new HashMap<String, Object>();
    @Autowired
    private BookService bookService;
    @Autowired
    private ImageService imageService;
    //@Autowired
    //private MongoDBManager mongo;

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public void setSearchName(String searchName){
        this.searchName=searchName;
    }

    public String getSearchName(){
        return searchName;
    }

    public void setMyFile(File myFile){
        this.myFile=myFile;
    }

    public File getMyFile(){
        return myFile;
    }

    public String getMyFileFileName(){
        return myFileFileName;
    }
    public void setMyFileFileName(String myFileFileName){
        this.myFileFileName= myFileFileName;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }

    public int getAmount(){
        return  amount;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
    public int getBookid(){
        return bookid;
    }
    public int getRows(){return rows;}
    public void setRows(int rows){this.rows=rows;}
    public int getPage(){return page;}
    public void setPage(int page){this.page=page;}

    public Book getBook() {
        return book;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category=category;
    }

    public int getCategoryid(){
        return categoryid;
    }

    public void setCategoryid(int categoryid){
        this.categoryid=categoryid;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    public String getBooks(){
        dataMap.clear();
        dataMap.put("total",bookService.getTotalAmount());
        dataMap.put("rows",bookService.getBooks());
        dataMap.put("success",true);
        return "ajax";
    }

    public String createBook(){
        ActionContext.getContext().put("category",bookService.getAllCategory());
        return "create";
    }

    public String getAllCategory(){
        dataMap.clear();
        dataMap.put("total",bookService.getTotalCategoryAmount());
        dataMap.put("rows",bookService.getAllCategory());
        dataMap.put("success",true);
        return "ajax";
    }

    public  String showBooksOfCategoryByid(){
        System.out.println("categoryid: "+categoryid);
        System.out.println("categoryName: "+categoryName);
        ActionContext.getContext().put("books",bookService.showBooksOfCategoryByid(categoryid));
        ActionContext.getContext().put("category_nav",categoryName);
        ActionContext.getContext().put("category",bookService.getAllCategory());
        return "booksOfCategory";
    }

    public String saveCategory(){
        System.out.println("saving category...");
        dataMap.clear();
        System.out.println(category);
        dataMap.put("success",bookService.saveCategory(category));
        System.out.println("saved category...");
        return"ajax";
    }

    public String updateCategory(){
        System.out.println("updating category...");
        dataMap.clear();
        System.out.println("categoryid: "+categoryid);
        category.setCategoryid(categoryid);
        System.out.println(category);
        dataMap.put("success",bookService.updateCategory(category));
        System.out.println("updated category...");
        return "ajax";
    }

    public String destroyCategory(){
        System.out.println("destroying category...");
        dataMap.clear();
        System.out.println(categoryid);
        dataMap.put("success",bookService.destroyCategory(categoryid));
        System.out.println("destroyed category...");
        return "ajax";
    }


    public String saveBook(){
        /*
        System.out.println("saving book...");
        dataMap.clear();
        System.out.println(book);
        dataMap.put("success",bookService.saveBook(book));
        System.out.println("saved book...");
        return"ajax";
        */

            /* save image of book in mongoDB*/
            System.out.println("saving image");
            /*
            DB db = mongo.getDB();
            GridFS gfsPhoto = new GridFS(db, "image");
            GridFSInputFile gfsFile = gfsPhoto.createFile(myFile);		// get image file from local drive
            Date dt = new Date();
            String newFileName = book.getBookname() + "-" + dt.getTime() + myFileFileName;

            gfsFile.setFilename(filenameMD5);							// set a new filename for identify purpose
            gfsFile.save();												// save the image file into mongoDB
            */

            Date dt = new Date();
            String newFileName = book.getBookname() + "-" + dt.getTime() + myFileFileName;
            String filenameMD5 = MD5Generator.stringMD5(newFileName);
            imageService.saveImage(myFile,filenameMD5);
            book.setImageid(filenameMD5);

            /* save category */
            System.out.println("saving category");
            book = bookService.setCategoryByCategoryName(book,categoryName);
            /*
            String[] categoryArr = categoryName.split(",");
            Set<Category> categories = new HashSet<Category>();
            for(int i=0;i<categoryArr.length;i++){
                categoryArr[i] = categoryArr[i].trim();
                System.out.println(categoryArr[i]);
                categories.add(bookService.getCategoryByid(Integer.parseInt(categoryArr[i])));

            }
            book.setCategory(categories);
            System.out.println("categories:\n"+book.getCategory());
            */

            /* save book(with imageid(MD5)) in mysql */
            System.out.println("saving book");
            bookService.saveBook(book);

        return "saved_book";
    }

    public String updateBook(){
        System.out.println("updating book...");
        dataMap.clear();
        System.out.println("bookid: "+bookid);
        book.setBookid(bookid);
        System.out.println(book);
        dataMap.put("success",bookService.updateBook(book));
        System.out.println("updated book...");
        return "ajax";
    }

    public String destroyBook(){
        System.out.println("destroying book...");
        dataMap.clear();
        System.out.println(bookid);
        dataMap.put("success",bookService.destroyBook(bookid));
        System.out.println("destroyed book...");
        return "ajax";
    }

    public String showBookByid(){
        ActionContext.getContext().put("category",bookService.getAllCategory());
        ActionContext.getContext().put("book",bookService.showBookByid(bookid));
        ActionContext.getContext().put("book_category",bookService.getCategoriesOfBookByid(bookid));
        return "success";
    }

    public String search(){
        ActionContext.getContext().put("books",bookService.getBooksNameLike(searchName));
        ActionContext.getContext().put("category",bookService.getAllCategory());
        ActionContext.getContext().put("search_nav",searchName);
        return "search";
    }


}
