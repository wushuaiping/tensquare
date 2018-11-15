package io.woo.tensquare.article.repository;

import io.woo.tensquare.article.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: wushuaiping
 * @date: 2018/11/15 10:49 AM
 * @description:
 */
public interface ChannelRepository extends JpaRepository<Channel, String> {
}
