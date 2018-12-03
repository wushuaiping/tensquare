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
 * 非好友表
 *
 * @author: wushuaiping
 * @date: 2018/11/30 4:58 PM
 * @description:
 */
@Setter
@Getter
@Entity
@Table(name = "tb_nofriend")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(NoFriend.class)
public class NoFriend implements Serializable {

    private static final long serialVersionUID = 7796323603117631281L;
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
}
