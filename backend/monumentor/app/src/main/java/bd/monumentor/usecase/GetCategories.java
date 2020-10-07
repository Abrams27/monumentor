package bd.monumentor.usecase;

import bd.monumentor.mappers.dto.CategoryDtoMapper;
import bd.monumentor.models.CategoryDto;
import bd.monumentor.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetCategories {

  private CategoryRepository categoryRepository;

  public List<CategoryDto> execute() {
    return categoryRepository
        .findAll().stream()
        .map(CategoryDtoMapper::map)
        .collect(Collectors.toList());
  }


}
