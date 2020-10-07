package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.entities.CategoryEntity;
import bd.monumentor.repository.entities.MonumentEntity;
import bd.monumentor.repository.entities.localization.LocalizationEntity;
import bd.monumentor.repository.idgenerators.MonumentIdGen;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonumentEntityMapper {

  public MonumentEntity map(MonumentJson monumentJson) {
    LocalizationEntity localizationEntity = LocalizationEntityMapper.map(monumentJson);
    String name = monumentJson.getIdentification();

    return MonumentEntity.builder()
        .id(MonumentIdGen.generate(name, localizationEntity.getId()))
        .name(name)
        .additionalDescription(getAndMapAdditionalDescription(monumentJson.getDescription()))
        .creationDate(monumentJson.getDatingOfObj())
        .localization(localizationEntity)
        .categories(mapCategories(monumentJson.getCategories()))
        .build();
  }

  private String getAndMapAdditionalDescription(String additionalDescription) {
    return Optional.ofNullable(additionalDescription)
        .map(string -> string.replaceAll("<.*?>", ""))
        .orElse("");
  }

  private Set<CategoryEntity> mapCategories(List<String> categories) {
    return categories.stream()
        .map(CategoryEntityMapper::map)
        .collect(Collectors.toSet());
  }


}
