package library.library.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table("book")
@Data
public class Book implements Serializable {
    private Integer id;
    private String bookNo;
    private String bookName	;
    private String author;
    private String  type	;
    private BigDecimal price	;
    private String publish;
    private String  remark;
    private Integer collection;
    private Integer have;
    private String position;
    private Integer lendtotal;
    private Date createtime	;
    private Date updatetime;


}
