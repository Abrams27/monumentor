package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.entities.localization.BoroughEntity;
import bd.monumentor.repository.entities.localization.CountyEntity;
import bd.monumentor.repository.idgenerators.localization.BoroughIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoroughEntityMapper {

  public BoroughEntity map(MonumentJson monumentJson) {
    CountyEntity countyEntity = CountyEntityMapper.map(monumentJson);
    String name = monumentJson.getCommuneName();

    return BoroughEntity.builder()
        .id(BoroughIdGen.generate(name, countyEntity.getId()))
        .name(name)
        .county(countyEntity)
        .build();
  }

}
