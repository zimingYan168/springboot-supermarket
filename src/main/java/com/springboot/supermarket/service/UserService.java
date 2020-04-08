package com.springboot.supermarket.service;/*
 @author yanziming
 @date 2020/4/7 - 16:16
 */

import com.springboot.supermarket.bean.ShopUser;
import com.springboot.supermarket.mapper.ShopUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    //该service用于用户的登录和注册

    @Autowired
   ShopUserMapper shopUserMapper;

    //邮件激活功能
    public String activateMail(String useremail){
        String msginfo;
        //1.根据传递过来的邮箱号 查看账户是否存在
            //1.1若不存在 显示激活码无效
            //1.2若存在
                //2.1 判断该账户是否被激活
                //激活过 显示该账户已被激活
                //没有激活 修改数据库信息 显示激活成功
        ShopUser user=  shopUserMapper.getByUserEmail(useremail);
        if (user !=null){
            //账户存在
            boolean activation = user.isActivation();

            if(activation){
             //已经激活过了
             msginfo="该账号已被激活";


            }else {

                //进入这里说明可以激活
                shopUserMapper.activation(user.getUseremail());


                msginfo="激活成功，请前往登录";
            }

        }else{
            msginfo="激活码无效";
        }


        return msginfo;
    }

    //注册功能
    public Map<String,String> registUser(ShopUser shopUser){


        Map<String,String> map=new HashMap<String,String>();

        //第一步 先查找是否存在
        ShopUser user= shopUserMapper.getByUserEmail(shopUser.getUseremail());
        if (user== null){
            //若不存在说明可以注册
            shopUserMapper.insertUser(shopUser);
            map.put("msg","注册成功，立即前往登录");
        }

            //不能注册，返回一个空的map



        return map;
    }
}
