package com.taotao.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用页面处理类
 * @author libanglong
 *
 */
@RequestMapping("page")
@Controller
public class PageController {

	/**
	 * 通用页面跳转
	 */
	@RequestMapping("{viewName}")
	public String page(@PathVariable("viewName") String viewName){
		return viewName;
	}
	
}
