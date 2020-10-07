package bd.monumentor.repository;

import bd.monumentor.repository.entities.CategoryEntity;
import bd.monumentor.repository.entities.MonumentEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentsRepository extends JpaRepository<MonumentEntity, Long> {

  List<MonumentEntity> findDistinctByCategoriesIn(Collection<CategoryEntity> categories);

}
