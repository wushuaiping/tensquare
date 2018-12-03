package io.wooo.tensquare.friend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 好友
 *
 * @author: wushuaiping
 * @date: 2018/11/30 4:56 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_friend")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Friend.class)
public class Friend implements Serializable {

    /**
     * 用户id
     */
    @Id
    private String userid;

    /**
     * 好友id
     */
    @Id
    private String friendid;

    /**
     * 是否互相喜欢 0：单向喜欢。1：互相喜欢
     */
    private String islike;
}
