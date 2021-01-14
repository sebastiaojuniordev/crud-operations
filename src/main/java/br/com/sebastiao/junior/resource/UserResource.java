package br.com.sebastiao.junior.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sebastiao.junior.common.dto.UserDTO;
import br.com.sebastiao.junior.common.mapper.UserMapper;
import br.com.sebastiao.junior.infrastructure.resource.CRUDResourceImpl;
import br.com.sebastiao.junior.jpa.entity.User;
import br.com.sebastiao.junior.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserResource extends CRUDResourceImpl<UserDTO, User, UserService, UserMapper, Integer> {

	@Autowired
	public UserResource(UserService service, UserMapper mapper) {
		super(service, mapper);
	}
}
