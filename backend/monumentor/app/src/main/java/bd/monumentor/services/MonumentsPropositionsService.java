package bd.monumentor.services;

import bd.monumentor.repository.CategoryRepository;
import bd.monumentor.repository.MonumentsRepository;
import bd.monumentor.repository.entities.CategoryEntity;
import bd.monumentor.repository.entities.MonumentEntity;
import bd.monumentor.utils.EarthDistanceResolver;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MonumentsPropositionsService {

  private final static int MAX_ELEMENTS = 1000;

  private CategoryRepository categoryRepository;
  private MonumentsRepository monumentsRepository;

  public List<MonumentEntity> getPropositions(Double latitude, Double longitude, Optional<Integer> distance,
      Optional<Integer> number, Optional<List<String>> categories) {

    List<CategoryEntity> categoryEntities =
        categoryRepository.findAllByNameIn(categories.orElseGet(List::of));

    return monumentsRepository
        .findDistinctByCategoriesIn(categoryEntities).stream()
        .filter(monumentEntity -> validateDistance(latitude, longitude, monumentEntity, distance))
        .limit(number.orElse(MAX_ELEMENTS))
        .collect(Collectors.toList());
  }


  private Boolean validateDistance(Double latitude, Double longitude, MonumentEntity monumentEntity,
      Optional<Integer> distance) {

    double monumentLatitude = monumentEntity.getLocalization().getLatitude();
    double monumentLongitude = monumentEntity.getLocalization().getLongitude();

    return distance
        .map(dist -> EarthDistanceResolver.distance(longitude, latitude, monumentLongitude, monumentLatitude) <= dist)
        .orElse(true);
  }


}
