package com.potchr.ncode.service.impl;

import com.potchr.ncode.entity.Ncode;
import com.potchr.ncode.entity.Ncodes;
import com.potchr.ncode.service.NcodeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
@Order(Ordered.LOWEST_PRECEDENCE - 2)
@ConditionalOnMissingBean(NcodeService.class)
public class YNcodeServiceImpl implements NcodeService {
    @Override
    public List<Ncode> loadNcodes() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ncodes.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Ncodes ncodes = null;
            URL resource = getClass().getClassLoader().getResource("Ncodes.xml");
            List<Ncodes> a = new ArrayList();
            if (resource != null) {
                for(int i=0 ; i < 100; i ++) {
                    long l = Runtime.getRuntime().freeMemory();
                    long start = System.currentTimeMillis();
//                Thread.sleep(3000);
                    ncodes = (Ncodes) unmarshaller.unmarshal(new File(resource.getFile()));
                    System.out.println(System.currentTimeMillis() - start);
                    System.out.println(Runtime.getRuntime().freeMemory() - l);
                    System.out.println(ncodes.getNcodes().size());
                    a.add(ncodes);
//                    return ncodes.getNcodes();
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public static void main(String[] args) {
		System.out.println(new YNcodeServiceImpl().loadNcodes());
	}
}
