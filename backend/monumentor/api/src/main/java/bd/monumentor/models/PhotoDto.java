package bd.monumentor.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDto {

  private String creationDate;
  private String url;
  private AuthorDto author;
}
