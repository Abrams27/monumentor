package bd.monumentor.usecase;

import bd.monumentor.mappers.dto.MonumentShortInfoDtoMapper;
import bd.monumentor.models.MonumentShortInfoDto;
import bd.monumentor.services.MonumentsPropositionsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetPropositions {

  private MonumentsPropositionsService monumentsPropositionsService;

  public List<MonumentShortInfoDto> execute(Double latitude, Double longitude, Integer distance, Integer number,
      List<String> categories) {

    return monumentsPropositionsService
        .getPropositions(latitude, longitude, Optional.ofNullable(distance), Optional.ofNullable(number),
            Optional.ofNullable(categories)).stream()
        .map(MonumentShortInfoDtoMapper::map)
        .collect(Collectors.toList());
  }

}
