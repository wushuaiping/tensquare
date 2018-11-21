package io.wooo.tensquare.spit.config.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: wushuaiping
 * @date: 2018/9/20 上午11:09
 * @description:
 */
@Document(collection = "sequence")
@Setter
@Getter
public class SequenceId {

    @Id
    private String id;

    /**
     * 自增id
     */
    @Field("seq_id")
    private long seqId;

    /**
     * MongoDB集合名
     */
    @Field("coll_name")
    private String collName;
}
