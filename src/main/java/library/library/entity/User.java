package library.library.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
@Table("user")
@Data
public class User implements Serializable {

    private String id;
    private String userName;
    private String realName;
    private String sex;
    private  Integer age;
    private String password;
    private String salt;
    private String status;
    private String delflag;
    private Date createTime;
    private Date updateTime;

}
