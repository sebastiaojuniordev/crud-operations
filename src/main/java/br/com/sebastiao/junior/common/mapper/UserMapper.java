package br.com.sebastiao.junior.common.mapper;

import br.com.sebastiao.junior.common.dto.UserDTO;
import br.com.sebastiao.junior.infrastructure.mapper.Mapper;
import br.com.sebastiao.junior.jpa.entity.User;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper extends Mapper<User, UserDTO> {

}
