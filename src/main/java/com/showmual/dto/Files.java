package com.showmual.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Files {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_Increment인 컬럼
	int fno;
	
	String filename;
	String fileOriName;
	String fileurl;
	
}
