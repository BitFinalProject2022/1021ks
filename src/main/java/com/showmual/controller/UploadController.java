package com.showmual.controller;

import java.io.File;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.showmual.domain.repository.ImageClothRepository;
import com.showmual.dto.Files;
import com.showmual.service.FilesService;
import com.showmual.service.ImageClothService;
import com.showmual.service.UserService;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class UploadController {
	
	@Autowired
	FilesService filesService;
	
	private final UserService userService;
	private final ImageClothService imageClothService;
	
	// 사진 등록 페이지
	@RequestMapping("/user/upload")
	public String Insert() {
		
		return "upload";
	}
	
	// 이미지 저장 + 경로 DB에 넣기
//	@RequestMapping(value = "/user/fileUpload", method = RequestMethod.POST)
//	public String fileinsert(HttpServletRequest request, @RequestPart MultipartFile files, Principal principal) throws Exception{
//		Files file = new Files();
//		
//		// 로그인한 사용자의 아이디를 가져온다.
//		String email = principal.getName();
//		String id = userService.findIdByEmail(email);
//		
//		if(!files.isEmpty()) { // 업로드할 파일이 존재할 경우에만
//			String sourceFileName = files.getOriginalFilename(); 
//			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
//			File destinationFile; 
//			String destinationFileName;
//			String fileUrl = "E:/Project/images/" + id + "/"; // 아이디 번호로 폴더 생성
//			
//			do { 
//				destinationFileName = 
//						RandomStringUtils.randomAlphanumeric(16) + "_" + id + "." + sourceFileNameExtension; 
//				destinationFile = new File(fileUrl + destinationFileName); 
//			} while (destinationFile.exists());
//			
//			destinationFile.getParentFile().mkdirs(); 
//			files.transferTo(destinationFile);
//	
//			file.setFilename(destinationFileName);
//			file.setFileOriName(sourceFileName);
//			file.setFileurl(fileUrl);
//			filesService.save(file);
//		}
//		
//		return "redirect:/user/upload";
//	}
	
	// 이미지 경로 보내기
	@RequestMapping(value = "/user/fileUpload", method = RequestMethod.POST)
	public String imageUrl(HttpServletRequest request, @RequestPart MultipartFile files, Principal principal, Model model) throws Exception {
		
		Files file = new Files();
		
		// 로그인한 사용자의 아이디를 가져온다.
		String email = principal.getName();
		String id = userService.findIdByEmail(email);
		String imgUrl = "";
		
		if(!files.isEmpty()) { // 업로드할 파일이 존재할 경우에만
			String sourceFileName = files.getOriginalFilename(); 
			String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
			File destinationFile; 
			String destinationFileName;
			// 경로
			String fileUrl = "E:/Project/images/" + id + "/"; // 아이디 번호로 폴더 생성
			
			do {
				// 이미지 이름
				destinationFileName = 
						RandomStringUtils.randomAlphanumeric(16) + "_" + id + "." + sourceFileNameExtension; 
				destinationFile = new File(fileUrl + destinationFileName); 
			} while (destinationFile.exists());
			
			destinationFile.getParentFile().mkdirs(); 
			files.transferTo(destinationFile);
			
			file.setFilename(destinationFileName);
			file.setFileOriName(sourceFileName);
			file.setFileurl(fileUrl);
			filesService.save(file);
			
			imgUrl = fileUrl + destinationFileName;
			
			// Django로 데이터 보내기
			ImageClothRepository response = imageClothService.getFirstTodoTest(imgUrl);
	        System.out.println("=================================");
	        System.out.println(response.getKind());
	        System.out.println(response.getQty());
	        
	        model.addAttribute("kind", response.getKind());
	        model.addAttribute("qty", response.getQty());
			
		}
		
		return "redirect:/user/upload";
	}
}
