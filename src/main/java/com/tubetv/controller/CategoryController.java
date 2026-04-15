package com.tubetv.controller;

import com.tubetv.controller.request.CategoryRequest;
import com.tubetv.controller.response.CategoryResponse;
import com.tubetv.entity.Category;
import com.tubetv.mapper.CategoryMapper;
import com.tubetv.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    /*
    * injeção de dependencia
    * -Com anotação @Autowired
    * -Usando Lombok, com anotação @RequiredArgsConstructor
    * -Criando um construtor para o atributo
    * */
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        List<CategoryResponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(categories);

    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        Category newCategory = CategoryMapper.toCategory(categoryRequest);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return  ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());

        /*Optional<Category> optCategory = categoryService.findCategoryById(id);
        if (optCategory.isPresent()) {
            return ResponseEntity.ok(CategoryMapper.toCategoryResponse(optCategory.get()));
        }
        return ResponseEntity.notFound().build();*/
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
