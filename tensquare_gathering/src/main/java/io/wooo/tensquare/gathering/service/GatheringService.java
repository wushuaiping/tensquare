package io.wooo.tensquare.gathering.service;

import io.wooo.tensquare.gathering.entity.Gathering;
import io.wooo.tensquare.gathering.repository.GatheringRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/20 3:49 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class GatheringService {

    private GatheringRepository gatheringRepository;

    public List<Gathering> getAll() {
        return gatheringRepository.findAll();
    }

    @Cacheable(value = "gathering", key = "#id")
    public Gathering getByOne(String id) {
        return gatheringRepository.getOne(id);
    }

    @CacheEvict(value = "gathering", key = "#gathering.id")
    public void save(Gathering gathering) {
        gatheringRepository.save(gathering);
    }

    @CacheEvict(value = "gathering", key = "#id")
    public void deleted(String id) {
        gatheringRepository.deleteById(id);
    }
}
