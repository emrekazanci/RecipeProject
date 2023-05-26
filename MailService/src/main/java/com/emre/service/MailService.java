package com.emre.service;

import com.emre.dto.response.ForgotPasswordToMailResponseDto;
import com.emre.rabbitmq.model.FavCategoriesSendMailModel;
import com.emre.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void createMail(RegisterMailModel registerMailModel) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Hesap Aktivasyon Kodu");
        simpleMailMessage.setFrom("ekazanci97@gmail.com");
        simpleMailMessage.setTo(registerMailModel.getEmail());
        simpleMailMessage.setText(registerMailModel.getUsername() + ", aktivasyon kodunuz: " + registerMailModel.getActivationCode());
        javaMailSender.send(simpleMailMessage);
    }

    public void createFavCategoryMail(FavCategoriesSendMailModel model) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Favori Kategorileriniz için Eklenen Tarifler");
        simpleMailMessage.setFrom("ekazanci97@gmail.com");
        simpleMailMessage.setTo(model.getEmail());
        simpleMailMessage.setText("Sevgili " + model.getUsername() + ",\nFavori kategorilerinize ait " + model.getRecipeName() + " isimli yeni bir tarif eklendi.");
        javaMailSender.send(simpleMailMessage);
    }

    public Boolean forgotPasswordToMail(ForgotPasswordToMailResponseDto dto) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("Şifre Değişikliği");
            simpleMailMessage.setFrom("ekazanci97@gmail.com");
            simpleMailMessage.setTo(dto.getEmail());
            simpleMailMessage.setText("Yeni şifreniz: " + dto.getPassword() + "\n Lütfen güvenliğiniz için giriş yaptıktan sonra şifrenizi değiştiriniz!!!");
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}
