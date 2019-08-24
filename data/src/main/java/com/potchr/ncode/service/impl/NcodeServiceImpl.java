package com.potchr.ncode.service.impl;

import com.potchr.ncode.entity.Ncode;
import com.potchr.ncode.entity.Ncodes;
import com.potchr.ncode.service.NcodeService;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Field;
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
 * <p>创建日期：2019/8/22 14:31</p>
 */
@Service
public class NcodeServiceImpl implements NcodeService
{
	@Override
	public List<Ncode> loadNcodes()
	{
		try
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(Ncodes.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Ncodes ncodes = null;
			File file = new File("D:\\potchr\\data\\src\\main\\resources\\Ncodes.xml");
			ncodes = (Ncodes) unmarshaller.unmarshal(file);
			System.out.println(ncodes.getNcodes().size());
			return ncodes.getNcodes();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
