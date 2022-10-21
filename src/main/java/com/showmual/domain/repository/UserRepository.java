package com.showmual.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.showmual.domain.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String userEmail);
	
	boolean existsByEmail(String email);
	
	@Query(value = "select id from users_tbl where email = :email", nativeQuery = true)
	String findIdByEmail(String email); //이렇게도 사용 가능하다.
}
