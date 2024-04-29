package hkmu.comps380f.project.dao;

import hkmu.comps380f.project.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
    public List<BookInfo> findAllByAvailability(boolean availability);

    @Modifying
    @Query(value = "DELETE FROM USER_FAV_REF WHERE BOOK_ID = :id", nativeQuery = true)
    int findAllFav(@Param("id") long id);

    @Modifying
    @Query(value = "DELETE FROM BOOK_COVER_PHOTO WHERE BOOK_ID = :id", nativeQuery = true)
    int findAllPhoto(@Param("id") long id);

    @Modifying
    @Query(value = "DELETE FROM BOOK_INFO WHERE ID = :id", nativeQuery = true)
    int findAllBook(@Param("id") long id);
}
