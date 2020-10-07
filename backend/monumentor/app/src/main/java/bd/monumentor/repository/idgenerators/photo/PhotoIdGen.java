package bd.monumentor.repository.idgenerators.photo;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PhotoIdGen {

  private Long urlId = 0L;

  private Map<String, Long> idMap = new HashMap<>();

  public Long generate(String url) {
    idMap.putIfAbsent(url, urlId++);

    return idMap.get(url);
  }

}
