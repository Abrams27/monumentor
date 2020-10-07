package bd.monumentor.models;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonumentDto {

  private String name;
  private String additionalDescription;
  private String creationDate;
  private LocalizationDto localization;
  private List<PhotoDto> photos;

}
