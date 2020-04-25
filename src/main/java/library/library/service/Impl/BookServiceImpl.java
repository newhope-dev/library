package library.library.service.Impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import library.library.entity.Book;
import library.library.mapper.BookMapper;
import library.library.service.BookService;
import org.springframework.stereotype.Service;

@Service("bookServiceImpl")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
