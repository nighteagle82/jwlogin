package cn.ne.jwt.service.impl;

import cn.ne.jwt.dao.IMemberDao;
import cn.ne.jwt.model.domain.User;
import cn.ne.jwt.model.pojo.Member;
import cn.ne.jwt.service.IMemberService;
import cn.ne.jwt.util.encrypt.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private IMemberDao memberDao;

    @Override
    public Member login(User user) {
        Member member = this.memberDao.findByMid(user.getMid());
        if (member != null) {
            if (EncryptUtil.encode(user.getPassword()).equals(member.getPassword())) {
                member.setPassword("");
                return member;
            }
        }
        return null;
    }
}
