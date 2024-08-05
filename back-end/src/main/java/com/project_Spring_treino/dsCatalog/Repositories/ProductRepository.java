package com.project_Spring_treino.dsCatalog.Repositories;

import com.project_Spring_treino.dsCatalog.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
