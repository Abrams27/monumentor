package bd.monumentor.models;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonumentShortInfoDto {

  private Long id;

  private String name;
  private String creationDate;
  private List<String> categories;

  private String locality;

  private Double latitude;
  private Double longitude;

}
