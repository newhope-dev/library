package library.library.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import library.library.common.Result;
import library.library.service.UserService;
import library.library.util.JwtUtil;
import library.library.entity.User;
import library.library.util.Md5;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class LoginController {
     @Autowired
    UserService userService;
    @RequestMapping("/login")
    public Result handleLogin(String username,String password) throws UnsupportedEncodingException, JSONException {

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        User user=userService.getOne(queryWrapper);
        if(user==null){
            return null;
        }
        String syspass= Md5.getMd5(password,user.getSalt());
        if(!syspass.equals(user.getPassword())){
            return null;
        }
      //  UsernamePasswordToken token=new UsernamePasswordToken(username,user.getPassword());
       String token=JwtUtil.sign(username,user.getPassword());
        JSONObject obj = new JSONObject();
        obj.put("token",token);


        Result result=new Result();
        result.setResult(token);
        return result;

    }
    @RequestMapping("/hello")
    public Result test(){
        Result result=new Result();
        result.setSuccess(true);
        result.setMessage("拦截成功");
        return result;
    }
}
