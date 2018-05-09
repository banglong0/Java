package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;

@Service
public class ItemService extends BaseService<Item> {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemDescService itemDescService;
	
	
	public Boolean saveItemAndDesc(Item item, String desc) {
		
		//设置item的状态码为1,表示该对象正常
		item.setStatus(1);
		// 保证id为null,通过数据库自增
		item.setId(null);
		Boolean flag = super.save(item);
		if (flag) {
			// flag为true,执行下面代码,然后看商品描述是否成功,如果为false直接执行最下面代码,返回500
			// 新增商品描述
			ItemDesc itemDesc = new ItemDesc();
			
			// 获取item的id,但是不一定会有.所以还要回写
			itemDesc.setItemId(item.getId());
			itemDesc.setItemDesc(desc);

			flag = this.itemDescService.save(itemDesc);

		}
		return flag;
	}
}
