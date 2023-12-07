package pl.coderslab.charity.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();
}