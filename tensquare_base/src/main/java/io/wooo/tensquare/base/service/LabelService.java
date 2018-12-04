package io.wooo.tensquare.base.service;

import com.querydsl.core.BooleanBuilder;
import io.wooo.tensquare.base.entity.Label;
import io.wooo.tensquare.base.model.LabelSearchRequest;
import io.wooo.tensquare.base.model.filter.LabelFilter;
import io.wooo.tensquare.base.repository.LabelRepository;
import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.common.util.IdWorker;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author: wushuaiping
 * @date: 2018/11/13 7:57 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class LabelService {

    private LabelRepository labelRepository;

    private IdWorker idWorker;

    public Label findById(String id) {
        return labelRepository.findById(id).get();
    }

    public void delete(String id) {
        labelRepository.deleteById(id);
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelRepository.save(label);
    }

    public void update(Label label) {
        labelRepository.save(label);
    }

    public List<Label> findBySearch(LabelSearchRequest searchRequest) {

        final BooleanBuilder builder = LabelFilter.builder()
                .id(searchRequest.getId())
                .count(searchRequest.getCount())
                .labelnameLike(searchRequest.getLabelname())
                .recommend(searchRequest.getRecommend())
                .state(searchRequest.getState())
                .build()
                .toBooleanBuilder();
        return StreamSupport.stream(labelRepository.findAll(builder).spliterator(), true).collect(Collectors.toList());
    }

    public PageResult<Label> findByPage(LabelSearchRequest searchRequest, Integer page, Integer size) {

        final BooleanBuilder builder = LabelFilter.builder()
                .id(searchRequest.getId())
                .count(searchRequest.getCount())
                .labelnameLike(searchRequest.getLabelname())
                .recommend(searchRequest.getRecommend())
                .state(searchRequest.getState())
                .build()
                .toBooleanBuilder();

        Pageable pageable = PageRequest.of(page - 1, size);
        final Page<Label> labelPage = labelRepository.findAll(builder, pageable);
        PageResult<Label> result = new PageResult<Label>();
        result.setRows(labelPage.getContent());
        result.setTotal(labelPage.getTotalElements());
        return result;
    }

    public List<Label> getAll() {
        return labelRepository.findAll();
    }
}
