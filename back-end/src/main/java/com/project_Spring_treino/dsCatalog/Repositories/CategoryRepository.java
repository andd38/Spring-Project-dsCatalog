package com.project_Spring_treino.dsCatalog.Repositories;

import com.project_Spring_treino.dsCatalog.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
