package com.example.BalantaTaller1.repository.prod;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BalantaTaller1.model.prod.Productcategory;


public interface ProductcategoryRepository extends JpaRepository<Productcategory, Integer>{

	//Optional<Productcategory> save(Optional<Productcategory> pc);

}
