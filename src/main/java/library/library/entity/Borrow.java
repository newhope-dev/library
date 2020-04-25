package library.library.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Table("borrow")
@Data
public class Borrow implements Serializable {
    private Integer id;
    private String bookNo;
    private String cardId;
    private Date borrowTime;
    private Date bereturnTime;
}
