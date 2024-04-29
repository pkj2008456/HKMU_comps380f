package hkmu.comps380f.project.dao;

import hkmu.comps380f.project.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    public List<UserOrder> findAllByUserIdOrderByCreateTimeDesc(String username);
    public UserOrder findByUserIdAndIdOrderByCreateTime(String username, long id);
}
