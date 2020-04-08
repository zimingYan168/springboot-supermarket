package com.springboot.supermarket.controller;/*
 @author yanziming
 @date 2020/4/7 - 16:36
 */

import com.springboot.supermarket.bean.ShopUser;
import com.springboot.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin("*")
@RestController
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JavaMailSenderImpl javaMailSender;    //邮件发送器

    //激活功能
    @GetMapping("/active")
    public String active(String email){
     return  userService.activateMail(email);

    }

    //    注册
    @PostMapping("/regist")
    public String registUser(ShopUser shopUser){
        String msg="";
        boolean flag=false;
        String check = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(shopUser.getUseremail());
        flag = matcher.matches();

        //正则表达式，
        //这个是一个企业级的程序里copy出来的。
        if (!flag) {
            System.out.println("邮箱不正确啊");
           msg="邮箱格式无效";
           return msg;
        }else{

        System.out.println("邮箱正确");
        //生成UUID
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String id=str.replace("-","");
        shopUser.setId(id);

        System.out.println(shopUser);

        //1.封装表单信息 调用service注册方法

        Map<String, String> map = userService.registUser(shopUser);

       //2.根据返回的Map查看是否注册成功
        if(map.isEmpty()){

            //如果为空、说明该用户已经被注册
           msg="该用户已被注册";

        }else{


            //注册成功 发送一封邮件
            //创建一个复杂的消息邮件
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            //邮件设置
            try {
                //true 的意思是：对附件进行编码
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
                mimeMessageHelper.setSubject("超市管理系统激活邮件");
                //true 意思是我写的这个内容是一个html
                mimeMessageHelper.setText("<a href='http://localhost:8081/active?email="+shopUser.getUseremail()+"'>点击这里，激活邮件</a>",true);

                mimeMessageHelper.setTo(shopUser.getUseremail());
                mimeMessageHelper.setFrom("483036606@qq.com");

                //发送
                javaMailSender.send(mimeMessage);

                msg="注册成功,请前往邮箱激活";
            } catch (MessagingException e) {
                msg="邮箱格式无效";
                e.printStackTrace();
            }




            //    测试发送简单邮件
        //  SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //标题
          //  simpleMailMessage.setSubject("");
            //内容
        //  simpleMailMessage.setText("<a href='http://localhost:8081/active?email="+shopUser.getUseremail()+">点击这里，激活邮件</a>",true);
//        收件人
        //   simpleMailMessage.setTo(shopUser.getUseremail());
//        发件人
       //   simpleMailMessage.setFrom("483036606@qq.com");





//            try {
//              //  javaMailSender.send(simpleMailMessage);
//                msg="注册成功,请前往邮箱激活";
//            } catch (Exception e) {
//               // msg="邮箱格式无效";
//                e.printStackTrace();
//            }



        }
        }
        //3.给页面返回一个JSON 字符串
        return msg;
    }

}
