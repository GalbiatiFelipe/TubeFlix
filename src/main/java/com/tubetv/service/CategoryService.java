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
        return categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
