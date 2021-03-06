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

        $(function () {
            $('#tb1').datagrid({
                // url: 'datagrid_data1.json',
                url: baseUrl + 'user/page',
                width: 1100,
                title: '用户管理',
                method: 'get',
                toolbar: '#tb',
                singleSelect: 'true',
                columns: [[
                    {field: 'id', title: 'id', width: 150},
                    {field: 'userName', title: '用户名称', width: 150},
                    {field: 'email', title: '邮箱', width: 150},
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
                        userName: $('#searchUserName').val()
                    });
                },
                add: function() {
                    $('#fm').form('clear');
                    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加用户');
                },
                update: function(index) {
                    if(index != null){
                        $('#tb1').datagrid('selectRow', index);
                    }
                    var row = $('#tb1').datagrid('getSelected');
                    if (row) {
                        $('#dlg').dialog('open').dialog('center').dialog('setTitle', '修改用户');
                        $('#fm').form('load',{
                            id:row.id,
                            userName:row.userName,
                            email:row.email,
                            roleKey:row.roleKey
                        });
                    }

                },
                delete: function(index) {
                    if(index != null){
                        $('#tb1').datagrid('selectRow', index);
                    }
                    var row = $('#tb1').datagrid('getSelected');
                    if (row) {
                        $.messager.confirm('Confirm', '确定删除用户吗?', function (r) {
                            if (r) {
                                $.post(baseUrl+'user/delete', {id: row.id}, function (result) {
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
                    var userName = document.getElementById("userName").value;
                    var email = document.getElementById("email").value;
                    var url = "";
                    if(id) {
                        url = baseUrl+"user/update";
                    }else {
                        url = baseUrl+"user/insert";
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

                }
            }

            var data = [
                {'text' : '普通用户', 'value' : 'NORMAL'},
                {'text' : '管理员', 'value' : 'ADMIN'}
            ];

            $('#roleKey').combobox({
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
    用户编号: <input id="searchUserName" class="easyui-textbox"/>
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
            <label>角色:</label>
            <input name="roleKey" id="roleKey" required="true"/>
        </div>
        <div class="fitem">
            <label>用户名称:</label>
            <input name="userName" id="userName" class="easyui-textbox" required="true"/>
        </div>
        <div class="fitem">
            <label>邮箱:</label>
            <input name="email" id="email" class="easyui-textbox" required="true"/>
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="obj.save()" style="width:90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="obj.close()" style="width:90px">Cancel</a>
</div>
</body>
</html>