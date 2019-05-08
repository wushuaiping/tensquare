package io.wooo.tensquare.user.service;

import io.wooo.tensquare.user.entity.CustomField;
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
public class CustomFieldService {

    private CustomFieldRepository customFieldRepository;

    public void insert(CustomField customField){
        customFieldRepository.save(customField);
    }

    public List<CustomField> findByCustomerId(Long customerId){
        return customFieldRepository.findByCustomerId(customerId);
    }
}
