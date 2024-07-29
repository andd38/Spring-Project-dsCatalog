package com.project_Spring_treino.dsCatalog.Services;

import com.project_Spring_treino.dsCatalog.Entities.Category;
import com.project_Spring_treino.dsCatalog.Repositories.CategoryRepository;
import com.project_Spring_treino.dsCatalog.Services.exception.ExceptionIdNotFound;
import com.project_Spring_treino.dsCatalog.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional (readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.get();
        return new CategoryDTO(entity);
    }
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = categoryRepository.findAll();

        List<CategoryDTO> listDTO = list.stream()
                .map(x-> new CategoryDTO(x))
                .collect(Collectors.toList());


        return listDTO;
    }


}
