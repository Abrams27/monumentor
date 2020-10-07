package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.PhotoJson;
import bd.monumentor.repository.entities.photo.AuthorEntity;
import bd.monumentor.repository.idgenerators.photo.AuthorIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorEntityMapper {

  public AuthorEntity map(PhotoJson photoJson) {
    String name = photoJson.getAuthor();

    return AuthorEntity.builder()
        .id(AuthorIdGen.generate(name))
        .name(name)
        .build();
  }
}
