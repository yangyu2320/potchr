package com.potchr.data;

import com.potchr.data.entity.Area;
import com.potchr.data.user.entity.Wcode;
import com.potchr.data.user.repository.UserRepository;
import com.potchr.data.user.entity.User;
import com.potchr.data.user.repository.WcodeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
@SpringBootApplication
public class DataApplication
{
	public static void main(String[] args)
	{
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DataApplication.class, args);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		User user = new User();
		user.setLoginName("yangyu2320");
		user.setFirstName("杨");
		user.setLastName("堉");
		user.setPassword("123123");
		Area area = new Area();
		area.setCountry("中国");
		area.setProvince("广东");
		area.setCity("广州");
		area.setCounty("增城区");
		user.setArea(area);
		userRepository.save(user);
		List<User> users = userRepository.findAll();
		users.forEach(curUser -> {
			System.out.println(curUser.toString());
		});
		WcodeRepository wcodeRepository = applicationContext.getBean(WcodeRepository.class);
		Wcode wcode = new Wcode();
		wcode.setWname("杨堉");
		wcodeRepository.save(wcode);
		wcodeRepository.findAll().forEach(curWcode -> {
			System.out.println(curWcode);
		});
		applicationContext.close();
	}
}
