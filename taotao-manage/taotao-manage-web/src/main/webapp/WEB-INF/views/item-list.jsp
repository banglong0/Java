<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/rest/item',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">商品ID</th>
            <th data-options="field:'title',width:200">商品标题</th>
            <th data-options="field:'cid',width:50">叶子类目</th>
            <th data-options="field:'sellPoint',width:100">卖点</th>
            <th data-options="field:'price',width:70,align:'right',formatter:TAOTAO.formatPrice">价格</th>
            <th data-options="field:'num',width:70,align:'right'">库存数量</th>
            <th data-options="field:'barcode',width:50">条形码</th>
            <th data-options="field:'status',width:60,align:'center',formatter:TAOTAO.formatItemStatus">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	//获取整个table对象
    	var itemList = $("#itemList");
    	//获取table中选中的行  ==>集合
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];//声明一个数组
    	for(var i in sels){
    		ids.push(sels[i].id);//将遍历的对象的id(商品ID)存入数组中.
    	}
    	ids = ids.join(",");//将数组中的元素用,进行分割.然后转成字符串
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增商品')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	//table对象中选中的行的商品Id被转成字符串,并返回过来
        	var ids = getSelectionsIds();
        	//对字符串进行判断.
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个商品才能编辑!');
        		return ;
        	}
        	if(ids.Of(',') > 0){
        		$.messager.alert('提示','只能选择一个商品!');
        		return ;
        	}
        	//title="编辑商品"的id
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			//回显数据,在当前table表中选中的行数中的第一个.之所以写[0]是因为前面已经校验过数据有且只有一个
        			//data表示被选中的那一行数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			//将被选中的那行数据中的价格,由分转成元.priceView是刚创建的.这就是js弱类型语言的自由
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#itemeEditForm").form("load",data);
        			
        			// 加载商品描述
        			$.getJSON('/rest/item/desc/'+data.id,function(_data){
        				itemEditEditor.html(_data.itemDesc);
        			});
        			
        			//加载商品规格
        			$.ajax({
			    		url : "/rest/item/param/item/" + data.id,
			    		type : "GET",
			    		dataType : "json",
			    		statusCode : {
			    			200 : function(_data){
			    				$("#itemeEditForm .params").show();
	        					$("#itemeEditForm [name=itemParams]").val(_data.paramData);
	        					$("#itemeEditForm [name=itemParamId]").val(_data.id);
	        					
	        					//回显商品规格
	        					 var paramData = JSON.parse(_data.paramData);
	        					
	        					 var html = "<ul>";
	        					 for(var i in paramData){
	        						 var pd = paramData[i];
	        						 html+="<li><table>";
	        						 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
	        						 
	        						 for(var j in pd.params){
	        							 var ps = pd.params[j];
	        							 html+="<tr><td class=\"param\"><span>"+ps.k+"</span>: </td><td><span class='textbox'><input autocomplete=\"off\" type=\"text\" value='"+ps.v+"'/></span></td></tr>";
	        						 }
	        						 
	        						 html+="</li></table>";
	        					 }
	        					 html+= "</ul>";
	        					 $("#itemeEditForm .params td").eq(1).html(html);
			    			},
			    			404 : function(){
			    				
			    			},
			    			500 : function(){
			    				$.messager.alert('提示','查询规格参数数据失败!');
			    			}
			    		}
			    	});
        			
        			TAOTAO.init({
        				"pics" : data.image,
        				"cid" : data.cid,
        				fun:function(node){
        					TAOTAO.changeItemParam(node, "itemeEditForm");
        				}
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'下架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定下架ID为 '+ids+' 的商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/instock",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','下架商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'上架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定上架ID为 '+ids+' 的商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/reshelf",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','上架商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>