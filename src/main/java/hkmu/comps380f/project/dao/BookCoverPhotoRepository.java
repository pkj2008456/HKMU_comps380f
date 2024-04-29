package hkmu.comps380f.project.dao;

import hkmu.comps380f.project.model.BookCoverPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCoverPhotoRepository extends JpaRepository<BookCoverPhoto, UUID> {
}
