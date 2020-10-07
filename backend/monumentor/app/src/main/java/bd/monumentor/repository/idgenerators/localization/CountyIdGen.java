package bd.monumentor.repository.idgenerators.localization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountyIdGen {

  private Long nameId = 0L;
  private Long countyId = 0L;

  private Map<String, Long> nameToLong = new HashMap<>();
  private Map<List<Long>, Long> idMap = new HashMap<>();

  public Long generate(String name, Long voivodeshipId) {
    Long nameId = generateNameId(name);
    List<Long> idList = List.of(nameId, voivodeshipId);
    idMap.putIfAbsent(idList, countyId++);

    return idMap.get(idList);
  }

  private Long generateNameId(String name) {
    nameToLong.putIfAbsent(name, nameId++);
    return nameToLong.get(name);
  }
}
