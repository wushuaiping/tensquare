package io.wooo.tensquare.recruit.service;

import io.wooo.tensquare.recruit.entity.Enterprise;
import io.wooo.tensquare.recruit.repository.EnterpriseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/14 3:35 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class EnterpriseService {

    private EnterpriseRepository enterpriseRepository;

    public List<Enterprise> getEnterpriseByIshot(String ishot){
        return enterpriseRepository.findByIshot(ishot);
    }
}
