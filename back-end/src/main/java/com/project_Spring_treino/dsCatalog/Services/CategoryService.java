package com.project_Spring_treino.dsCatalog.Services;

import com.project_Spring_treino.dsCatalog.Entities.Category;
import com.project_Spring_treino.dsCatalog.Repositories.CategoryRepository;
import com.project_Spring_treino.dsCatalog.Services.exception.ExceptionIdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(()->new ExceptionIdNotFound("id not found"));
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }


}
