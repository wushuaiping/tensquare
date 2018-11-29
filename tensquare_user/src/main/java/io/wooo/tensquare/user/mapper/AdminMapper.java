package io.wooo.tensquare.user.mapper;

import io.wooo.tensquare.user.entity.Admin;
import io.wooo.tensquare.user.model.AdminRegisterModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 5:09 PM
 * @description:
 */
@Mapper
public interface AdminMapper {

    AdminMapper MAPPER = Mappers.getMapper(AdminMapper.class);

    Admin registerModelToEntity(AdminRegisterModel registerModel);
}
