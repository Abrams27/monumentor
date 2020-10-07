package bd.monumentor.repository;

import bd.monumentor.repository.entities.photo.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<PhotoEntity, Long> {

}
