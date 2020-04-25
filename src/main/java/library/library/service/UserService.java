package library.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import library.library.entity.User;


public interface UserService extends IService<User> {
    public User finduserBynam(String username);
}
