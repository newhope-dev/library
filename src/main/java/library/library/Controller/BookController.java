package library.library.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import library.library.common.Result;
import library.library.entity.Book;
import library.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    public BookService bookService;
    @GetMapping("/list")
    public Result<?> querylist(Book book){
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_name","算法导论");
       List<Book> list= bookService.list(queryWrapper);
       return Result.ok(list);
    }

}
