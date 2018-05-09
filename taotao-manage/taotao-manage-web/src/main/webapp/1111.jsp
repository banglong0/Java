<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tr>
		<td class="names">室内照片：</td>
		<td class="dowebok dad-active dad-container" trans="1"
			onselectstart="return false"><div class="upd">
				<input type="file" name="upic1" class="upic" id="upic1">&nbsp;&nbsp;<input
					type="button" class="webtn6 webtn_6 webtn_7" onclick="getPhotos(3)"
					value="我的图库">&nbsp;
			</div>
			<div class="upictxt msg">
				<img align="absmiddle" src="./images/untitled3.bmp"
					class="dad-draggable-area">最多10张。客厅/卧室/厨房等3张以上照片可帮助您获得较好效果！您可以<a
					onclick="getPhotos(3)">从我的图库选择</a>
				<div class="btn4pic">
					<div class="readme">
						<div class="b">说明：</div>
						<div>
							<h1>&nbsp;&nbsp;</h1>
							图片左移，
						</div>
						<div>
							<h2>&nbsp;&nbsp;</h2>
							图片右移，
						</div>
						<div>
							<h3>&nbsp;&nbsp;</h3>
							设为封面，
						</div>
						<div>
							<h4>&nbsp;&nbsp;</h4>
							删除
						</div>
						<div
							style="width: 600px; font-size: 0px; line-height: 0px; float: left; height: 0px;"></div>
					</div>
				</div>
			</div>
			<div class="pic dads-children" num="1" trans="1" data-dad-id="1">
				<h5>第1张图片</h5>
				<img
					src="https://img4.tuituifang.com/11/sh/pic/20180508/08/15257533881511148397.jpg?1525753378"
					class="dad-draggable-area"><span class="btn4pic"><h1
						title="向左移动" onclick="move2($(this),1,-1)"></h1>
					<h2 title="向右移动" onclick="move2($(this),1,1)"></h2>
					<h3 title="设为封面" onclick="upface($(this))"></h3>
					<h4 title="删除"
						onclick="upfile0(1,'11/sh/pic/20180508/08/15257533881511148397.jpg?1525753378',$(this))"></h4></span><input
					type="text" name="txt1[]" class="txt" value="填写图片说明" maxlength="12"
					onclick="post.img.titles(this,event)"><span class="trans"
					style="cursor: pointer;">转移</span>
				<ul class="trans" style="display: none;">
					<li>房型图</li>
					<li style="display: none;">室内图</li>
					<li>小区图</li>
				</ul>
			</div>
			<div class="pic dads-children" num="2" trans="1" data-dad-id="2">
				<h5>第2张图片</h5>
				<img
					src="https://img4.tuituifang.com/11/sh/pic/20180508/14/15257533941511148471.jpg?1525753384"
					class="dad-draggable-area"><span class="btn4pic"><h1
						title="向左移动" onclick="move2($(this),1,-1)"></h1>
					<h2 title="向右移动" onclick="move2($(this),1,1)"></h2>
					<h3 title="设为封面" onclick="upface($(this))"></h3>
					<h4 title="删除"
						onclick="upfile0(1,'11/sh/pic/20180508/14/15257533941511148471.jpg?1525753384',$(this))"></h4></span><input
					type="text" name="txt1[]" class="txt" value="填写图片说明" maxlength="12"
					onclick="post.img.titles(this,event)"><span class="trans"
					style="cursor: pointer;">转移</span>
				<ul class="trans" style="display: none;">
					<li style="color: black;">房型图</li>
					<li style="display: none; color: black;">室内图</li>
					<li style="color: rgb(243, 127, 6);">小区图</li>
				</ul>
			</div>
			<div class="pic dads-children addStart" data-dad-id="3" num="3"></div></td>
	</tr>
</body>
</html>