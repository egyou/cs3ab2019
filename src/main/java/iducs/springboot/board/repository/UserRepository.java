package iducs.springboot.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iducs.springboot.board.domain.User;

public interface UserRepository 
	extends JpaRepository<User, Long> {

}
