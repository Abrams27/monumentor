package bd.monumentor.repository.idgenerators.photo;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorIdGen {

  private Long nameId = 0L;

  private Map<String, Long> nameToLong = new HashMap<>();

  public Long generate(String name) {
    nameToLong.putIfAbsent(name, nameId++);

    return nameToLong.get(name);
  }

}
