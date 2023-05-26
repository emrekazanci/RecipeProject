package com.emre.mapper;

import com.emre.dto.request.DeleteCommentForDeletedRecipe;
import com.emre.dto.request.GetIngredientsName;
import com.emre.dto.request.SaveRecipeRequestDto;
import com.emre.dto.request.UpdateRecipeRequestDto;
import com.emre.repository.entity.Recipe;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRecipeMapper {
    IRecipeMapper INSTANCE = Mappers.getMapper(IRecipeMapper.class);

    Recipe saveFromSaveRecipeDtoToRecipe(final SaveRecipeRequestDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recipe updateFromUpdateRecipeDtoToRecipe(final UpdateRecipeRequestDto dto,@MappingTarget Recipe recipe);

}
