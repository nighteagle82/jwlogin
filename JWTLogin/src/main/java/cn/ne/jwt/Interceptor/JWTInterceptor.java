package cn.ne.jwt.Interceptor;

import cn.ne.jwt.util.jwt.JWTParams;
import cn.ne.jwt.util.jwt.JWTUtil;
import cn.ne.jwt.util.resutils.ErrorStatus;
import cn.ne.jwt.util.resutils.ResponseUtil;
import cn.ne.jwt.util.resutils.SystemConstants;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private JWTParams params;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> msgMap = new HashMap<>();
        String message = "";
        String token = request.getHeader("token");
        try {
            return JWTUtil.verify(token, params.getSignKey());
//            return true;
        }catch (SignatureVerificationException e) {    // 验签异常
            message = "无效的签名。";
        } catch (TokenExpiredException e) {   // Token过期
            message = "Token过期。";
        } catch (AlgorithmMismatchException e) { // 算法不一致
           message = "签名算法不一致。";
        } catch (JWTVerificationException e) {  // JWT验证异常【抛出该异常即可】
            message = "Token无效。";
        } catch (Exception e) {  // 其他异常
            message ="没有找到Token，拒绝访问。";
        }

        msgMap = ResponseUtil.setResponseMap(SystemConstants.CODE_1, SystemConstants.FAILURE, new ErrorStatus(
                SystemConstants.E001, SystemConstants.getMessage().get(SystemConstants.E001) + message
        ), null);

        String json = new ObjectMapper().writeValueAsString(msgMap);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
