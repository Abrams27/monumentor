package bd.monumentor.repository.idgenerators;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryIdGen {

  private Long categoryId = 0L;

  private Map<String, Long> idMap = new HashMap<>();

  public Long generate(String category) {
    idMap.putIfAbsent(category, categoryId++);

    return idMap.get(category);
  }


}
