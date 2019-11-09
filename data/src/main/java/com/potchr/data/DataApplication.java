package com.potchr.data;

import com.potchr.data.ccode.entity.ImportedCustomer;
import com.potchr.data.ccode.entity.PreciseCustomer;
import com.potchr.data.ccode.repository.CustomerRepository;
import com.potchr.data.ccode.service.CustomerImportService;
import com.potchr.data.user.entity.ErpUser;
import com.potchr.data.user.service.UserService;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableCaching
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication(scanBasePackages = {"com.potchr.data"})
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(DataApplication.class).bannerMode(Banner.Mode.OFF).build();
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        updateRegCode(applicationContext);
        applicationContext.close();
    }


    private static void updateRegCode(ApplicationContext applicationContext) {
        JdbcTemplate jdbcTemplate = applicationContext.getBean("customerJdbcTemplate", JdbcTemplate.class);
        String updateSql = "UPDATE precise_customer SET business_scope=? WHERE customer_id=?";
        for (int i = 867; i < 1317; i++) {
            int past = 1000 * i;
            String sql = "SELECT customer_id,customer_name FROM precise_customer ORDER BY customer_id LIMIT " + past + ",1000";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            List<Object[]> updateParams = new ArrayList<>();
            for (Map<String, Object> map : maps) {
                String customer_name = (String) map.get("customer_name");
                sql = "SELECT MAX(business_scope) AS business_scope FROM imported_customer_ext WHERE customer_name='" + customer_name + "'";
                String business_scope = jdbcTemplate.queryForObject(sql, String.class);
                Object[] param = new Object[2];
                param[0] = business_scope;
                param[1] = map.get("customer_id");
                updateParams.add(param);
            }
            jdbcTemplate.batchUpdate(updateSql, updateParams);
            System.out.println(i);
        }
    }

    private static void deleteRepeat(ApplicationContext applicationContext) {
        String sql = "SELECT customer_name,MAX(customer_id) AS customer_id FROM precise_customer GROUP BY customer_name HAVING COUNT(1) > 1";
        JdbcTemplate jdbcTemplate = applicationContext.getBean("customerJdbcTemplate", JdbcTemplate.class);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        if (mapList != null && !mapList.isEmpty()) {
            List<Long> customer_ids = mapList.stream().map(map -> (Long) map.get("customer_id")).collect(Collectors.toList());
            String idFilter = customer_ids.stream().map(id -> id.toString()).collect(Collectors.joining(","));
            sql = "DELETE FROM precise_customer WHERE customer_id IN (" + idFilter + ")";
            jdbcTemplate.execute(sql);
            System.out.println("完成");
        }
    }

    private static void addCcode(ApplicationContext applicationContext) throws ParseException {
        ImportedCustomer importedCustomer = new ImportedCustomer();
        importedCustomer.setCustomerName("Test");
        importedCustomer.setCustomerId((Long) new SnowflakeShardingKeyGenerator().generateKey());
        importedCustomer.setMorePhoneNumber("morePhoneNumber");
        CustomerRepository repository = applicationContext.getBean(CustomerRepository.class);
        repository.save(importedCustomer);
        System.out.println("完成");
    }

    private static void move(ApplicationContext applicationContext, int i) {
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
            try {
                foudDate = foudDateStr == null ? null : new SimpleDateFormat("yyy-MM-dd").parse(foudDateStr);
            } catch (ParseException e) {
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
            if (enterpriceType != null) {
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
        for (PreciseCustomer preciseCustomer : customerList) {
            insertPreciseCustomers.add(preciseCustomer);
            if (insertPreciseCustomers.size() == 5000) {
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

    private static void countCcode(ApplicationContext applicationContext) {
        JdbcTemplate jdbcTemplate = applicationContext.getBean("customerJdbcTemplate", JdbcTemplate.class);
        List<Map<String, Object>> customerList = jdbcTemplate.queryForList("SELECT province,city FROM imported_customer WHERE province IS NOT NULL OR city IS NOT NULL GROUP BY province,city");
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
        for (String city : citys) {
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

    private static void importCcode(ApplicationContext applicationContext) {
        CustomerImportService customerImportService = applicationContext.getBean(CustomerImportService.class);
        customerImportService.importCustomers("D:\\客商数据");
    }

    private static void testJpa(ApplicationContext applicationContext) {
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
