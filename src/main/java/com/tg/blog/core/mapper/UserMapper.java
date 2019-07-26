package com.tg.blog.core.mapper;

import com.tg.blog.core.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.blog.core.pojo.dto.LoginInfoDTO;
import com.tg.blog.core.pojo.vo.UserBaseVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Teng
 * @since 2019-07-16
 */
public interface UserMapper extends BaseMapper<User> {
   @Select("select id,username,email,user_type from tb_user where email = #{email} and password = #{password}")
   UserBaseVO  selectUserByLoginInfo(@Param("email")String email,@Param("password") String password);
}
