package com.customer.services;

import java.util.List;
import java.util.Map;

import com.customer.model.CustomerModel;

public interface CustomerService {

	List<Map<String, Object>> loadCustomer();

	Map<String, Object> insertCustomer(CustomerModel model);

	Map<String, Object> removeCustomer(Integer id);

	Map<String, Object> updateCustomer(CustomerModel model);

}
