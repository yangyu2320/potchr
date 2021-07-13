package com.potchr.trade.order.sal.repository;

import com.potchr.trade.order.sal.entity.SalOrder;
import com.potchr.trade.order.sal.entity.SalOrderProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author yangyu
 * @since 2021/7/7
 */
@NoRepositoryBean
public interface SalOrderRepository<O extends SalOrder> extends JpaRepository<O, String> {
}
