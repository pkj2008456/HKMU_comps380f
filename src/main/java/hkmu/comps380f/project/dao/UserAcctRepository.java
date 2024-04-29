package hkmu.comps380f.project.dao;

import hkmu.comps380f.project.model.UserAcct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAcctRepository extends JpaRepository<UserAcct, String> {

    @Modifying
    @Query(value = "DELETE FROM USER_FAV_REF WHERE USER_ID = :id", nativeQuery = true)
    int findAllFav(@Param("id") String id);
}
