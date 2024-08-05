package com.project_Spring_treino.dsCatalog.Services;

import com.project_Spring_treino.dsCatalog.Entities.Category;
import com.project_Spring_treino.dsCatalog.Entities.Product;
import com.project_Spring_treino.dsCatalog.Repositories.CategoryRepository;
import com.project_Spring_treino.dsCatalog.Repositories.ProductRepository;
import com.project_Spring_treino.dsCatalog.Resources.exceptions.DatabaseException;
import com.project_Spring_treino.dsCatalog.Services.exception.ExceptionIdNotFound;
import com.project_Spring_treino.dsCatalog.dto.CategoryDTO;
import com.project_Spring_treino.dsCatalog.dto.ProductDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository ProductRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional (readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> obj = ProductRepository.findById(id);
        Product entity = obj.orElseThrow(()->new ExceptionIdNotFound("id not found"));
        return new ProductDTO(entity, entity.getCategories());
    }
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> list = ProductRepository.findAll(pageRequest);

        Page<ProductDTO> listDTO = list.map(x-> new ProductDTO(x));



        return listDTO;
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity  = new Product();

        copyDtoToEntity(dto,entity);
        entity = ProductRepository.save(entity);
        return new ProductDTO(entity);
    }



    @Transactional
    public ProductDTO update(Long id,ProductDTO dto) {
        try{
            Product entity =ProductRepository.getReferenceById(id);

            copyDtoToEntity(dto,entity);
            entity = ProductRepository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ExceptionIdNotFound("id not found"+id);
        }

    }

    @Transactional
    public void deleteById(Long id){
        if(!ProductRepository.existsById(id)){
            throw  new ExceptionIdNotFound("Resource not found");
        }
        try{
            ProductRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setPrice(dto.getPrice());
        entity.setImgURL(dto.getImgUrl());

        entity.getCategories().clear();
        for(CategoryDTO catDTO : dto.getCategories()){
            Category category = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(category);
        }

    }

}
