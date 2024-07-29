package com.project_Spring_treino.dsCatalog.Services;

import com.project_Spring_treino.dsCatalog.Entities.Category;
import com.project_Spring_treino.dsCatalog.Repositories.CategoryRepository;
import com.project_Spring_treino.dsCatalog.Resources.exceptions.DatabaseException;
import com.project_Spring_treino.dsCatalog.Services.exception.ExceptionIdNotFound;
import com.project_Spring_treino.dsCatalog.dto.CategoryDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        Category entity = obj.orElseThrow(()->new ExceptionIdNotFound("id not found"));
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

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity  = new Category();
        entity.setName(dto.getName());
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id,CategoryDTO dto) {
        try{
            Category entity =categoryRepository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = categoryRepository.save(entity);
            return new CategoryDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ExceptionIdNotFound("id not found"+id);
        }

    }

    @Transactional
    public void deleteById(Long id){
        if(!categoryRepository.existsById(id)){
            throw  new ExceptionIdNotFound("Resource not found");
        }
        try{
            categoryRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

}
