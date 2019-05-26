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
<body id="userBody">
<div class="pd-20">

    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-4">
                用户名称: <input type="text" class="input-text" style="width: 250px" id="userName" value="${userName}">
            </div>

        </div>
        <div class="row cl">
            <div class="cl pd-5">
                <button type="button"
                        class="btn btn-success radius" id="" name="" onclick="selectByCondition()">
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
            <th scope="col" colspan="8">用户管理</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">用户ID</th>
            <th width="100">用户名称</th>
            <th width="100">电话</th>
            <th width="100">邮箱</th>
            <th width="100">生日</th>
            <th width="100">是否有效</th>
            <th width="200">个人简介</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysUser">
            <tr class="text-c">
                <td><input type="checkbox" class="addBox" value="${sysUser.userId}" name="userId"></td>
                <td>${sysUser.userId}</td>
                <td>${sysUser.userName}</td>
                <td>${sysUser.phone}</td>
                <td>${sysUser.email}</td>
                <td>
                    <fmt:formatDate value="${sysUser.birthday}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>
                    <c:if test="${sysUser.flag}">
                        是
                    </c:if>
                    <c:if test="${!sysUser.flag}">
                        否
                    </c:if>
                </td>
                <td>${sysUser.introduce}</td>
                <td class="f-14">
                    <a title="删除" href="javascript:;" onclick="del_role_user(this,${sysUser.userId})" class="ml-5"
                       style="text-decoration:none">
                        <i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--装展示分页导航的容器--%>
    <jsp:include page="/application/page.jsp">
        <jsp:param name="bodyId" value="userBody"/>
    </jsp:include>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script type="text/javascript">
    /*通过用户名搜索没有授权的用户*/
    function selectByCondition() {
        var userName=$("#userName").val();

        $("#userBody").load("authorization/queryNoAuthUserByRoleId",{"userName":userName,"roleId":"${roleId}"});
    }

    /*批量授权*/
    function batchAdd() {
        var ids=[];
        if((".addBox:checked").length==0){
            alert("你还没有选择用户");
            return
        }
       $(".addBox:checked").each(function () {
           ids.push($(this).val());
       })

        $.ajax({
            type:"POST",
            url:"authorization/batchAdd",
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

</body>
</html>
