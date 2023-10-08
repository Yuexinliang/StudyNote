package com.jr.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * className UserServiceImpl
 * packageName com.jr.service.Impl
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/07 08:35
 */
@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * @description: 用户登录
     * @param users
     * @return: com.jr.entity.Users
     * @author CYQH
     * @date: 2023/10/07 9:59
     */
    @Override
    public Users getUserByAccountPwd(Users users) {
        return usersMapper.selectUserByAccountPwd(users);
    }
}
