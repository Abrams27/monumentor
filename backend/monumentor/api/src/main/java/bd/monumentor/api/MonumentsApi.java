package bd.monumentor.api;

import bd.monumentor.models.CategoryDto;
import bd.monumentor.models.MonumentDto;
import bd.monumentor.models.MonumentShortInfoDto;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/monumentor")
public interface MonumentsApi {

  @CrossOrigin
  @GetMapping("/categories")
  List<CategoryDto> getCategories();

  @CrossOrigin
  @GetMapping("details/{id}")
  MonumentDto getDetails(@PathVariable Long id);

  @CrossOrigin
  @GetMapping("/propositions")
  List<MonumentShortInfoDto> getPropositions(
      @RequestParam(required = true) Double latitude,
      @RequestParam(required = true) Double longitude,
      @RequestParam(required = false) Integer distance,
      @RequestParam(required = false) Integer number,
      @RequestParam(required = false) List<String> categories);
}
