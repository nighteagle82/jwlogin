package cn.ne.jwt.controller;

import cn.ne.jwt.model.domain.User;
import cn.ne.jwt.model.pojo.Member;
import cn.ne.jwt.service.IMemberService;
import cn.ne.jwt.util.jwt.JWTParams;
import cn.ne.jwt.util.jwt.JWTUtil;
import cn.ne.jwt.util.resutils.ErrorStatus;
import cn.ne.jwt.util.resutils.ResponseUtil;
import cn.ne.jwt.util.resutils.SystemConstants;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class LoginAction {

    @Autowired
    private JWTParams params;   // 初始化jwt所需的参数

    @Autowired
    private IMemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String login(@RequestBody User user) {
        Map<String, Object> msgMap = new HashMap<>();

        Member member = this.memberService.login(user);
        if (member !=null) {
            if (member.getLocked() == 1) {      // 1:锁定,0:开放
                msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                   SystemConstants.E005, SystemConstants.getMessage().get(SystemConstants.E005)
                ),null);
            }
            // jwt
            try {
                params.setPayloadName("member");
                params.setPayloadJSON(JSON.toJSONString(member));
                params.setAlgorithm(Algorithm.HMAC256(params.getSignKey()));
            } catch (IllegalArgumentException e) {
                String name = e.getClass().getSimpleName();
                System.out.println(name);
            }
            String token = JWTUtil.createToken(params);
            // 下发token
            Map<String, String> accessToken = new HashMap<>();
            accessToken.put("token", token);
            msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_0, SystemConstants.SUCCESS, new ErrorStatus(
                    SystemConstants.E000, SystemConstants.getMessage().get(SystemConstants.E000)
            ),accessToken);
        } else {    // 用户不存在
            msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                    SystemConstants.E005, SystemConstants.getMessage().get(SystemConstants.E003)
            ),null);
        }

        return JSON.toJSONString(msgMap);
    }

}
