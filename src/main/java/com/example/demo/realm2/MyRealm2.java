package com.example.demo.realm2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-25
 * <p>Version: 1.0
 */
@Slf4j
public class MyRealm2 implements Realm {

    @Override
    public String getName() {
        return "myrealm2";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken; //仅支持UsernamePasswordToken类型的Token
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"wang".equals(username)) {
            log.info("realm2 用户名不为 'wang' ***** username = " +username);
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            log.info("密码不为 '123' ***** password = " +password);
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        log.info("*********** realm2认证成功");
        return new SimpleAuthenticationInfo(username + "_realm2", password, getName());
    }
}
