package com.potchr.data.user.repository;

import com.potchr.data.user.entity.ErpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.PersistenceUnit;
import javax.persistence.QueryHint;
import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/7/11 14:56</p>
 */
public interface UserRepository extends JpaRepository<ErpUser,Integer>
{
	@Query("FROM ErpUser")
	public List<ErpUser> queryCache();
}
