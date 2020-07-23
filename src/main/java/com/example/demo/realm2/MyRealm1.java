package com.example.demo.realm2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author mjiang
 * @date 2020/7/1/0001 15:17
 * @description
 */
@Slf4j
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //得到用户名
        String username = (String)token.getPrincipal();
        //得到密码
        String password = new String((char[])token.getCredentials());
        if(!"zhang".equals(username)) {
            log.info("realm1 用户名不为 'zhang' ***** username = " +username);
            //如果用户名错误
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)) {
            //如果密码错误
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        log.info("*********** realm1认证成功");
        return new SimpleAuthenticationInfo(username + "_realm1", password, getName());
    }

}
