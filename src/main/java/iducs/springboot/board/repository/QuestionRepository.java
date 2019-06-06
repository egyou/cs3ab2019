package iducs.springboot.board.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import iducs.springboot.board.entity.QuestionEntity;
import iducs.springboot.board.entity.UserEntity;


public interface QuestionRepository 
	extends JpaRepository<QuestionEntity, Long> {	
	//List<QuestionEntity> findAllOrderByCreateTime();
	
	List<QuestionEntity> findAll(Sort sort); 
	Page<QuestionEntity> findAll(Pageable pageable); 
}
