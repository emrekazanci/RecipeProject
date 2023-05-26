package com.emre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequestDto {
    @NotBlank(message = "Lütfen bir yorum yazınız. Yorum satırı boş bırakılamaz!")
    private String comment;
    private String recipeId;
}
