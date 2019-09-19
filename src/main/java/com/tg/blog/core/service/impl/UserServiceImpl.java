package com.tg.blog.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tg.blog.base.exception.ResponseCommonException;
import com.tg.blog.base.utils.EncryptUtil;
import com.tg.blog.base.utils.TokenUtils;
import com.tg.blog.core.model.User;
import com.tg.blog.core.mapper.UserMapper;
import com.tg.blog.core.pojo.dto.UserRegistoryDTO;
import com.tg.blog.core.pojo.vo.UserBaseVO;
import com.tg.blog.core.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


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

    @Value("${security.password.secret}")
    private String pwdSecret;

    @Override
    public Optional<Map<String,Object>> login(String email , String password) {
        UserBaseVO user = null;
        try {
            user = baseMapper.selectUserByLoginInfo(email, EncryptUtil.encrypt(password,pwdSecret));
        } catch (Exception e) {
            throw new RuntimeException("selectUserByLoginInfo ->根据登录信息查询用户失败",e);
        }
        if (user == null){return Optional.empty();}
        Optional token = TokenUtils.generateUserToken(user);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token.get());
        user.setId(null);
        map.put("user",user);
        return Optional.ofNullable(map);
    }

    @Override
    public boolean registery(UserRegistoryDTO registoryDTO) {
        /**校验用户邮箱验证码*/

        /**校验用户是否已经存在*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",registoryDTO.getUsername()).or().eq("email",registoryDTO.getEmail());
        boolean isExist = baseMapper.selectCount(queryWrapper) > 0 ? true : false;
        if (isExist){
            throw  new ResponseCommonException("亲,用户或者邮箱已经存在了沃~");
        }
        /**注册流程*/
        User user = new User();
        BeanUtils.copyProperties(registoryDTO, user);
        try {
            user.setPassword(EncryptUtil.encrypt(user.getPassword(),pwdSecret));
        }catch (Exception e){
            throw  new RuntimeException("用户注册失败",e);
        }
        boolean isRegister =  baseMapper.insert(user) > 0 ? true :  false;
        return isRegister;
    }
}
