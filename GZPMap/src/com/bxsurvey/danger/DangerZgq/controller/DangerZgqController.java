package com.bxsurvey.danger.DangerZgq.controller;

import javax.servlet.http.HttpServletRequest;

import net.framework.base.controller.BaseController;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxsurvey.danger.DangerZgq.model.DangerZgq;
import com.bxsurvey.danger.DangerZgq.service.DangerZgqServiceI;

@Controller
@RequestMapping("/danger/zgq")
public class DangerZgqController extends BaseController {
	@Autowired
	private DangerZgqServiceI dangerZgqService;
	
	/**
	 * 根据id查询
	 * @param departId
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "searchDangerZgqById")
	@ResponseBody
	public JSONObject searchDangerZgqById(String dangerZgqId,HttpServletRequest request){
		DangerZgq dz = null;
		try {
			dz = dangerZgqService.getObjectById(new Integer(dangerZgqId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean2JSONObject(dz);
	}
	
}
