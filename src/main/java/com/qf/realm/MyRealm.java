package com.qf.realm;

import com.qf.entity.SysUser;
import com.qf.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm{
    @Autowired
    private ISysUserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权处理");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证处理");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username=token.getUsername();
        //通过用户名查询这个用户
        SysUser sysUser= userService.getUserByName(username);
        ByteSource byteSource = ByteSource.Util.bytes(username);//盐值
        if(sysUser!=null){
           SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(sysUser,sysUser.getUserPassword(),byteSource,this.getName());
           return info;
       }
        return null;
    }
}
