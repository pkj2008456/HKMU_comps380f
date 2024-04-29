package hkmu.comps380f.project.dao;

import hkmu.comps380f.project.model.BookComment;
import hkmu.comps380f.project.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
    public List<BookComment> findAllByBookIdOrderByCreateTimeAsc(long id);

    public List<BookComment> findAllByUserIdOrderByCreateTimeAsc(String username);

    public List<BookComment> findAllByBookIdAndUserIdOrderByCreateTimeAsc(long id, String username);

    public List<BookComment> deleteAllByBook(BookInfo book);
}
