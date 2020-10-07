package bd.monumentor.otwarte.zabytki.downloader.models;

import java.util.List;
import lombok.Data;

@Data
public class MonumentJson {

  private String identification;
  private String description;

  private List<String> categories;
  private String datingOfObj;

  private String street;
  private Double latitude;
  private Double longitude;

  private List<PhotoJson> photos;

  private String placeName;
  private String communeName;
  private String districtName;
  private String voivodeshipName;

}
