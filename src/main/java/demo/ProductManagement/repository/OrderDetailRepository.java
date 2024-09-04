package demo.ProductManagement.repository;


import demo.ProductManagement.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity,Integer> {

}
