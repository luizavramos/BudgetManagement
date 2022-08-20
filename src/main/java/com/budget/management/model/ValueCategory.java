package com.budget.management.model;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;



public interface ValueCategory {
	@Value("#{target.category.description}")
	String getCategory();
	BigDecimal getTotal();
}
