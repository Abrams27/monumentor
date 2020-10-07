package bd.monumentor.mappers.dto;

import bd.monumentor.models.CategoryDto;
import bd.monumentor.repository.entities.CategoryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryDtoMapper {

  public CategoryDto map(CategoryEntity categoryEntity) {
    return CategoryDto.builder()
        .name(categoryEntity.getName())
        .build();
  }
}
