package library.library.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import library.library.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    public List<Book> find();
}
