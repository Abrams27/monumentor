package bd.monumentor.usecase;

import bd.monumentor.mappers.dto.MonumentDtoMapper;
import bd.monumentor.models.MonumentDto;
import bd.monumentor.repository.MonumentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetDetails {

  private MonumentsRepository monumentsRepository;

  public MonumentDto execute(Long id) {
    return monumentsRepository
        .findById(id)
        .map(MonumentDtoMapper::map)
        .orElseThrow();
  }

}
