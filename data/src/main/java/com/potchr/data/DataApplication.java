package com.potchr.data;

import com.potchr.data.ccode.entity.ImportedCustomer;
import com.potchr.data.ccode.entity.PreciseCustomer;
import com.potchr.data.ccode.repository.CustomerRepository;
import com.potchr.data.ccode.service.CustomerImportService;
import com.potchr.data.user.entity.ErpUser;
import com.potchr.data.user.service.UserService;
import com.potchr.ncode.entity.Ncode;
import com.potchr.ncode.service.NcodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@EnableCaching
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication(scanBasePackages = { "com.potchr.data", "com.potchr.ncode" })
//@EnableDiscoveryClient
//@EnableFeignClients
public class DataApplication
{
	public static void main(String[] args)
	{
		SpringApplication springApplication = new SpringApplicationBuilder(DataApplication.class).bannerMode(Banner.Mode.OFF).build();
		ConfigurableApplicationContext applicationContext = springApplication.run(args);
		Set<String> citySet = new TreeSet<>();
		//		for (int i = 1; i < 16; i++)
		testNcode(applicationContext, citySet);
		System.out.println(citySet);
		applicationContext.close();
	}

	private static void testNcode(ApplicationContext applicationContext,Set<String> citySet)
	{
		Logger logger = LoggerFactory.getLogger("Ncode转换");
		NcodeService ncodeService = applicationContext.getBean(NcodeService.class);
		List<Ncode> ncodes = ncodeService.loadNcodes();
		ncodes.sort((o1, o2) -> {
			return o1.getNname().compareTo(o2.getNname());
		});
		Ncode[] ncodeArr = ncodes.toArray(new Ncode[0]);
		JdbcTemplate jdbcTemplate = applicationContext.getBean("customerJdbcTemplate", JdbcTemplate.class);
		List<PreciseCustomer> importedCustomers = jdbcTemplate.query("SELECT * FROM precise_customer WHERE ncode IS NULL", new BeanPropertyRowMapper<PreciseCustomer>(PreciseCustomer.class));
		List<Map<String,Object>> parameters = new ArrayList<>();
		importedCustomers.stream().forEach(importedCustomer -> {
			String provicne = StringUtils.stripToNull(importedCustomer.getProvince());
			String city = StringUtils.stripToNull(importedCustomer.getCity());
			if (provicne == null && city == null)
			{
				return;
			}
			Ncode ncode = new Ncode();
			ncode.setNname(city != null ? city : provicne);
			int index = Arrays.binarySearch(ncodeArr, ncode, (ncode1, ncode2) -> {
				return ncode1.getNname().compareTo(ncode2.getNname());
			});
			if (index < 0)
			{
				if (provicne != null)
				{
					ncode = new Ncode();
					ncode.setNname(provicne);
					index = Arrays.binarySearch(ncodeArr, ncode, (ncode1, ncode2) -> {
						return ncode1.getNname().compareTo(ncode2.getNname());
					});
				}
			}
			if (index < 0)
			{
				citySet.add(ncode.getNname());
			} else
			{
				String ncodeStr = ncodeArr[index].getNcode();
				if (ncodeStr == null)
				{
					citySet.add(ncode.getNname());
					return;
				}
				Map<String,Object> parameter = new HashMap<>();
				parameter.put("customer_id", importedCustomer.getCustomerId());
				parameter.put("ncode", ncodeStr);
				parameters.add(parameter);
			}
		});
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = applicationContext.getBean("customerNamedParameterJdbcTemplate", NamedParameterJdbcTemplate.class);
		logger.info("共计:" + parameters.size());
		int count = 0;
		List<Map<String,Object>> updateParameters = new ArrayList<>();
		for (Map<String,Object> parameter : parameters)
		{
			updateParameters.add(parameter);
			if (updateParameters.size() == 2500)
			{
				count += 2500;
				namedParameterJdbcTemplate.batchUpdate("UPDATE precise_customer SET ncode=:ncode WHERE customer_id=:customer_id", updateParameters.toArray(new HashMap[0]));
				updateParameters.clear();
				logger.info("已完成:" + count + "/" + parameters.size());
			}
		}
		namedParameterJdbcTemplate.batchUpdate("UPDATE precise_customer SET ncode=:ncode WHERE customer_id=:customer_id", updateParameters.toArray(new HashMap[0]));
		logger.info("已完成:" + (count + updateParameters.size()) + "/" + parameters.size());
//		logger.info("第" + i + "张表完成！");
	}

