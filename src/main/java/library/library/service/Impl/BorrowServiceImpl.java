package library.library.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import library.library.entity.Borrow;

import library.library.mapper.BorrowMapper;
import library.library.service.BorrowService;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow> implements BorrowService {
}
