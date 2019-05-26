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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <base href="<%=request.getContextPath()+"/"%>">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body id="userBody">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 采购管理 <span class="c-gray en">&gt;</span>采购申请 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20 container">

    <div class="cl pd-5 bg-1 bk-gray"> <span class="l"> <a href="javascript:;" onclick="batchdel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;" onclick="admin_purchase_add('添加用户','purchase/toAdd','800')"><i class="Hui-iconfont">&#xe600;</i> 添加采购单</a> </span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
    <form>
        <table class="table table-border table-bordered table-hover table-bg">
            <thead>
            <tr>
                <th scope="col" colspan="7">采购订单管理</th>
            </tr>
            <tr class="text-c">
                <th width="25"><input type="checkbox" value="" name=""></th>
                <th width="40">用户ID</th>
                <th width="100">标题</th>
                <th width="100">金额</th>
                <th width="100">用户名</th>
                <th width="100">内容</th>
                <th width="100">创建时间</th>
                <th width="100">是否有效</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="sysPurchase">
                <tr class="text-c">
                    <td><input type="checkbox" value="${sysPurchase.id}" class="delAll" name="batchdel"></td>
                    <td>${sysPurchase.title}</td>
                    <td>${sysPurchase.money}</td>
                    <td>${sysPurchase.userName}</td>
                    <td>${sysPurchase.content}</td>
                    <td><fmt:formatDate value="${sysPurchase.creatTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>

                    <td>
                        <c:if test="${sysPurchase.flag}">是</c:if>
                        <c:if test="${!sysPurchase.flag}">否</c:if>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/layui/layui.all.js"></script>
<script type="text/javascript">
    /*管理员-采购-添加*/
    function admin_purchase_add(title,url,w,h){//添加弹框
        layer_show(title,url,w,h);
    }
    /*管理员-用户-编辑*/
    function admin_user_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }


</script>
</body>
</html>
