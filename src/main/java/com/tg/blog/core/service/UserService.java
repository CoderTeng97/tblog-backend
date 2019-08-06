package com.tg.blog.core.service;

import com.tg.blog.core.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.blog.core.pojo.dto.UserRegistoryDTO;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author Teng
 * @since 2019-07-16
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    public Optional<Map<String,Object>> login(String email , String password) throws Exception;

    /**
     * 用户注册
     * @param registoryDTO
     * @return
     */
    public boolean registery(UserRegistoryDTO registoryDTO);
}
