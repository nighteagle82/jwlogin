package cn.ne.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class News implements Serializable {

    private Long nid;
    private String title;
    private String content;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
