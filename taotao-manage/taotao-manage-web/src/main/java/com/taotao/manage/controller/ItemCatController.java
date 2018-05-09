package com.taotao.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;

@RequestMapping("item/cat")
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	/**
	 * 需求:商品新增页面点击"选择类目",触发商品类目加载
	 * 1.该请求是同步还是异步?	异步
	 * 2.参数是什么?	parentId,必须有,而且默认为0
	 * 3.结果是什么?	List<ItemCat>
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatByParentId(
			@RequestParam(value="id",defaultValue="0") Long parentId){
		
		
		
		try {
			ItemCat record = new ItemCat();
			record.setParentId(parentId);
			record.setStatus(1);
			
			List<ItemCat> list = this.itemCatService.queryListByrecord(record );
			
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
}
