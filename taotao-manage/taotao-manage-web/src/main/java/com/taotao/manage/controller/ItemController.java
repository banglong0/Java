package com.taotao.manage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUiResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;

@RequestMapping("item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 需求:新增商品 1.该请求是同步还是异步? 异步 Ajax 2.参数是什么? Item对象. String desc,item_desc对象
	 * 3.返回结果是什么? statusCode状态码
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> addItemAndDesc(Item item, @RequestParam("desc") String desc) {

		// 同一个请求,必须保证操作的原子性(要么全部成功,要么全部失败),一个请求两次事务会导致数据不一致
		try {
			if (StringUtils.isEmpty(item.getTitle())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			if (this.itemService.saveItemAndDesc(item, desc)) {
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 分页查询商品列表
	 * 	请求是异步
	 * 	请求参数:pageNum pageSize
	 * 	返回结果:total总记录数; rows 数据结果集
	 * 	两种方式:Map和EasyUiResult通用类
	 */

//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<Map<String,Object>> queryItemPageList(
//			@RequestParam(value="page",defaultValue="1") Integer pageNum,
//			@RequestParam(value="rows",defaultValue="10") Integer pageSize){
//		
//		try {
//			Map<String,Object> map = new HashMap<String,Object>();
//			
//			//使用分页助手pageHelper来进行分页
//			PageInfo<Item> pageInfo = this.itemService.queryPageListByWhere(pageNum, pageSize);
//			
//			map.put("total", pageInfo.getTotal());
//			map.put("rows", pageInfo.getList());
//			
//			return ResponseEntity.status(HttpStatus.OK).body(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<EasyUiResult> queryItemPageList(
			@RequestParam(value="page",defaultValue="1") Integer pageNum,
			@RequestParam(value="rows",defaultValue="10") Integer pageSize){
		
		try {
			//使用分页助手pageHelper来进行分页
			PageInfo<Item> pageInfo = this.itemService.queryPageListByWhere(pageNum, pageSize);
			
			EasyUiResult result = new EasyUiResult();
			result.setTotal(pageInfo.getTotal());
			result.setRows(pageInfo.getList());
			
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * /item/desc/'+data.id
	 * 根据商品id加载商品描述
	 * 请求:异步
	 * 参数:商品id 占位符传参
	 * 结果:商品描述对象 _data
	 */
}