	private static void addCcode(ApplicationContext applicationContext) throws ParseException
	{
		ImportedCustomer importedCustomer = new ImportedCustomer();
		importedCustomer.setCustomerName("Test");
		importedCustomer.setCustomerId((Long) new SnowflakeShardingKeyGenerator().generateKey());
		importedCustomer.setMorePhoneNumber("morePhoneNumber");
		CustomerRepository repository = applicationContext.getBean(CustomerRepository.class);
		repository.save(importedCustomer);
		System.out.println("完成");
	}

	private static void move(ApplicationContext applicationContext, int i)
	{
		Logger logger = LoggerFactory.getLogger("客商迁移");
		JdbcTemplate jdbcTemplate = applicationContext.getBean("customerJdbcTemplate", JdbcTemplate.class);
		List<ImportedCustomer> importedCustomers = jdbcTemplate.query("SELECT * FROM imported_customer_" + i, new BeanPropertyRowMapper<ImportedCustomer>(ImportedCustomer.class));
		logger.info("共计:" + importedCustomers.size() + "条数据");
		List<PreciseCustomer> customerList = importedCustomers.parallelStream().map(importedCustomer -> {
			PreciseCustomer preciseCustomer = new PreciseCustomer();
			preciseCustomer.setCustomerId(importedCustomer.getCustomerId());
			preciseCustomer.setCustomerName(StringUtils.stripToNull(importedCustomer.getCustomerName()));
			preciseCustomer.setStatus(StringUtils.stripToNull(importedCustomer.getStatus()));
			preciseCustomer.setLegalPerson(StringUtils.stripToNull(importedCustomer.getLegalPerson()));
			preciseCustomer.setRegisteredCapitalDesc(StringUtils.stripToNull(importedCustomer.getRegisteredCapitalDesc()));
			String foudDateStr = StringUtils.stripToNull(importedCustomer.getFoundDate());
			Date foudDate = null;
			try
			{
				foudDate = foudDateStr == null ? null : new SimpleDateFormat("yyy-MM-dd").parse(foudDateStr);
			} catch (ParseException e)
			{
				logger.error("日期格式转换错误: " + foudDateStr);
			}
			preciseCustomer.setFoundDate(foudDateStr);
			preciseCustomer.setFoundDateDate(foudDate);
			preciseCustomer.setProvince(StringUtils.stripToNull(importedCustomer.getProvince()));
			preciseCustomer.setCity(StringUtils.stripToNull(importedCustomer.getCity()));
			preciseCustomer.setPhoneNumber(StringUtils.stripToNull(importedCustomer.getPhoneNumber()));
			preciseCustomer.setMorePhoneNumber(StringUtils.stripToNull(importedCustomer.getMorePhoneNumber()));
			preciseCustomer.setEmail(StringUtils.stripToNull(importedCustomer.getEmail()));
			preciseCustomer.setCreditCode(StringUtils.stripToNull(importedCustomer.getCreditCode()));
			preciseCustomer.setTaxCode(StringUtils.stripToNull(importedCustomer.getTaxCode()));
			preciseCustomer.setOrgCode(StringUtils.stripToNull(importedCustomer.getOrgCode()));
			String enterpriceType = StringUtils.stripToNull(importedCustomer.getEnterpriceType());
			if (enterpriceType != null)
			{
				enterpriceType = enterpriceType.replaceAll("\\(", "（");
				enterpriceType = enterpriceType.replaceAll("\\)", "）");
				enterpriceType = enterpriceType.replaceAll(",", "、");
				enterpriceType = enterpriceType.replaceAll("，", "、");
			}
			preciseCustomer.setEnterpriceType(enterpriceType);
			preciseCustomer.setIndustry(StringUtils.stripToNull(importedCustomer.getIndustry()));
			preciseCustomer.setHomePage(StringUtils.stripToNull(importedCustomer.getHomePage()));
			preciseCustomer.setAddress(StringUtils.stripToNull(importedCustomer.getAddress()));
			preciseCustomer.setBusinessScope(StringUtils.stripToNull(importedCustomer.getBusinessScope()));
			preciseCustomer.setCustomsCode(StringUtils.stripToNull(importedCustomer.getCustomsCode()));
			return preciseCustomer;
		}).collect(Collectors.toList());
		logger.info("转换完成，共计:" + customerList.size() + "条数据");
		CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
		List<PreciseCustomer> insertPreciseCustomers = new ArrayList<>();
		long count = 0;
		for (PreciseCustomer preciseCustomer : customerList)
		{
			insertPreciseCustomers.add(preciseCustomer);
			if (insertPreciseCustomers.size() == 5000)
			{
				count += 5000;
				customerRepository.saveAll(insertPreciseCustomers);
				insertPreciseCustomers.clear();
				logger.info("已完成:" + count + "/" + customerList.size());
			}
		}
		count += insertPreciseCustomers.size();
		customerRepository.saveAll(insertPreciseCustomers);
		logger.info("已完成:" + count + "/" + customerList.size());
	}

