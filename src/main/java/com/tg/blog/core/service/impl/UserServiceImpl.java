package com.tg.blog.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tg.blog.core.model.User;
import com.tg.blog.core.mapper.UserMapper;
import com.tg.blog.core.pojo.vo.UserBaseVO;
import com.tg.blog.core.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Teng
 * @since 2019-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Optional<UserBaseVO> login(String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("email",email).eq("password",password);
        User user = baseMapper.selectOne(queryWrapper);
        if(user ==null){
            return  null;
        }
        UserBaseVO userBaseVO = new UserBaseVO();
        userBaseVO.setEmail(user.getEmail());
        userBaseVO.setUsername(user.getUsername());
        userBaseVO.setId(user.getId());
        return Optional.of(userBaseVO);
    }
}
