package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iducs.springboot.board.domain.User;

public interface UserRepository 
	extends JpaRepository<User, Long> {
	 public List<User> findByName(String name);
	 public List<User> findByCompany(String company);
}
