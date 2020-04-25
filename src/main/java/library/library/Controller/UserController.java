package library.library.Controller;

import library.library.common.Result;
import library.library.entity.User;
import library.library.service.UserService;
import library.library.util.Md5;
import library.library.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping("/add")
    public Result addUser(String username,String password){
         Result result=new Result();
        String salt= UUID.randomUUID().toString();
        String pass= Md5.getMd5(password,salt);
        User user=new User();
        user.setUserName(username);
        user.setSalt(salt);
        user.setPassword(pass);
        user.setStatus("1");
        user.setDelflag("0");
        Boolean b=userService.save(user);
        if(b){
            result.setMessage("添加成功");
            result.setSuccess(true);
            return result;
        }
        result.setMessage("添加失败");
        result.setSuccess(false);
        return result;


    }
}
