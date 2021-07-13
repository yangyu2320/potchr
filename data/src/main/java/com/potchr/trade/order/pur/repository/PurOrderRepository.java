package com.potchr.trade.order.pur.repository;

import com.potchr.trade.order.pur.entity.PurOrder;
import com.potchr.trade.order.pur.entity.PurOrderProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yangyu
 * @since 2021/7/7
 */
public interface PurOrderRepository extends JpaRepository<PurOrder<PurOrderProductItem>, String> {
}
