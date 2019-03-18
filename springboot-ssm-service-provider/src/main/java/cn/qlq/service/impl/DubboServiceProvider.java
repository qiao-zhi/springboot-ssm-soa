package cn.qlq.service.impl;

import java.util.concurrent.CountDownLatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("cn.qlq.mapper")
public class DubboServiceProvider {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		// 入口运行类
		SpringApplication.run(DubboServiceProvider.class, args);

		countDownLatch.await();
	}
}