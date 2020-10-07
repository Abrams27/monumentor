package bd.monumentor.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalizationDto {

  private String address;

  private Double latitude;
  private Double longitude;

  private String locality;
  private String borough;
  private String county;
  private String voivodeship;

}
