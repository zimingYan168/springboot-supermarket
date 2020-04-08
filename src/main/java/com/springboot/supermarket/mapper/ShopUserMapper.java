package com.springboot.supermarket.mapper;/*
 @author yanziming
 @date 2020/4/7 - 16:10
 */

import com.springboot.supermarket.bean.ShopUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ShopUserMapper {

    //根据传递过来的邮箱查询是否注册过
    @Select("select * from shopuser where useremail=#{useremail}")
    public ShopUser getByUserEmail(String useremail);

    //把传递过来的User对象写入
    @Insert("insert into shopuser(id,useremail,password) values(#{id},#{useremail},#{password}) ")
    public void  insertUser(ShopUser shopUser);

   //修改为激活状态
    @Update("update shopuser set activation=true where useremail=#{email}")
    public void activation(String email);

}
