package com.project_Spring_treino.dsCatalog.Resources;


import com.project_Spring_treino.dsCatalog.Entities.Category;
import com.project_Spring_treino.dsCatalog.Services.CategoryService;
import com.project_Spring_treino.dsCatalog.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
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

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
        dto = categoryService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update (@PathVariable Long id, @RequestBody CategoryDTO dto){
        dto = categoryService.update(id,dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
