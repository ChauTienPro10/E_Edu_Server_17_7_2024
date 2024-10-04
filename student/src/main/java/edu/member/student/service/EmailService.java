package edu.member.student.service;

import edu.member.student.congfifuration.CustomJwtDecoder;
import edu.member.student.dto.request.VerifyCodeRequest;
import edu.member.student.dto.response.EmailSendResponse;
import edu.member.student.entity.VerifyCode;
import edu.member.student.repository.VerifyCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String rootMail;
    @Autowired
    private JwtUtil jwtUtil;
    private final JwtDecoder jwtDecoder;
    @Autowired
    VerifyCodeRepository verifyCodeRepository; // khai bao repository cua verifycode
    @Autowired
    public EmailService(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    public EmailSendResponse sendSimpleEmail(String to, String title, String content) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(title);
            message.setText(content);
            message.setFrom(this.rootMail);
            javaMailSender.send(message);
            return  EmailSendResponse.builder()
                    .notify("Send email success!")
                    .build();
        }
        catch (Exception ex){
            return  EmailSendResponse.builder()
                    .notify("Send email failure!")
                    .build();
        }

    }
    public VerifyCode generateCode(String email){ // khoi tao mot ma code  moi
        Random random = new Random(); // su dung ham random
        int code = 100000 + random.nextInt(900000); // tao ngau nhien ma gom 6 so
        return VerifyCode.builder()
                .code(String.valueOf(code))
                .email(email)
                .expirationTime(LocalDateTime.now().plusMinutes(1))
                .build();
    }

    public VerifyCode sendCode(String email){
        try{
            VerifyCode code=generateCode(email);
            sendSimpleEmail(email,"Verify code", code.getCode());
            return verifyCodeRepository.save(code);
        }
        catch (Exception ex){
            log.info(ex.toString());
            return null;
        }
    }

    public boolean verificationCode( VerifyCodeRequest request,String _jwt){
        Jwt jwt=jwtDecoder.decode(_jwt);
        String username = jwtUtil.extractUsername(jwt);
        Optional<VerifyCode> verifyCode=verifyCodeRepository
                .findByEmailAndCode(username, request.getCode());
        if (verifyCode.isPresent()) {
            log.info(verifyCode.get().getExpirationTime().toString());
            verifyCodeRepository.delete(verifyCode.get());
            return verifyCode
                   .map(VerifyCode::getExpirationTime)  // Trích xuất thời gian hết hạn
                   .map(expirationTime -> expirationTime.isAfter(LocalDateTime.now())) // Kiểm tra xem có sau thời điểm hiện tại
                   .orElse(false);
        } else {
            return false; // Nếu không tìm thấy giá trị
        }
    }

    public  String getMail( String _jwt){
        Jwt jwt=jwtDecoder.decode(_jwt);
        String username = jwtUtil.extractUsername(jwt);
        return username;
    }
}
