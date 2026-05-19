package com.tubetv.service;

import com.tubetv.entity.Category;
import com.tubetv.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    /*
     * injeção de dependencia
     * -Com anotação @Autowired
     * -Usando Lombok, com anotação @RequiredArgsConstructor
     * -Criando um construtor para o atributo
     * */
    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        category.setId(categoryRepository.saveAndFlush(category).getId());
        return categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> updateCategory(Long categoryId, Category category) {
        Optional<Category> optlCategory = categoryRepository.findById(categoryId);
        if (optlCategory.isPresent()) {
            Category updatedCategory = optlCategory.get();
            updatedCategory.setName(category.getName());

            categoryRepository.save(updatedCategory);
            return Optional.of(updatedCategory);
        }
        return Optional.empty();
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
