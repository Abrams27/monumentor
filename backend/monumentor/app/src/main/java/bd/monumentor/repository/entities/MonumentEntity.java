package bd.monumentor.repository.entities;

import bd.monumentor.repository.entities.localization.LocalizationEntity;
import bd.monumentor.repository.entities.photo.PhotoEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Entity
@Table(name = "MONUMENT")
public class MonumentEntity {

  @Id
  private Long id;

  private String name;
  private String additionalDescription;
  private String creationDate;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "localization", nullable = false)
  private LocalizationEntity localization;

  @OneToMany(mappedBy = "monument")
  private Set<PhotoEntity> photos = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "CATEGORY_TO_MONUMENT",
      joinColumns = @JoinColumn(name = "monument_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<CategoryEntity> categories = new HashSet<>();
}
