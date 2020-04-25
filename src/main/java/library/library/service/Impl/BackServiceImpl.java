package library.library.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import library.library.entity.Back;
import library.library.mapper.BackMapper;
import library.library.service.BackService;
import org.springframework.stereotype.Service;

@Service
public class BackServiceImpl extends ServiceImpl<BackMapper, Back> implements BackService {
}
