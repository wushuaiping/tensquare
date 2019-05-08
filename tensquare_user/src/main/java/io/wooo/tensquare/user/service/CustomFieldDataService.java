package io.wooo.tensquare.user.service;

import io.wooo.tensquare.user.entity.CustomField;
import io.wooo.tensquare.user.entity.CustomFieldData;
import io.wooo.tensquare.user.repository.CustomFieldDataRepository;
import io.wooo.tensquare.user.repository.CustomFieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wushuaiping
 * @date 2019/4/18 16:32
 */
@Service
@AllArgsConstructor
public class CustomFieldDataService {

    private CustomFieldDataRepository customFieldDataRepository;

    public void insert(CustomFieldData customFieldData){
        customFieldDataRepository.save(customFieldData);
    }

    public List<CustomFieldData> findData(Long customerId){
        return customFieldDataRepository.findByCustomerId(customerId);
    }

}
