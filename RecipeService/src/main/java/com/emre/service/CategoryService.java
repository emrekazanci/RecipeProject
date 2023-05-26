package com.emre.service;

import com.emre.exception.ErrorType;
import com.emre.exception.RecipeManagerException;
import com.emre.repository.ICategoryRepository;
import com.emre.repository.entity.Category;
import com.emre.repository.entity.ERole;
import com.emre.utility.JwtTokenProvider;
import com.emre.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService extends ServiceManager<Category, String> {
    private final ICategoryRepository categoryRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public CategoryService(ICategoryRepository categoryRepository, JwtTokenProvider jwtTokenProvider) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String saveCategory(String token, String categoryName) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        if (role.get().equals(ERole.ADMIN.toString())) {
            Category category = Category.builder().categoryName(categoryName).build();
            if (categoryRepository.existsByCategoryNameIgnoreCase(category.getCategoryName()))
                throw new RecipeManagerException(ErrorType.CATEGORY_ALREADY_EXIST);
            else save(category);
        } else {
            throw new RecipeManagerException(ErrorType.USER_NOT_ADD_DELETE_UPDATE);
        }
        return "Kategori eklenmiştir.";
    }

    public String deleteCategory(String token, String categoryId) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        if (role.get().equals(ERole.ADMIN.toString())) {
            Optional<Category> category = findById(categoryId);
            if (category.isEmpty()) throw new RecipeManagerException(ErrorType.CATEGORY_NOT_FOUND);
            deleteById(category.get().getCategoryId());
        } else {
            throw new RecipeManagerException(ErrorType.USER_NOT_ADD_DELETE_UPDATE);
        }
        return "Kategori silinmiştir.";
    }

    public Boolean existsByCategoryId(String categoryId) {
        return categoryRepository.existsByCategoryId(categoryId);
    }

}
