package com.emre.mapper;

import com.emre.dto.request.AddCommentRequestDto;
import com.emre.dto.request.UpdateCommentRequestDto;
import com.emre.repository.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-26T23:46:37+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ICommentMapperImpl implements ICommentMapper {

    @Override
    public Comment addCommentDtoToComment(AddCommentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder<?, ?> comment = Comment.builder();

        comment.comment( dto.getComment() );
        comment.recipeId( dto.getRecipeId() );

        return comment.build();
    }

    @Override
    public Comment updateCommentDtoToComment(UpdateCommentRequestDto dto, Comment comment) {
        if ( dto == null ) {
            return comment;
        }

        if ( dto.getCommentId() != null ) {
            comment.setCommentId( dto.getCommentId() );
        }
        if ( dto.getComment() != null ) {
            comment.setComment( dto.getComment() );
        }

        return comment;
    }
}
