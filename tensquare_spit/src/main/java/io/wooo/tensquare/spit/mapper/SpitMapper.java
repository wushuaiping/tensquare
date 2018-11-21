package io.wooo.tensquare.spit.mapper;

import io.wooo.tensquare.spit.document.Spit;
import io.wooo.tensquare.spit.model.SpitModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 2:55 PM
 * @description:
 */
@Mapper
public interface SpitMapper {

    SpitMapper MAPPER = Mappers.getMapper(SpitMapper.class);

    Spit toEntity(SpitModel model);
}
