package bd.monumentor.contollers;

import bd.monumentor.api.MonumentsApi;
import bd.monumentor.models.CategoryDto;
import bd.monumentor.models.MonumentDto;
import bd.monumentor.models.MonumentShortInfoDto;
import bd.monumentor.usecase.GetCategories;
import bd.monumentor.usecase.GetDetails;
import bd.monumentor.usecase.GetPropositions;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MonumentsController implements MonumentsApi {

  private GetCategories getCategories;
  private GetDetails getDetails;
  private GetPropositions getPropositions;

  @Override
  public List<CategoryDto> getCategories() {
    return getCategories.execute();
  }

  @Override
  public MonumentDto getDetails(Long id) {
    return getDetails.execute(id);
  }

  @Override
  public List<MonumentShortInfoDto> getPropositions(Double latitude, Double longitude, Integer distance, Integer number,
      List<String> categories) {
    return getPropositions.execute(latitude, longitude, distance, number, categories);
  }
}