	private static void countCcode(ApplicationContext applicationContext)
	{
		JdbcTemplate jdbcTemplate = applicationContext.getBean("customerJdbcTemplate", JdbcTemplate.class);
		List<Map<String,Object>> customerList = jdbcTemplate.queryForList("SELECT province,city FROM imported_customer WHERE province IS NOT NULL OR city IS NOT NULL GROUP BY province,city");
		List<String> citys = customerList.stream().map(customer -> {
			String province = (String) customer.get("province");
			province = StringUtils.stripToNull(province);
			String city = (String) customer.get("city");
			city = StringUtils.stripToNull(city);
			//					enterpriceType = enterpriceType.replaceAll("\\(", "（");
			//					enterpriceType = enterpriceType.replaceAll("\\)", "）");
			//					enterpriceType = enterpriceType.replaceAll(",", "、");
			//					enterpriceType = enterpriceType.replaceAll("，", "、");
			return province + "-" + city;
		}).filter(o -> StringUtils.isNotBlank((String) o)).collect(Collectors.toSet()).stream().collect(Collectors.toList());
		citys.sort((o1, o2) -> {
			return o1.compareTo(o2);
		});
		for (String city : citys)
		{
			System.out.println(city);
		}
		//		String sql =
		//				"SELECT MAX(CHAR_LENGTH(customer_name)) AS customerName"
		//						+ ",MAX(CHAR_LENGTH(status)) AS status"
		//						+ ",MAX(CHAR_LENGTH(legal_person)) AS legalPerson"
		//						+ ",MAX(CHAR_LENGTH(found_date)) AS foundDate"
		//						+ ",MAX(CHAR_LENGTH(province)) AS province"
		//						+ ",MAX(CHAR_LENGTH(city)) AS city"
		//						+ ",MAX(CHAR_LENGTH(phone_number)) AS phoneNumber"
		//						+ ",MAX(CHAR_LENGTH(more_phone_number)) AS morePhoneNumber"
		//						+ ",MAX(CHAR_LENGTH(email)) AS email"
		//						+ ",MAX(CHAR_LENGTH(credit_code)) AS creditCode"
		//						+ ",MAX(CHAR_LENGTH(tax_code)) AS taxCode"
		//						+ ",MAX(CHAR_LENGTH(org_code)) AS orgCode"
		//						+ ",MAX(CHAR_LENGTH(enterprice_type)) AS enterpriceType"
		//						+ ",MAX(CHAR_LENGTH(industry)) AS industry"
		//						+ ",MAX(CHAR_LENGTH(home_page)) AS homePage"
		//						+ ",MAX(CHAR_LENGTH(address)) AS address"
		//						+ ",MAX(CHAR_LENGTH(business_scope)) AS businessScope"
		//						+ ",MAX(CHAR_LENGTH(customs_code)) AS customsCode"
		//						+ " FROM imported_customer";
		//		System.out.println(jdbcTemplate.queryForMap(sql));
	}

	private static void importCcode(ApplicationContext applicationContext)
	{
		CustomerImportService customerImportService = applicationContext.getBean(CustomerImportService.class);
		customerImportService.importCustomers("D:\\客商数据");
	}

	private static void testJpa(ApplicationContext applicationContext)
	{
		UserService userService = applicationContext.getBean(UserService.class);
		List<ErpUser> allUsers = userService.getAllUsers();
		allUsers.stream().forEach(System.out::println);
		System.out.println("-------------------");
		userService.getAllUsers();
		System.out.println("===================");
		userService.queryById(42);
		System.out.println("--------===========");
		ErpUser erpUser = userService.queryById(42);
		System.out.println("===========--------");
		erpUser.setLoginName("Test01");
		erpUser.setFirstName("南宫");
		userService.save(erpUser);
		userService.queryById(42);
		userService.getAllUsers();
	}
}
