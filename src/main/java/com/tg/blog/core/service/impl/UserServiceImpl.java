package com.tg.blog.core.service.impl;

import com.tg.blog.core.model.User;
import com.tg.blog.core.mapper.UserMapper;
import com.tg.blog.core.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
