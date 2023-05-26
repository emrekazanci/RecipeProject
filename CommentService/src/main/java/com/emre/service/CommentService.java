package com.emre.service;

import com.emre.dto.request.AddCommentRequestDto;
import com.emre.dto.request.UpdateCommentRequestDto;
import com.emre.dto.response.GetUserIdAndUsernameDto;
import com.emre.exception.CommentManagerException;
import com.emre.exception.ErrorType;
import com.emre.manager.IRecipeManager;
import com.emre.manager.IUserProfileManager;
import com.emre.mapper.ICommentMapper;
import com.emre.repository.ICommentRepository;
import com.emre.repository.entity.Comment;
import com.emre.utility.JwtTokenProvider;
import com.emre.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment, String> {
    private final ICommentRepository commentRepository;
    private final IUserProfileManager userProfileManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IRecipeManager recipeManager;

    public CommentService(ICommentRepository commentRepository,
                          IUserProfileManager userProfileManager,
                          JwtTokenProvider jwtTokenProvider,
                          IRecipeManager recipeManager) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.userProfileManager = userProfileManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.recipeManager = recipeManager;
    }

    public Comment addComment(String token, AddCommentRequestDto addCommentDto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        GetUserIdAndUsernameDto dto = userProfileManager.getUserIdAndUserNameWithAuthId(authId.get()).getBody();
        Comment comment = ICommentMapper.INSTANCE.addCommentDtoToComment(addCommentDto);
        String recipeId = recipeManager.getRecipeIdForIsExistRecipe(addCommentDto.getRecipeId()).getBody();
        if (!comment.getRecipeId().equals(recipeId))
            throw new CommentManagerException(ErrorType.RECIPE_NOT_FOUND);
        comment.setUserId(dto.getUserId());
        comment.setUsername(dto.getUsername());
        save(comment);
        recipeManager.sendCommentId(comment.getCommentId(), comment.getRecipeId());
        return comment;
    }

    public Boolean updateComment(String token, UpdateCommentRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        GetUserIdAndUsernameDto userProperties = userProfileManager.getUserIdAndUserNameWithAuthId(authId.get()).getBody();
        Optional<Comment> optionalComment = commentRepository.findById(dto.getCommentId());
        if (optionalComment.isEmpty()) throw new CommentManagerException(ErrorType.COMMENT_NOT_FOUND);
        if (!optionalComment.get().getUserId().equals(userProperties.getUserId()))
            throw new CommentManagerException(ErrorType.COMMENT_IS_NOT_THIS_USER);
        update(ICommentMapper.INSTANCE.updateCommentDtoToComment(dto, optionalComment.get()));
        return true;
    }

    public Boolean deleteComment(String token, String commentId) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        GetUserIdAndUsernameDto dto = userProfileManager.getUserIdAndUserNameWithAuthId(authId.get()).getBody();
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (!comment.get().getUserId().equals(dto.getUserId()))
            throw new CommentManagerException(ErrorType.COMMENT_IS_NOT_THIS_USER);
        commentRepository.deleteById(commentId);
        recipeManager.deleteCommentInRecipeForDeletedComment(comment.get().getRecipeId(), commentId);
        return true;
    }

    public Boolean deleteCommentForDeletedRecipe(String recipeId) {
        List<Comment> comments = commentRepository.findByRecipeId(recipeId);
        comments.forEach(willDelete -> deleteById(willDelete.getCommentId()));
        return true;
    }
}
