package bd.monumentor.repository;

import bd.monumentor.repository.entities.CategoryEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  List<CategoryEntity> findAllByNameIn(Collection<String> names);
}
