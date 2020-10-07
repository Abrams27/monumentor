package bd.monumentor.mappers.dto;

import bd.monumentor.models.MonumentDto;
import bd.monumentor.models.PhotoDto;
import bd.monumentor.repository.entities.MonumentEntity;
import bd.monumentor.repository.entities.photo.PhotoEntity;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonumentDtoMapper {

  public MonumentDto map(MonumentEntity monumentEntity) {
    return MonumentDto.builder()
        .name(monumentEntity.getName())
        .additionalDescription(monumentEntity.getAdditionalDescription())
        .creationDate(monumentEntity.getCreationDate())
        .localization(LocalizationDtoMapper.map(monumentEntity.getLocalization()))
        .photos(mapPhotos(monumentEntity.getPhotos()))
        .build();
  }

  private List<PhotoDto> mapPhotos(Set<PhotoEntity> photos) {
    return photos.stream()
        .map(PhotoDtoMapper::map)
        .collect(Collectors.toList());
  }
}
