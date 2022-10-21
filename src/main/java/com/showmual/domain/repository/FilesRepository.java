package com.showmual.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.showmual.dto.Files;


public interface FilesRepository extends JpaRepository<Files, Integer> {
	
	Files findByFno(int fno);
}
