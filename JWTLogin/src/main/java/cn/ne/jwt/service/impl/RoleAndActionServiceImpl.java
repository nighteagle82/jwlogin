package cn.ne.jwt.service.impl;

import cn.ne.jwt.dao.IActionDao;
import cn.ne.jwt.dao.IMemberAndRoleDao;
import cn.ne.jwt.service.IRoleAndActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@CacheConfig(cacheNames = "redisCache")
@Service
public class RoleAndActionServiceImpl implements IRoleAndActionService {

    @Autowired
    private IActionDao actionDao;

    @Autowired
    private IMemberAndRoleDao memberAndRoleDao;

    @Override
    public Map<String, Object> get(String mid) {
        Map<String, Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(mid)) {
            result.put("roles", this.memberAndRoleDao.findAllByMemberId(mid));
            result.put("actions", this.actionDao.findAllByMemberId(mid));
            return result;
        }
        return null;
    }
}
