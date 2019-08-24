package com.potchr.data.bcode.repository;

import com.potchr.data.bcode.entity.Bcode;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.PersistenceUnit;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/9 16:49</p>
 */
public interface BcodeRepository extends JpaRepository<Bcode, String>
{
}
