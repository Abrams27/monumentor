package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.FileJson;
import bd.monumentor.otwarte.zabytki.downloader.models.PhotoJson;
import bd.monumentor.repository.entities.MonumentEntity;
import bd.monumentor.repository.entities.photo.PhotoEntity;
import bd.monumentor.repository.idgenerators.photo.PhotoIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PhotoEntityMapper {

  public PhotoEntity map(PhotoJson photoJson, MonumentEntity monumentEntity, String photoUrlPrefix) {
    FileJson file = photoJson.getFile();

    return PhotoEntity.builder()
        .id(PhotoIdGen.generate(file.getUrl()))
        .creationDate(photoJson.getDateTaken())
        .author(AuthorEntityMapper.map(photoJson))
        .url(photoUrlPrefix + file.getUrl())
        .monument(monumentEntity)
        .build();
  }
}
