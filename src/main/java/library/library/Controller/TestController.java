package library.library.Controller;

import library.library.common.Result;
import library.library.entity.Book;
import library.library.service.BookService;
import library.library.service.Impl.BookServiceImpl;
import library.library.util.SpringContextUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@RestController
public class TestController {


    @RequestMapping("/testexcel")
    public Result listToexcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result=new Result();
        BookService bookService= (BookService) SpringContextUtils.getBean("bookServiceImpl");
        System.out.println(bookService);
        List<Book> list=bookService.list();
        System.out.println(list);
        Workbook wb=new HSSFWorkbook();

        Sheet sheet1=wb.createSheet("sex");

        Cell cell=sheet1.createRow(0).createCell(2);
        cell.setCellValue("hi,there");
        System.out.println(cell.getRichStringCellValue().toString());

        String filename= URLEncoder.encode("员工表.xls","UTF-8");
       // response.setContentType("application/octet-stream");
       // response.setHeader("content-disposition","attachment;filename="+filename);
        response.setHeader("filename",filename);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(list.toString());
        try{
          // ((HSSFWorkbook) wb).write(response.getOutputStream());

        }catch (Exception e){

        }
        return  null;

    }
    @RequestMapping("/image")
    public Result importexcel(HttpServletRequest request,@RequestParam("uploadfile") MultipartFile file){
        Result result=new Result();
        System.out.println("是否运行");
        //检查文件是否为空
        if(file.isEmpty()){
            result.setMessage("文件为空");
            return result;
        }
       //确定文件夹
        String parentPath=request.getServletContext().getRealPath("upload");
        File parent=new File(parentPath);
        if(!parent.exists()){
            parent.mkdirs();
        }
        //确定文件名
        String filename= UUID.randomUUID().toString();
        String originalFilename=file.getOriginalFilename();
        int beginIndex=originalFilename.lastIndexOf(".");
        String suffix=originalFilename.substring(beginIndex);
        String child=filename+suffix;
        //保存用户上传的文件
        File dest=new File(parent,child);
        System.out.println(dest);
        try{
            file.transferTo(dest);
        }catch (Exception e){

        }
        String avatarPath="/upload/"+child;
        result.setResult(avatarPath);

        return result;
    }

}
