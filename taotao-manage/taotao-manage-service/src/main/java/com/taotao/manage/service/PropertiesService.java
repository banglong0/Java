package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class PropertiesService {
	
	// 前台要求传回json数据，定义json处理对象,将java对象转成json返回
	//jackson提供的json转换工具类,主要用于将java对象转换成json格式,将json格式数据解析成java对象
	public static final ObjectMapper MAPPER = new ObjectMapper();

	// @Value: 获取当前容器中key所对应的value值
	@Value("${TT-MANAGE_IMAGE_PATH}") // 图片上传路径
	public String IMAGE_PATH;

	@Value("${TT_MANAGE_IMAGE_URL}") // 图片访问域名地址
	public String IMAGE_URL;
}
