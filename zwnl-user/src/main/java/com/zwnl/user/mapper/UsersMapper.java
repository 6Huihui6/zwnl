package com.zwnl.user.mapper;

import com.zwnl.user.domain.po.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}
