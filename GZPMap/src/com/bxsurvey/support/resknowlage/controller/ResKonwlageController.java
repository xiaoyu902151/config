package com.bxsurvey.support.resknowlage.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bxsurvey.comd.ComdExpert.controller.ComdExpertController;

@Controller
@RequestMapping("/support/resKonwlage")
public class ResKonwlageController {
	private static Logger log = Logger.getLogger(ResKonwlageController.class);
	@RequestMapping(params = "updateView")
	@ResponseBody
	public ModelAndView updateView(String userId){
		ModelAndView md = new ModelAndView();

		md.setViewName("support/reskonwlage/updateView");
		return md;
	}
	
	@RequestMapping(params = "addView")
	@ResponseBody
	public ModelAndView addView(){
		ModelAndView md = new ModelAndView();
		md.setViewName("support/reskonwlage/addView");
		return md;
	}
}
