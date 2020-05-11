package library.library.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import library.library.common.Result;
import library.library.entity.Book;
import library.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorrowController {
    @Autowired
    BookService bookService;
    @RequestMapping("/list")
    public Result getbook(){
        Result result=new Result();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("userid","ui");
        List<Book> list= bookService.list(queryWrapper);
        Page<Book> page=new Page<>(1,10);
        IPage<Book> ipagelist= bookService.page(page,queryWrapper);
        result.setResult(ipagelist);

        return result;

    }
}
