package io.wooo.tensquare.user.mapper;

import io.wooo.tensquare.user.entity.User;
import io.wooo.tensquare.user.model.UserRegisterModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 5:09 PM
 * @description:
 */
@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User registerModelToEntity(UserRegisterModel registerModel);
}
