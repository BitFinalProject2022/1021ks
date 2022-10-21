package com.showmual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showmual.domain.repository.FilesRepository;
import com.showmual.dto.Files;


@Service
public class FilesService {
	
	@Autowired
	FilesRepository filesRepository;
	
	public void save(Files files) {
		Files f = new Files();
		f.setFilename(files.getFilename());
		f.setFileOriName(files.getFileOriName());
		f.setFileurl(files.getFileurl());
		
		filesRepository.save(f);
	}
}
