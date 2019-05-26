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
<body id="roleBody">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 角色管理 <span class="c-gray en">&gt;</span>角色列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20 container">
    
    <div class="cl pd-5 bg-1 bk-gray"> <span class="l"> <a href="javascript:;" onclick="batchdel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加用户','sysRole/toAdd','800')"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a> </span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
    <form>
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="7">用户管理</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">角色ID</th>
            <th width="100">角色名</th>
            <th width="100">角色描述</th>
            <th width="100">是否有效</th>
            <th width="100">创建时间</th>

            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysRole">
            <tr class="text-c">
                <td><input type="checkbox" value="${sysRole.roleId}" class="delAll" name="batchdel"></td>
                <td>${sysRole.roleId}</td>
                <td>${sysRole.roleName}</td>
                <td>${sysRole.roleDesc}</td>

                <td>
                    <c:if test="${sysRole.flag}">是</c:if>
                    <c:if test="${!sysRole.flag}">否</c:if>
                </td>
                <td>
                    <fmt:formatDate value="${sysRole.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>

                <td class="f-14"><a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','sysRole/toUpdate?roleId=${sysRole.roleId}','1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="admin_role_del(this,${sysRole.roleId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
    <div class="row">
        <!--显示分页数据-->
        <div class="col-md-6">
            总${pageInfo.pages}页,总共${pageInfo.total}条记录
        </div>
        <!--显示分页条-->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="sysRole/page?pageNum=1">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="sysRole/page?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page">
                        <c:if test="${page==pageInfo.pageNum}">
                            <li class="active"><a href="sysRole/page?pageNum=${page}">${page}</a></li>
                        </c:if>
                        <c:if test="${page!=pageInfo.pageNum}">
                            <li ><a href="sysRole/page?pageNum=${page}">${page}</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="sysRole/page?pageNum=${pageInfo.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <li><a href="sysRole/page?pageNum=${pageInfo.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>
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
    /*管理员-角色-添加*/
    function admin_role_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-组织-编辑*/
    function admin_role_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-组织-删除*/
    function admin_role_del(obj,id){
        layer.confirm('组织删除须谨慎，确认要删除吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            $.ajax({
                url:"sysRole/delete",
                type:"POST",
                data:{"roleId":id},
                success: function (result) {
                    if(result.result){
                        $(obj).parents("tr").remove();
                        layer.msg('删除成功!',{icon:1,time:1000});
                        //parent.location.reload();
                    }else{

                        layer.msg('删除失败!',{icon:2,time:1000});
                    }
                }
            })


        });
    }
  function batchdel() {
      layer.confirm('角色删除须谨慎，确认要删除吗？',function(index) {
          //此处请求后台程序，下方是成功后的前台处理……
          var ids = [];
          var checks = $(".delAll:checked");
              if (checks.length == 0) {
                  alert("你还没有选中删除的数据");
                  return;
              }
          for (var i = 0; i < checks.length; i++) {
              ids.push(checks[i].value);
          }

          $.ajax({
              url: "sysRole/batchdel",
              type: "POST",
              data: "ids=" + ids,
              success: function (result) {
                  if (result.result) {

                      layer.msg('删除成功!', {icon: 1, time: 1000});
                      location.reload();
                  } else {

                      layer.msg('不能删除!', {icon: 2, time: 1000});
                  }
              }
          })


      });
  }
 

</script>
</body>
</html>
