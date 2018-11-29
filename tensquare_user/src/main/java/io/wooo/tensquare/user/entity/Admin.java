package io.wooo.tensquare.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.wooo.tensquare.common.util.IdWorker;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: wushuaiping
 * @date: 2018/11/28 3:17 PM
 * @description:
 */
@Entity
@Table(name = "tb_admin")
@Setter
@Getter
public class Admin implements Serializable {

    private static final long serialVersionUID = 1198288471617604066L;

    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 状态
     */
    private String state;

    /**
     * 注册时间
     */
    private LocalDateTime regDate;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginDate;

    public void initAdmin(Admin admin) {
        if (admin == null) {
            admin = new Admin();
        }
        final IdWorker instance = IdWorker.getInstance();
        admin.setId(instance.nextIdStringValue());
        final LocalDateTime now = LocalDateTime.now();
        admin.setRegDate(now);
        admin.setLastLoginDate(now);
        admin.setState("1");
    }
}
