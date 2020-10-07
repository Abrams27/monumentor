package bd.monumentor.repository.idgenerators.localization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocalizationIdGen {

  private Long addressId = 0L;
  private Long localizationId = 0L;

  private Map<String, Long> nameToLong = new HashMap<>();
  private Map<List<Long>, Long> idMap = new HashMap<>();

  public Long generate(String address, Long localityId) {
    Long nameId = generateAddressId(address);
    List<Long> idList = List.of(nameId, localityId);
    idMap.putIfAbsent(idList, localizationId++);

    return idMap.get(idList);
  }

  private Long generateAddressId(String address) {
    nameToLong.putIfAbsent(address, addressId++);
    return nameToLong.get(address);
  }
}
