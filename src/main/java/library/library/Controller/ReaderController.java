package library.library.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import library.library.common.Result;
import library.library.entity.Reader;
import library.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    public ReaderService readerService;

    @RequestMapping("/list")
    public Result<?> querylist(){
        QueryWrapper<Reader> queryWrapper=new QueryWrapper<>();
        List<Reader> list=readerService.list(queryWrapper);
        return  Result.ok(list);
    }
}
