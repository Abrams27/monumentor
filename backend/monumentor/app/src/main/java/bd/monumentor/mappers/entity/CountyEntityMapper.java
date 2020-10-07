package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.entities.localization.CountyEntity;
import bd.monumentor.repository.entities.localization.VoivodeshipEntity;
import bd.monumentor.repository.idgenerators.localization.CountyIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountyEntityMapper {

  public CountyEntity map(MonumentJson monumentJson) {
    VoivodeshipEntity voivodeshipEntity = VoivodeshipEntityMapper.map(monumentJson);
    String name = monumentJson.getDistrictName();

    return CountyEntity.builder()
        .id(CountyIdGen.generate(name, voivodeshipEntity.getId()))
        .name(name)
        .voivodeship(voivodeshipEntity)
        .build();
  }
}
