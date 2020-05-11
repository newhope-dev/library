package library.library;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import library.library.entity.Book;
import library.library.mapper.BookMapper;
import library.library.mapper.UserMapper;
import library.library.entity.User;

import library.library.service.BookService;
import library.library.service.Impl.BookServiceImpl;
import library.library.service.UserService;
import library.library.util.SpringContextUtils;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class LibraryApplicationTests {

	@Resource
	UserService userService;
	@Resource
	BookService bookService;
	@Resource
	BookMapper bookMapper;
	private Cell cell6;

	@Test
	void contextLoads() {
	}

	@Test
	public  void d(){
		User u=userService.finduserBynam("易城蓝图");
		System.out.println(u);
	}
	@Test
	public void context(){
		QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("book_name","算法导");
		List<Book> list= bookService.list(queryWrapper);
		list.stream().filter(a->a.getBookName().equals("算法导")).collect(Collectors.toList());
		System.out.println(list);
	}
//	@Test
//	public void Json(){
//		String str="";
//		JSONObject jsonObject=JSONObject.fromObject(str);
//		System.out.println(jsonObject.toString());
//	}

	@Test
	public void s(){
		BookService bookService= (BookService)SpringContextUtils.getBean("bookServiceImpl");
		System.out.println(bookService);
		List<Book> list=bookService.list();
		System.out.println(list);
		Workbook wb=new HSSFWorkbook();

		Sheet sheet1=wb.createSheet("sex");
		Row row=sheet1.createRow(0);
		
		Cell cell=sheet1.createRow(0).createCell(0);
	    Cell cell1=sheet1.createRow(0).createCell(1);
		Cell cell2=sheet1.createRow(0).createCell(2);
		Cell cell3=sheet1.createRow(0).createCell(3);
		Cell cell4=sheet1.createRow(0).createCell(4);
		Cell cell5=sheet1.createRow(0).createCell(5);
		Cell cell6= sheet1.createRow(0).createCell(6);
		Cell cell7=sheet1.createRow(0).createCell(7);
		Cell cell8=sheet1.createRow(0).createCell(8);
		
		cell.setCellValue("序号");
		cell1.setCellValue("书号");
		cell2.setCellValue("书名");
		cell3.setCellValue("作者");
		cell4.setCellValue("类型");
		cell5.setCellValue("价格");
		cell6.setCellValue("出版社");
		cell7.setCellValue("摘要");
		cell8.setCellValue("收藏");
		for(int i=0;i<list.size();i++) {
			row = sheet1.createRow(i + 1);
			Book book = list.get(i);
			Cell xuhao = row.createCell(0);
			xuhao.setCellValue(book.getId());
			Cell no = row.createCell(1);
			no.setCellValue(book.getBookNo());
			Cell name = row.createCell(2);
			name.setCellValue(book.getBookName());
			Cell author = row.createCell(3);
			author.setCellValue(book.getAuthor());
			Cell type = row.createCell(4);
			type.setCellValue(book.getType());
			System.out.println(type.getRichStringCellValue().toString());
			System.out.println(author.getRichStringCellValue().toString());
		}


		try{
			FileOutputStream output=new FileOutputStream("f:/test.xls");
			((HSSFWorkbook) wb).write(output);
			output.close();
		}catch (Exception e){

		}
	}

}
