package com.project_Spring_treino.dsCatalog.Resources;


import com.project_Spring_treino.dsCatalog.Services.CategoryService;
import com.project_Spring_treino.dsCatalog.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResources {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
        public ResponseEntity<List<CategoryDTO>> findAll(){
            List<CategoryDTO> list = categoryService.findAll();
            return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findByID(@PathVariable Long id){
        CategoryDTO obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


}
