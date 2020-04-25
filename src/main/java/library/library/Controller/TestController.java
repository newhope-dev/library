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
    public Result importexcel(HttpServletRequest request,@RequestParam("uploadfile") MultipartFile multipartFile){
        Result result=new Result();
        String filename=multipartFile.getOriginalFilename();
        System.out.println(filename);
        String filepath=request.getSession().getServletContext().getRealPath("/")+multipartFile.getOriginalFilename();
        System.out.println(filepath);
        try {
            multipartFile.transferTo(new File(filepath));
            result.setResult(filepath);
            return  result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
