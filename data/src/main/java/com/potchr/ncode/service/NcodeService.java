package com.potchr.ncode.service;

import com.potchr.ncode.entity.Ncode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import javax.xml.bind.JAXBException;
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
 * <p>创建日期：2019/8/22 14:30</p>
 */
public interface NcodeService
{
	List<Ncode> loadNcodes();
}
