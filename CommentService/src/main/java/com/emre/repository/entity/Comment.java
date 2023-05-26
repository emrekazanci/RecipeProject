package com.emre.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment extends Base {
    @Id
    private String commentId;
    private String username;
    private String comment;
    @Builder.Default
    private Long commentDate = System.currentTimeMillis();
    private String userId;
    private String recipeId;
}
