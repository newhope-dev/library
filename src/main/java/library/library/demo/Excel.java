package library.library.demo;

import library.library.entity.Book;
import library.library.service.BookService;
import library.library.service.Impl.BookServiceImpl;
import library.library.util.SpringContextUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.List;
@Controller
public class Excel {



    public static void main(String[] args) throws IOException {
//        BookService bookService= (BookServiceImpl)SpringContextUtils.getBean("bookServiceImpl");
//        System.out.println(bookService);
//        List<Book> list=bookService.list();
//        System.out.println(list);
        Workbook wb=new HSSFWorkbook();

        File file=new File("f:/test.xls");
        FileInputStream f=new FileInputStream(file);
        Workbook workbook=new HSSFWorkbook(f);
        System.out.println(workbook.getNumberOfSheets());
        for(int num=0;num<workbook.getNumberOfSheets();num++){
            Sheet s=workbook.getSheetAt(num);
            for(int n=0;n<=s.getLastRowNum();n++){
                Row row=s.getRow(n);
                Cell c=row.getCell(0);
                Cell name=row.getCell(1);
                System.out.println(c+" "+name);
            }
        }

        Sheet sheet1=wb.createSheet("sex");

        Cell cell=sheet1.createRow(0).createCell(2);
        cell.setCellValue("hi,there");
        System.out.println(cell.getRichStringCellValue().toString());
        try{
            FileOutputStream output=new FileOutputStream("test.xls");
            ((HSSFWorkbook) wb).write(output);
            output.close();
        }catch (Exception e){

        }
    }
}
