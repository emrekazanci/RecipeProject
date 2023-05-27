package com.emre.controller;

import com.emre.repository.entity.Category;
import com.emre.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(CATEGORY_SAVE + "/{token}/{categoryName}")
    public ResponseEntity<String> saveCategory(@PathVariable String token,@PathVariable @Valid String categoryName) {
        return ResponseEntity.ok(categoryService.saveCategory(token, categoryName));
    }

    @DeleteMapping(CATEGORY_DELETE + "/{token}")
    public ResponseEntity<String> deleteCategory(@PathVariable String token, String categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(token, categoryId));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }
}
