package com.atlas.mygoods.passwordless;

import com.atlas.mygoods.models.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class EmailSender {

    @Value("${mygoods.email.from}")
    private String from;

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender aMailSender) {
        mailSender = aMailSender;
    }

    public void send(String aUserId, String aToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("from");
        mailMessage.setTo(aUserId);
        mailMessage.setSubject("Your signin link");
        mailMessage.setText(String.format("Hello!\nAccess your account here: http://localhost:8080/passwordless/signin/%s?uid=%s", aToken, aUserId));

        mailSender.send(mailMessage);
    }

    private void sendVerificationEmail(User user, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
//        TODO:change to company email
        String fromAddress = "adampeterson8981@gmail.com";
        String senderName = "MyGood";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
//                + "Please click the link below to verify your registration:<br>"
//                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br>"
                + "<h3>Code: [[code]]</h3><br>"
                + "Thank you,<br>"
                + "MyGood.";

        final MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteUrl + "/api/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);
        content = content.replace("[[code]]", user.getVerificationCode());

        helper.setText(content, true);

        mailSender.send(message);
    }


}
