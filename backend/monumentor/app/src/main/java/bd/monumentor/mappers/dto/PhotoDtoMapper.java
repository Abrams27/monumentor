package bd.monumentor.mappers.dto;

import bd.monumentor.models.PhotoDto;
import bd.monumentor.repository.entities.photo.PhotoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PhotoDtoMapper {

  public PhotoDto map(PhotoEntity photoEntity) {
    return PhotoDto.builder()
        .url(photoEntity.getUrl())
        .creationDate(photoEntity.getCreationDate())
        .author(AuthorDtoMapper.map(photoEntity.getAuthor()))
        .build();
  }
}
