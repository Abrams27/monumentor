package bd.monumentor.repository.idgenerators.localization;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VoivodeshipIdGen {

  private Long voivodeshipId = 0L;

  private Map<String, Long> nameToLong = new HashMap<>();

  public Long generate(String name) {
    nameToLong.putIfAbsent(name, voivodeshipId++);

    return nameToLong.get(name);
  }
}
