package com.tubetv.mapper;

import com.tubetv.controller.request.CategoryRequest;
import com.tubetv.controller.response.CategoryResponse;
import com.tubetv.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    /*
    * -To Category
    * transformar o Record category request em Category
    *
    * -Padrão Builder
     * Uma forma simplificada de fazer o mapeamento para a entidade, atraves da annotation @Builder na classe principal
    * */

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
