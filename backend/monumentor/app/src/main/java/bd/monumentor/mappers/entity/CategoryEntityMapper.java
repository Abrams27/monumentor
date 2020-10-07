package bd.monumentor.mappers.entity;

import bd.monumentor.repository.entities.CategoryEntity;
import bd.monumentor.repository.idgenerators.CategoryIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryEntityMapper {

  public CategoryEntity map(String name) {
    return CategoryEntity.builder()
        .id(CategoryIdGen.generate(name))
        .name(name)
        .build();
  }
}
