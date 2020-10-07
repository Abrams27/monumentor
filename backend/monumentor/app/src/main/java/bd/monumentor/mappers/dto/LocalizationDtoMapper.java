package bd.monumentor.mappers.dto;

import bd.monumentor.models.LocalizationDto;
import bd.monumentor.repository.entities.localization.BoroughEntity;
import bd.monumentor.repository.entities.localization.CountyEntity;
import bd.monumentor.repository.entities.localization.LocalityEntity;
import bd.monumentor.repository.entities.localization.LocalizationEntity;
import bd.monumentor.repository.entities.localization.VoivodeshipEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocalizationDtoMapper {

  public LocalizationDto map(LocalizationEntity localizationEntity) {
    LocalityEntity localityEntity = localizationEntity.getLocality();
    BoroughEntity boroughEntity = localityEntity.getBorough();
    CountyEntity countyEntity = boroughEntity.getCounty();
    VoivodeshipEntity voivodeshipEntity = countyEntity.getVoivodeship();

    return LocalizationDto.builder()
        .address(localizationEntity.getAddress())
        .latitude(localizationEntity.getLatitude())
        .longitude(localizationEntity.getLongitude())
        .locality(mapLocality(localityEntity))
        .borough(mapBorough(boroughEntity))
        .county(mapCounty(countyEntity))
        .voivodeship(mapVoivodeship(voivodeshipEntity))
        .build();
  }

  private String mapLocality(LocalityEntity localityEntity) {
    return localityEntity.getName();
  }

  private String mapBorough(BoroughEntity boroughEntity) {
    return boroughEntity.getName();
  }

  private String mapCounty(CountyEntity countyEntity) {
    return countyEntity.getName();
  }

  private String mapVoivodeship(VoivodeshipEntity voivodeshipEntity) {
    return voivodeshipEntity.getName().toString();
  }
}
