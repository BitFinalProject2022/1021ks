package com.showmual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.showmual.domain.repository.ImageClothRepository;
import com.showmual.service.ImageClothService;

public class ImageClothController {
	
	final ImageClothService imageClothService;
	
    @Autowired
    public ImageClothController(ImageClothService imageClothService) {
        this.imageClothService = imageClothService;
    }
    
    @GetMapping("/cloth/type")
    public String ClothWebClient(Model model) {
    	String imgUrl = "abc";
    	
        ImageClothRepository response = imageClothService.getFirstTodoTest(imgUrl);
        System.out.println("=================================");
        System.out.println(response.getKind());
        System.out.println(response.getQty());
        
        model.addAttribute("kind", response.getKind());
        model.addAttribute("qty", response.getQty());
        return "hello";
    }
    
//  Mono<ClothRepository> cloth;
//
//  @RequestMapping(value = "/cloth/type", method = RequestMethod.GET)
//  public String getCloth() {
//      System.out.println("################");
//      cloth = clothService.findById();
//      System.out.println(cloth);
//
//      return "hello";
//  }
}
