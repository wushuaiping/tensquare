package io.wooo.tensquare.spit.service;

import io.wooo.tensquare.common.entity.PageResult;
import io.wooo.tensquare.spit.document.Spit;
import io.wooo.tensquare.spit.repository.SpitRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: wushuaiping
 * @date: 2018/11/21 1:51 PM
 * @description:
 */
@Service
@AllArgsConstructor
public class SpitService {

    private SpitRepository spitRepository;

    private MongoTemplate mongoTemplate;

    public List<Spit> getAll() {
        return spitRepository.findAll();
    }

    @Transactional
    public void save(Spit spit) {
        // 后期需要将user信息获取到设置到spit里面
        spit.setPublishtime(LocalDateTime.now());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        // 如果当前节点有父节点，则需要对父节点的吐槽回复数+1
        final Spit parent = searchParentNode(spit);
        parent.setThumbup(parent.getComment() + 1);
        spitRepository.save(parent);
        spitRepository.save(spit);
    }

    /**
     * 这里可以优化一波，在Spit表中 添加一个字段，
     * 叫做顶级父节点字段，我只需要拿这个字段就能知道最顶层父节点了，就不需要去递归然后数据库找了。效率提升upup！
     *
     * @param spit
     * @return
     */
    public Spit searchParentNode(Spit spit) {
        if (spit != null && StringUtils.isNotBlank(spit.getParentid())) {
            Spit parent = getById(spit.getParentid());
            searchParentNode(parent);
        }
        return spit;
    }

    public Spit getById(String id) {
        return spitRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleted(String id) {
        spitRepository.deleteById(id);
    }

    public PageResult<Spit> getByParentId(String parentId, Pageable pageable) {
        final Page<Spit> spitPage = spitRepository.findByParentid(parentId, pageable);
        return new PageResult<Spit>(spitPage.getTotalElements(), spitPage.getContent());
    }

    @Transactional
    public Integer thumup(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        // 每次自增1
        update.inc("thumbup", 1);
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.upsert(true);
        findAndModifyOptions.returnNew(true);
        Spit spit = mongoTemplate.findAndModify(query, update, findAndModifyOptions, Spit.class);
        return spit.getThumbup();
    }
}
