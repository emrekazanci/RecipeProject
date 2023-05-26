package com.emre.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    USERNAME_DUPLICATE(4401, "Böyle bir kullanıcı zaten kayıtlı", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4600, "Token Hatası", HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4700, "Token oluşturulamadı", HttpStatus.BAD_REQUEST),
    RECIPE_NOT_FOUND(4100, "Bu recipeId'ye ait bir Recipe bulunamadı", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND(4101, "Comment bulunamadı", HttpStatus.NOT_FOUND),
    COMMENT_IS_NOT_THIS_USER(4102, "Bu yorum, bu kullanıcıya ait değildir", HttpStatus.BAD_REQUEST),
    POINT_IS_NOT_THIS_USER(4102, "Bu puan, bu kullanıcıya ait değildir", HttpStatus.BAD_REQUEST),
    POINT_NOT_FOUND(4103,"Puan bulunamadı",HttpStatus.NOT_FOUND);
    private int code;
    private String message;
    HttpStatus httpStatus;
    }
