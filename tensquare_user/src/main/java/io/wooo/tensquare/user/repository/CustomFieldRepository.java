package io.wooo.tensquare.user.repository;

import io.wooo.tensquare.user.entity.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wushuaiping
 * @date 2019/4/18 16:31
 */
@Repository
public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {

    List<CustomField> findByCustomerId(Long customerId);

}
