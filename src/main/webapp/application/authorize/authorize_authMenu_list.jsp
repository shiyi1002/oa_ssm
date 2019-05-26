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
<body id="userBody">
<div class="pd-20">

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
                <td><input type="checkbox" value="${sysMenu.menuId}" class="delAll" name="batchdel"></td>
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
        <jsp:param name="bodyId" value="authorization"></jsp:param>
    </jsp:include>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script type="text/javascript">
    /*解除用户和角色的关系*/

    /*解除菜单和角色的关系*/

    function admin_menu_del(obj,id) {
        layer.confirm('确定要解除与该用户的关系？', function (index) {
            $.ajax({
                    type:"POST",
                    url:"authorization/delRoleAndMenu",
                    data: "menuId=" + id+"&roleId="+${params}.roleId,
                success: function (data) {
                if (data.result) {
                    layer.msg('解决授权成功!', {icon: 1, time: 2000});
                    location.reload();
                } else {
                    layer.msg('解决授权失败!', {icon: 2, time: 2000});
                }
            }

        })
        });
    }

</script>
</body>
</html>
