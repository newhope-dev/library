package library.library.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Table("back")
@Data
public class Back implements Serializable {

    private Integer id;
    private String bookNo;
    private String cardId;
    private Date borrowTime;
    private Date backTime;
}
