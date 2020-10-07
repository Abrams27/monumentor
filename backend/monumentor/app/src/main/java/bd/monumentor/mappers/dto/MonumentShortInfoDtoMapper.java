package bd.monumentor.mappers.dto;

import bd.monumentor.models.MonumentShortInfoDto;
import bd.monumentor.repository.entities.CategoryEntity;
import bd.monumentor.repository.entities.MonumentEntity;
import bd.monumentor.repository.entities.localization.LocalityEntity;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonumentShortInfoDtoMapper {

  public MonumentShortInfoDto map(MonumentEntity monumentEntity) {
    return MonumentShortInfoDto.builder()
        .id(monumentEntity.getId())
        .name(monumentEntity.getName())
        .creationDate(monumentEntity.getCreationDate())
        .categories(mapCatgories(monumentEntity.getCategories()))
        .locality(mapLocality(monumentEntity.getLocalization().getLocality()))
        .latitude(monumentEntity.getLocalization().getLatitude())
        .longitude(monumentEntity.getLocalization().getLongitude())
        .build();
  }

  private List<String> mapCatgories(Set<CategoryEntity> categories) {
    return categories.stream()
        .map(MonumentShortInfoDtoMapper::mapCategory)
        .collect(Collectors.toList());
  }

  private String mapCategory(CategoryEntity categoryEntity) {
    return categoryEntity.getName();
  }

  private String mapLocality(LocalityEntity localityEntity) {
    return localityEntity.getName();
  }
}
