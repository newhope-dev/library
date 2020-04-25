package library.library.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import library.library.entity.Reader;
import library.library.mapper.ReaderMapper;
import library.library.service.ReaderService;
import org.springframework.stereotype.Service;

@Service
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements ReaderService {
}
