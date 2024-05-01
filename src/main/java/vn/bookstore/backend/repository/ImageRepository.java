package vn.bookstore.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bookstore.backend.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
