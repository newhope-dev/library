package library.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import library.library.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User finduserBynam(String username);
}
