package com.emre.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4300, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(4400, "Böyle bir kategori bulunamadı", HttpStatus.NOT_FOUND),
    RECIPE_NOT_FOUND(4500, "Böyle bir tarif bulunamadı", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4600, "Token Hatası", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4700, "Token oluşturulamadı", HttpStatus.BAD_REQUEST),
    CATEGORY_ALREADY_EXIST(4100, "Kategori zaten kayıtlı", HttpStatus.BAD_REQUEST),
    USER_NOT_ADD_DELETE_UPDATE(4101, "Kullanıcı yetkisine sahip olanlar, ekleme,silme,güncelleme yapamaz", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
