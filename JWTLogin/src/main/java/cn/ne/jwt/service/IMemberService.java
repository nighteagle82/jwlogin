package cn.ne.jwt.service;


import cn.ne.jwt.model.domain.User;
import cn.ne.jwt.model.pojo.Member;

public interface IMemberService {
    Member login(User user);
}
