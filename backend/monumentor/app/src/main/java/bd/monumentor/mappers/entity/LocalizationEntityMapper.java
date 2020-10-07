package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.entities.localization.LocalityEntity;
import bd.monumentor.repository.entities.localization.LocalizationEntity;
import bd.monumentor.repository.idgenerators.localization.LocalizationIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocalizationEntityMapper {

  public LocalizationEntity map(MonumentJson monumentJson) {
    LocalityEntity localityEntity = LocalityEntityMapper.map(monumentJson);
    String address = monumentJson.getStreet();

    return LocalizationEntity.builder()
        .id(LocalizationIdGen.generate(address, localityEntity.getId()))
        .address(address)
        .locality(localityEntity)
        .latitude(monumentJson.getLatitude())
        .longitude(monumentJson.getLongitude())
        .build();
  }
}
