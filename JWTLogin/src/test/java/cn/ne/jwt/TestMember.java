package cn.ne.jwt;

import cn.ne.jwt.dao.IMemberDao;
import cn.ne.jwt.model.domain.User;
import cn.ne.jwt.model.pojo.Member;
import cn.ne.jwt.service.IMemberService;
import cn.ne.jwt.service.IRoleAndActionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JWTLoginApp.class)
@WebAppConfiguration
public class TestMember {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRoleAndActionService roleAndActionService;

    @Test
    public void testUser() {
        User user = new User().setMid("admin").setPassword("123456");
        Member login = this.memberService.login(user);
        System.out.println(login);

        Map<String, Object> result = this.roleAndActionService.get(user.getMid());
        result.forEach((k,v)->{
            System.out.printf("k >>> %s, v >>> %s%n", k, v);
        });
    }

}
