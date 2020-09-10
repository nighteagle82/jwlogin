package cn.ne.jwt.controller;

import cn.ne.jwt.model.domain.User;
import cn.ne.jwt.model.pojo.Member;
import cn.ne.jwt.service.IRoleAndActionService;
import cn.ne.jwt.util.jwt.JWTParams;
import cn.ne.jwt.util.jwt.JWTUtil;
import cn.ne.jwt.util.resutils.ErrorStatus;
import cn.ne.jwt.util.resutils.ResponseUtil;
import cn.ne.jwt.util.resutils.SystemConstants;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/content")
public class MemberAction {

    @Autowired
    private JWTParams params;

    @Autowired
    private IRoleAndActionService roleAndActionService;

    @RequestMapping(value = "/member", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String member(HttpServletRequest request) {
        Map<String, Object> msgMap = new HashMap<>();

        // 如果要使用户帐号，可通过Token中包含的数据获取
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            // 解码jwt token 获取Member对象
            DecodedJWT decodedJWT = JWTUtil.getDecodedJWT(token, params.getSignKey());
            Member member = JSONObject.parseObject(decodedJWT.getClaim("member").asString(), Member.class);
            if (member != null) {
                Map<String, Object> memberResult = this.roleAndActionService.get(member.getMid());
                memberResult.put("member", member); // 将member一起返回
                msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_0, SystemConstants.SUCCESS, new ErrorStatus(
                        SystemConstants.E005, SystemConstants.getMessage().get(SystemConstants.E000)
                ), memberResult);
            }
        }
        return JSONObject.toJSONString(msgMap);
    }
}
