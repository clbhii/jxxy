<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>DataGrid Complex Toolbar - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script>
		var baseUrl = "../";
		var fieldIndex = 0;
		$(function () {
			$('#tb1').datagrid({
				// url: 'datagrid_data1.json',
				url: baseUrl + 'device/pageDevice',
				width: 1100,
				title: '设备管理',
				method: 'get',
				toolbar: '#tb',
				singleSelect: 'true',
				columns: [[
					{field: 'id', title: 'id', width: 150},
					{field: 'devNo', title: '设备编号', width: 150},
					{field: 'devName', title: '设备名称', width: 150},
					{field:'operate',title:'操作',align:'center',width:$(this).width()*0.1,
						formatter:function(value, row, index){
							var str = '<a href="#" name="update" class="easyui-linkbutton" onclick="obj.update('+index+')"></a>' +
									  '<a href="#" name="delete" class="easyui-linkbutton" onclick="obj.delete('+index+')"></a>';
							return str;
						}
					}
				]],
				pagination: true,
				pageSize: 10,
				pageList: [10, 15, 20, 25],
				onLoadSuccess:function(data){
					$("a[name='update']").linkbutton({text:'修改',plain:true,iconCls:'icon-add'});
					$("a[name='delete']").linkbutton({text:'删除',plain:true,iconCls:'icon-add'});
				}
			});
			obj = {
				search: function () {
					$('#tb1').datagrid('load', {
						devNo: $('#searchDevNo').val()
					});
				},
				add: function() {
					$('#fm').form('clear');
					$('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加设备');
				},
				update: function(index) {
					if(index != null){
						$('#tb1').datagrid('selectRow', index);
					}
					var row = $('#tb1').datagrid('getSelected');
					if (row) {
						$('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改设备');
						$('#fm').form('load',{
							id:row.id,
							devType:row.devType,
							devNo:row.devNo,
							devName:row.devName
						});
					}

				},
				delete: function(index) {
					if(index != null){
						$('#tb1').datagrid('selectRow', index);
					}
					var row = $('#tb1').datagrid('getSelected');
					if (row) {
						$.messager.confirm('Confirm', '确定删除设备吗?', function (r) {
							if (r) {
								$.post(baseUrl+'device/delete', {id: row.id}, function (result) {
									if (result != null && result.success) {
										$('#tb1').datagrid('reload');
									} else {
										$.messager.alert("提示", "删除失败", "info");
									}
								})
							}
						});
					}
				},
				close:function(){
					$('#dlg').dialog('close');
				},
				save:function () {
					var id = document.getElementById("id").value;
					var devNo = document.getElementById("devNo").value;
					var devName = document.getElementById("devName").value;
					var url = "";
					if(id) {
						url = baseUrl+"device/update";
					}else {
						url = baseUrl+"device/insert";
					}

					$('#fm').form('submit', {
						url: url ,
						onSubmit: function () {
							return $(this).form('validate');
						},
						success: function (result) {
							var res = $.parseJSON( result );
							if (res != null && res.success) {
								if (id) {
									obj.close();
									$.messager.alert("提示", "更新成功", "info");

									$('#tb1').datagrid('reload');
								}else{
									obj.close();
									$.messager.alert("提示", "添加成功", "info");
									$('#tb1').datagrid('reload');
								}
							} else {
								$.messager.alert("提示", "失败", "info");
							}
							$('#fm').form('clear');
						}
					});
				},
				addField: function () {
					$('#fm').append("<div class=\"fitem\">" +
								"<label>属性名:</label>" +
								"<input name=\"deviceFieldDOList[" +fieldIndex+"].fieldKey\" id=\"fieldKey"+fieldIndex+"\"  style='width: 100px'/>" +
								"<label>属性值:</label>" +
								"<input name=\"deviceFieldDOList[" +fieldIndex+"].fieldValue\" id=\"fieldValue"+fieldIndex+"\" style='width: 100px'/>" +
								"<button type=\"button\">Click Me!</button>" +
							"</div>");
					fieldIndex ++;

				}
			};

			var data = [
				{'text' : '智能电灯', 'value' : '1'},
				{'text' : '智能空调', 'value' : '2'}
			];

			$('#devType').combobox({
				textField: 'text',
				valueField: 'value',
				panelHeight: 'auto',
				data: data
			});
		});

	</script>
</head>
<body>
	<div style="margin:20px 0;"></div>
	<table id="tb1">

	</table>
	<div id="tb" style="padding:2px 5px;">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="obj.add()">添加</a>
		</div>
		设备编号: <input id="searchDevNo" class="easyui-textbox"/>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search()">Search</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width:400px;height:320px;padding:10px 20px"
		 closed="true" buttons="#dlg-buttons">

		<form id="fm" method="post" novalidate>
			<div class="fitem" hidden="true">
				<label>id:</label>
				<input name="id" id="id" class="easyui-textbox"/>
			</div>
			<div class="fitem">
				<label>设备类型:</label>
				<input name="devType" id="devType" required="true"/>
			</div>
			<div class="fitem">
				<label>设备编号:</label>
				<input name="devNo" id="devNo" class="easyui-textbox" required="true"/>
			</div>
			<div class="fitem">
				<label>设备名称:</label>
				<input name="devName" id="devName" class="easyui-textbox" required="true"/>
			</div>
			<div class="fitem">
				<label>添加属性:</label>
				<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="obj.addField()" style="width:90px">添加属性</a>
			</div>

		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="obj.save()" style="width:90px">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="obj.close()" style="width:90px">Cancel</a>
	</div>
</body>
</html>