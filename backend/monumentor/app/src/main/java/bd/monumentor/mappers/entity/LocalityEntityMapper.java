package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.entities.localization.BoroughEntity;
import bd.monumentor.repository.entities.localization.LocalityEntity;
import bd.monumentor.repository.idgenerators.localization.LocalityIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocalityEntityMapper {

  public LocalityEntity map(MonumentJson monumentJson) {
    BoroughEntity boroughEntity = BoroughEntityMapper.map(monumentJson);
    String name = monumentJson.getPlaceName();

    return LocalityEntity.builder()
        .id(LocalityIdGen.generate(name, boroughEntity.getId()))
        .name(name)
        .borough(boroughEntity)
        .build();
  }
}
