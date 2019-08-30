package com.potchr.data;

import com.potchr.data.ccode.entity.ImportedCustomer;
import com.potchr.data.ccode.repository.CustomerRepository;
import com.potchr.data.service.CcodeImportService;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataApplicationTests
{
	@Autowired
	private CustomerRepository customerRepository;

	private SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();

	@Test
	public void testCcodeRepeat()
	{
		ImportedCustomer importedCustomer = new ImportedCustomer();
		importedCustomer.setCustomerId((Long) keyGenerator.generateKey());
		importedCustomer.setRemark("xxxxxxxxxxxxxxxxxxx");
		customerRepository.save(importedCustomer);
	}
}
