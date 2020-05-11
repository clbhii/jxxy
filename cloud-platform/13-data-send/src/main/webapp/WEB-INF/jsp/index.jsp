<%@ page import="com.cl.common.Contants" %>
<%@ page import="com.cl.model.UserDO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		var data = [{
			text: '系统管理',
			iconCls: 'fa fa-wpforms',
			state: 'open',
			children: [{
				text: '用户管理',
				url:'../mvc/userlist'
			}]
		},{
			text: '终端管理',
			iconCls: 'fa fa-at',
			selected: true,
			children: [{
				text: '设备管理',
				url:'../mvc/devicelist'
			},{
				text: '在线设备管理',
				url:'../mvc/devicedatalist'
			}
			]
		}];

		function onSideMenuSelect(item) {
			if (!$('#mainTab').tabs('exists', item.text)) {
				$('#mainTab').tabs('add', {
					title: item.text,
					content: '<iframe scrolling="auto" frameborder="0"  src="' + item.url + '" style="width:100%;height:99%;"></iframe>',
					closable: true,
					icon: item.iconCls,
					id: item.id
				});
			} else {
				$('#mainTab').tabs('select', item.text);
			}
		}
	</script>
</head>
<body class="easyui-layout">
	<% UserDO userDO = (UserDO)request.getSession().getAttribute(Contants.USER_SESSION);
	   String name = userDO.getUserName();
	   System.out.println(userDO.getUserName());
	%>
		<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
			<div style="float:left;width:49%;font-size: 250%">物联网云平台</div>
			<div style="float:right;width:10%;font-size: 250%"><%=name%></div>
		</div>
		<div data-options="region:'west',split:true,title:'菜单'" style="width:250px;padding:10px;">
			<div id="leftMenu" class="easyui-sidemenu" data-options="data:data,onSelect: onSideMenuSelect" ></div>
		</div>
		<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
		<div data-options="region:'center',title:'Center'">
			<div id="mainTab" class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:100%;height:99%">
			</div>
		</div>
</body>
</html>