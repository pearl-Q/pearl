package com.ssh.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssh.domain.Customer;
import com.ssh.service.CustomerService;
import com.ssh.service.impl.CustomerServiceImpl;


	@RunWith(SpringJUnit4ClassRunner.class) 
	@ContextConfiguration("classpath:applicationContext.xml")
	public class sshTest {
		@Resource(name = "customerService ")
		private CustomerService customerService;
		@Test
		public void demo1() {
			
		}
}
