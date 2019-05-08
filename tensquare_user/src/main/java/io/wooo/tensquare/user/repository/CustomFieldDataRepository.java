package io.wooo.tensquare.user.repository;

import io.wooo.tensquare.user.entity.CustomFieldData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wushuaiping
 * @date 2019/4/18 16:31
 */
@Repository
public interface CustomFieldDataRepository extends JpaRepository<CustomFieldData, Long> {

    List<CustomFieldData> findByCustomerId(Long customerId);

}
