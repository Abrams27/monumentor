package bd.monumentor.mappers.dto;

import bd.monumentor.models.AuthorDto;
import bd.monumentor.repository.entities.photo.AuthorEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorDtoMapper {

  public AuthorDto map(AuthorEntity authorEntity) {
    return AuthorDto.builder()
        .name(authorEntity.getName())
        .build();
  }
}
