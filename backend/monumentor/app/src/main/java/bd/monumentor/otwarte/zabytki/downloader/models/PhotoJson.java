package bd.monumentor.otwarte.zabytki.downloader.models;

import lombok.Data;

@Data
public class PhotoJson {

  private String author;
  private String dateTaken;

  private FileJson file;

}
