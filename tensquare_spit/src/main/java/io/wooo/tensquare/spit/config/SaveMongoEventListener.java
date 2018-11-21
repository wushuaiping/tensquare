package io.wooo.tensquare.spit.config;

import io.wooo.tensquare.spit.annotation.MongoGeneratedId;
import io.wooo.tensquare.spit.config.model.SequenceId;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @author: wushuaiping
 * @date: 2018/9/20 上午11:13
 * @description:
 */
@Component
@AllArgsConstructor
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {

    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        if (event != null) {
            Object source = event.getSource();
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                ReflectionUtils.makeAccessible(field);
                // 判断是否需要自增1，如果该id没有值并且值不为0，则可以自增1
                if (field.isAnnotationPresent(MongoGeneratedId.class)) {
                    Class<?> type = field.getType();
                    if (type.isAssignableFrom(Long.class)) {
                        final Object cannotTypeId = field.get(source);
                        Long id = (Long) cannotTypeId;
                        if (id == null || id == 0L) {
                            // 取得MongoDB集合名字，然后设置该集合的自增id
                            field.set(source, getNextId(event.getCollectionName()));
                        }
                    }
                }
            });
        }
    }

    /**
     * 获取下一个ID
     *
     * @param collName
     * @return
     */
    private Long getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        // 每次自增1
        update.inc("seqId", 1);
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.upsert(true);
        findAndModifyOptions.returnNew(true);
        SequenceId sequenceId = mongoTemplate.findAndModify(query, update, findAndModifyOptions, SequenceId.class);
        return sequenceId.getSeqId();
    }
}
