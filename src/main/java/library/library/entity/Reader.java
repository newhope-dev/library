package library.library.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

@Table("reader")
@Data
public class Reader implements Serializable {
    private Integer id;
    private String cardId;
    private String name;
    private String sex;
    private String work;
    private String type;
    private Integer lendtotal;
    private String status;
    private String delflag;
    private Date createtime;
    private Date updatetime;

}
