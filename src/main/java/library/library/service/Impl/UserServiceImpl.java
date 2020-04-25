package library.library.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import library.library.entity.User;
import library.library.mapper.UserMapper;
import library.library.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User finduserBynam(String username) {
        return userMapper.finduserBynam(username);
    }
}
