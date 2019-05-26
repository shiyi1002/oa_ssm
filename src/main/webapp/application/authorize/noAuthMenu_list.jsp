<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/22 0022
  Time: 下午 7:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/3/18
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <base href="<%=request.getContextPath()+"/"%>">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>

</head>
<body id="menuBody">

<div class="pd-20 container">
    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-4">
                菜单名称: <input type="text" class="input-text" style="width: 250px" id="menuName" value="${menuName}">
            </div>

        </div>
        <div class="row cl">
            <div class="cl pd-5">
                <button type="button"
                        class="btn btn-success radius" id="" name="" onclick="queryNoAuthMenuByRoleId()">
                    <i class="Hui-iconfont">&#xe665;</i> 搜索
                </button>
            </div>
        </div>
    </div>
    <div class="cl pd-5 bg-1 bk-gray">
        <a href="javascript:;" onclick="batchAdd()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 批量授权</a>
    </div>
    <div class="cl pd-5 bg-1 bk-gray"></div>
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="7">菜单管理</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">菜单ID</th>
            <th width="100">父菜单编号</th>
            <th width="100">菜单名</th>
            <th width="100">菜单类型</th>
            <th width="100">菜单路径</th>
            <th width="100">是否公共</th>
            <th width="100">创建时间</th>
            <th width="100">修改时间</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysMenu">
            <tr class="text-c">
                <td><input type="checkbox" value="${sysMenu.menuId}" class="selAll" name="menuId"></td>
                <td>${sysMenu.menuId}</td>
                <td>${sysMenu.menuParentId}</td>
                <td>${sysMenu.menuName}</td>
                <td>${sysMenu.menuType}</td>
                <td>${sysMenu.menuPath}</td>
                <td>
                    <c:if test="${sysMenu.isPublish}">是</c:if>
                    <c:if test="${!sysMenu.isPublish}">否</c:if>
                </td>

                <td><fmt:formatDate value="${sysMenu.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                <td><fmt:formatDate value="${sysMenu.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                <td class="f-14"><a title="编辑" href="javascript:;" onclick="admin_menu_edit('菜单编辑','sysMenu/toUpdate?menuId=${sysMenu.menuId}','1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="admin_menu_del(this,${sysMenu.menuId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--装展示分页导航的容器--%>
    <jsp:include page="/application/page.jsp">
        <jsp:param name="bodyId" value="menuBody"/>
    </jsp:include>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script type="text/javascript">
    /*通过菜单名搜索没有授权的用户*/
    function queryNoAuthMenuByRoleId() {
        var menuName=$("#menuName").val();

        $("#menuBody").load("authorization/queryNoAuthMenuByRoleId",{"menuName":menuName,"roleId":"${roleId}"});
    }

    /*批量授权*/
    function batchAdd() {
        var ids=[];
        if((".selAll:checked").length==0){
            alert("你还没有选择菜单");
            return
        }
        $(".selAll:checked").each(function () {
            ids.push($(this).val());
        })

        $.ajax({
            type:"POST",
            url:"authorization/batchAddMenu",
            data:"ids="+ids+"&roleId="+${roleId},

            success:function (data) {
                if(data.result){
                    alert("批量授权成功");
                    location.reload();
                }else{
                    alert("批量授权失败");
                }
            }

        });
    }
</script>
</body>
</html>


