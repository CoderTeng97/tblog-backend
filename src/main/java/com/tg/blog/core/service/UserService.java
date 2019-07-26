package com.tg.blog.core.service;

import com.tg.blog.core.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Teng
 * @since 2019-07-16
 */
public interface UserService extends IService<User> {
    public Optional<Map<String,Object>> login(String email , String password);
}
