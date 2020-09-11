package com.xtkj.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_userinfo")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -5002602837713748029L;

    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    @TableField("userId")
    private String userId;
    @TableField("userPwd")
    private String userPwd;
    @Version
    private Integer version;
}
