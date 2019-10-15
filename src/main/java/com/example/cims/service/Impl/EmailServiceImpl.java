package com.example.cims.service.Impl;

import com.example.cims.Entity.User;
import com.example.cims.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendRegistrationSuccessEmail(User user,String role) {
        try{

            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Registration");

            if(role.equals("cust")){
                helper.setText("<div style=\"width:400px;background-color: #f3f3f3;\">\n" +
                        "    <div>\n" +
                        "      <h1 style=\"background-color: #f5d103;padding-left:12px;\">AutoMart</h1>\n" +
                        "    </div>\n" +
                        "    <div style=\"padding-left:12px;\">\n" +
                        "        <h2>Hi "+user.getFname()+",</h2>\n" +
                        "        <h3>You have successfully registered to AutoMart.</h3>\n" +
                        "        <h3>Enjoy shopping with us!</h3>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "  </div>", true);
            }
            else if(role.equals("admin")){
                helper.setText("<div style=\"width:400px;background-color: #f3f3f3;\">\n" +
                        "    <div>\n" +
                        "      <h1 style=\"background-color: #f5d103;padding-left:12px;\">AutoMart</h1>\n" +
                        "    </div>\n" +
                        "    <div style=\"padding-left:12px;\">\n" +
                        "        <h2>Hi "+user.getFname()+",</h2>\n" +
                        "        <h3>You have successfully registered to AutoMart as a Admin.</h3>\n" +
                        "    </div>\n" +
                        "    <br>\n" +
                        "  </div>", true);
            }
            javaMailSender.send(msg);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean testSendRegistrationSuccessEmail(String email) {
        try{
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setTo(user.getEmail());
//
//            msg.setSubject("AutoMart Registration");
//            msg.setText("Hi "+user.getFname()+", \n You have successfully registered to AutoMart. \n Enjoy shopping with us!");
//
//            javaMailSender.send(msg);

            MimeMessage msg = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setTo("hiranthaathapaththu@gmail.com");

            helper.setSubject("Testing from Spring Boot");

            // default = text/plain
            //helper.setText("Check attachment for image!");

            // true = text/html
            helper.setText("<div style=\"width:400px;background-color: #f3f3f3;\">\n" +
                    "    <div>\n" +
                    "      <h1 style=\"background-color: #f5d103;padding-left:12px;\">AutoMart</h1>\n" +
                    "    </div>\n" +
                    "    <div style=\"padding-left:12px;\">\n" +
                    "        <h2>Hi Hirantha,</h2>\n" +
                    "        <h3>You have successfully registered to AutoMart.</h3>\n" +
                    "        <h3>Enjoy shopping with us!</h3>\n" +
                    "    </div>\n" +
                    "    <br>\n" +
                    "  </div>", true);

            // hard coded a file path
            //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

            //helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

            javaMailSender.send(msg);


            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean sendOrderSuccessfulEmail(String email, List<Object> orderlist) {



        return false;
    }
}
