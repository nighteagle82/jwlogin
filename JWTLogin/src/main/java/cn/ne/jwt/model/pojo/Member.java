package cn.ne.jwt.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)    // 启用链式调用
public class Member implements Serializable {

    private Long id;
    private String mid;
    private String name;
    private String password;
    private Integer locked;

}
