package br.com.sebastiao.junior.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sebastiao.junior.jpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
