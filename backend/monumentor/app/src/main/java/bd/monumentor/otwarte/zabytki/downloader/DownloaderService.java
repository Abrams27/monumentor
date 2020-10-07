package bd.monumentor.otwarte.zabytki.downloader;

import bd.monumentor.mappers.entity.MonumentEntityMapper;
import bd.monumentor.mappers.entity.PhotoEntityMapper;
import bd.monumentor.otwarte.zabytki.downloader.config.DownloaderProperties;
import bd.monumentor.otwarte.zabytki.downloader.config.MonumentJsonValidator;
import bd.monumentor.otwarte.zabytki.downloader.config.PhotoPrefixUrlProperties;
import bd.monumentor.otwarte.zabytki.downloader.models.MonumentJson;
import bd.monumentor.repository.MonumentsRepository;
import bd.monumentor.repository.PhotosRepository;
import bd.monumentor.repository.entities.MonumentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DownloaderService {

  private MonumentsRepository monumentsRepository;
  private PhotosRepository photosRepository;
  private DownloaderProperties properties;
  private PhotoPrefixUrlProperties photoPrefixUrlProperties;

  @PostConstruct
  @SneakyThrows
  void downloadAndSave() {
    log.info("Downloading jsons from {}", properties.getUrl());

    ZipInputStream zipIn = connect();

    for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn.getNextEntry()) {
      if (!entry.isDirectory()) {
        mapAndSave(zipIn);
      }

      zipIn.closeEntry();
    }

    zipIn.close();

    log.info("Downloading finished");
  }

  @SneakyThrows
  private ZipInputStream connect() {
    URL url = new URL(properties.getUrl());

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    return new ZipInputStream(connection.getInputStream());
  }

  @SneakyThrows
  private void mapAndSave(ZipInputStream zipIn) {
    String json = IOUtils.toString(zipIn, "UTF-8");
    MonumentJson monumentJson = mapJson(json);


    Optional.ofNullable(monumentJson)
        .filter(MonumentJsonValidator::validate)
        .ifPresent(this::mapAndSaveMonumentEntityAndAddPhotos);
  }

  private void mapAndSaveMonumentEntityAndAddPhotos(MonumentJson monumentJson) {
    MonumentEntity monumentEntity = MonumentEntityMapper.map(monumentJson);

    monumentsRepository.save(monumentEntity);

    monumentJson
        .getPhotos().stream()
        .map(json -> PhotoEntityMapper.map(json, monumentEntity, photoPrefixUrlProperties.getUrl()))
        .forEach(photosRepository::save);
  }

  @SneakyThrows
  private MonumentJson mapJson(String json) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    return objectMapper.readValue(json, MonumentJson.class);
  }

}
