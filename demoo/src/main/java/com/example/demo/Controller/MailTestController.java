package com.example.demo.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("qq")
public class MailTestController {


        @Autowired
        private JavaMailSender mailSender; //自动注入的Bean

        @Value("${spring.mail.username}")
        private String Sender; //读取配置文件中的参数

        @Test
        public void sendSimpleMail() throws Exception {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Sender);
            message.setTo(Sender); //自己给自己发送邮件
            message.setSubject("主题：简单邮件");
            message.setText("测试邮件内容");
            mailSender.send(message);
        }

    @Test
    public void sendAttachmentsMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(Sender);
            helper.setSubject("主题：带附件的邮件");
            helper.setText("带附件的邮件内容");
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File("target/demo-0.0.1-SNAPSHOT.war"));
//            FileSystemResource file = new FileSystemResource(new File("src/main/log/logback.log"));
            //加入邮件
            helper.addAttachment(file.getFilename(), file);
        } catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
