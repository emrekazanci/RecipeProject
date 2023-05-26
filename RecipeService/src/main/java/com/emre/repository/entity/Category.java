package com.emre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Category extends Base{
    @Id
    private String categoryId;
    @NotBlank(message = "Lütfen bir kategori adı giriniz.")
    private String categoryName;
}
