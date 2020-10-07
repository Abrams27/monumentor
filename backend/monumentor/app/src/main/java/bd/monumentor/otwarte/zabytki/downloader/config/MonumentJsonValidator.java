package bd.monumentor.otwarte.zabytki.downloader.config;

import bd.monumentor.otwarte.zabytki.downloader.models.FileJson;
import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.otwarte.zabytki.downloader.models.PhotoJson;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonumentJsonValidator {

  public boolean validate(MonumentJson monumentJson) {

    return monumentJson.getIdentification() != null
        && monumentJson.getDatingOfObj() != null
        && monumentJson.getStreet() != null
        && monumentJson.getLatitude() != null
        && monumentJson.getLongitude() != null
        && monumentJson.getPlaceName() != null
        && monumentJson.getCommuneName() != null
        && monumentJson.getDistrictName() != null
        && monumentJson.getVoivodeshipName() != null
        && monumentJson.getCategories() != null
        && monumentJson.getCategories().size() > 0
        && monumentJson.getPhotos() != null
        && validatePhotos(monumentJson.getPhotos());
  }

  private boolean validatePhotos(List<PhotoJson> photoJsonList) {
    return photoJsonList.stream()
        .allMatch(MonumentJsonValidator::validatePhoto)
        && photoJsonList.size() > 0;
  }

  private boolean validatePhoto(PhotoJson photoJson) {
    return photoJson != null
        && photoJson.getAuthor() != null
        && photoJson.getDateTaken() != null
        && validateFile(photoJson.getFile());
  }

  private boolean validateFile(FileJson fileJson) {
    return fileJson != null
        && fileJson.getUrl() != null;
  }

}
