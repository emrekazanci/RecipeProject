package com.emre.controller;

import com.emre.dto.request.AddCommentRequestDto;
import com.emre.dto.request.UpdateCommentRequestDto;
import com.emre.repository.entity.Comment;
import com.emre.service.CommentService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(COMMENT_ADD + "/{token}")
    public ResponseEntity<Comment> addComment(@PathVariable String token, @RequestBody @Valid AddCommentRequestDto dto) {
        return ResponseEntity.ok(commentService.addComment(token, dto));
    }

    @PutMapping(UPDATE + "/{token}")
    public ResponseEntity<Boolean> updateComment(@PathVariable String token, @RequestBody UpdateCommentRequestDto dto) {
        return ResponseEntity.ok(commentService.updateComment(token, dto));
    }

    @DeleteMapping(DELETE + "/{token}/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable String token, @PathVariable String commentId) {
        return ResponseEntity.ok(commentService.deleteComment(token, commentId));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    //==========================

    @PostMapping("/delete-comment-for-deleted-recipe/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> deleteCommentForDeletedRecipe(@PathVariable String recipeId) {
        return ResponseEntity.ok(commentService.deleteCommentForDeletedRecipe(recipeId));
    }


}
