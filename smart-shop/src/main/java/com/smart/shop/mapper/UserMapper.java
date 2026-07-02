package com.smart.shop.mapper;

import com.smart.shop.entity.Role;
import com.smart.shop.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE username = #{username} AND active = 1")
    User findByUsername(String username);

    @Select("SELECT r.* FROM t_role r INNER JOIN t_user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{userId}")
    List<Role> findRolesByUserId(Integer userId);
}
