package bd.monumentor.mappers.entity;

import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.entities.localization.VoivodeshipEntity;
import bd.monumentor.repository.idgenerators.localization.VoivodeshipIdGen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VoivodeshipEntityMapper {

  public VoivodeshipEntity map(MonumentJson monumentJson) {
    String name = monumentJson.getVoivodeshipName();

    return VoivodeshipEntity.builder()
        .id(VoivodeshipIdGen.generate(name))
        .name(name)
        .build();
  }
}
