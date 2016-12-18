package com.customer.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.BeanUtils;
import com.customer.dao.CustomerDao;
import com.customer.entity.DbCustomer;
import com.customer.model.CustomerModel;


@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> loadCustomer() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		DetachedCriteria criteria=DetachedCriteria.forClass(DbCustomer.class,"customer");
		ProjectionList projectionList = Projections.projectionList();
		
		projectionList.add(Projections.property("customer.id"),"id");
		projectionList.add(Projections.property("customer.customerCode"),"customerCode");
		projectionList.add(Projections.property("customer.firstName"),"firstName");
		projectionList.add(Projections.property("customer.lastName"),"lastName");
		projectionList.add(Projections.property("customer.sex"),"sex");
		projectionList.add(Projections.property("customer.phoneNumber"),"phoneNumber");
		projectionList.add(Projections.property("customer.email"),"email");
		projectionList.add(Projections.property("customer.address"),"address");
		projectionList.add(Projections.property("customer.zipCode"),"zipCode");
		projectionList.add(Projections.property("customer.dateEnter"),"dateEnter");
		
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.asc("customer.customerCode"));
		criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Session session = sessionFactory.openSession();
		list = criteria.getExecutableCriteria(session).list();
		session.close();
		
		
		return list;
	}


	@Override
	public Map<String, Object> removeCustomer(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();

		try{
			DbCustomer dbCustomer = new DbCustomer();
			session.beginTransaction();
			dbCustomer.setId(id);
			session.delete(dbCustomer);
			session.flush();
			session.getTransaction().commit();
			map.put("success", true);
			map.put("msg", "Customer Removed!!");
		
		}catch (Exception e){
			map.put("success", false);
			map.put("msg", "Remove fail!!");
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		
		return map;
	}


	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> insertCustomer(CustomerModel model) {
Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();
		
		Date date = new Date();
		
		if(BeanUtils.isEmpty(model.getCustomerCode())){
			map.put("success", false);
			map.put("msg", "Customer Code can't be null.");
		}
		else if(BeanUtils.isEmpty(model.getFirstName())){
			map.put("success", false);
			map.put("msg", "FirstName can't be null.");
		}
		else if(BeanUtils.isEmpty(model.getLastName())){
			map.put("success", false);
			map.put("msg", "LastName can't be null.");
		}
		else if(BeanUtils.isEmpty(model.getPhoneNumber())){
			map.put("success", false);
			map.put("msg", "Phone Number can't be null.");
		}
		else if(BeanUtils.isEmpty(model.getSex())){
			map.put("success", false);
			map.put("msg", "You have to select sex.");
		}
		else{
			try{
				DbCustomer dbCustomer = new DbCustomer();
				DbCustomer dbCustomer1 = new DbCustomer();
				
				session.beginTransaction();
				
				System.out.println(model.getCustomerCode());
				
				Query queryDbCustomer=session.createQuery("from DbCustomer  where customerCode=:customerCode");
				queryDbCustomer.setParameter("customerCode", model.getCustomerCode());
				dbCustomer1=(DbCustomer) queryDbCustomer.uniqueResult();
				System.out.println("input code : " + model.getCustomerCode());
				if(!BeanUtils.isEmpty(model.getCustomerCode())){
					System.out.println("input code : " + model.getCustomerCode());
					if(null != dbCustomer1){
						map.put("success", false);
						map.put("msg", "Duplicate Customer Code!!.");
						
							
					}else {
						dbCustomer.setCustomerCode(model.getCustomerCode());
						if(BeanUtils.isNotEmpty(model.getFirstName())){
							dbCustomer.setFirstName(model.getFirstName());
						}
						if(BeanUtils.isNotEmpty(model.getLastName())){
							dbCustomer.setLastName(model.getLastName());
						}
						if(BeanUtils.isNotEmpty(model.getPhoneNumber())){
							dbCustomer.setPhoneNumber(model.getPhoneNumber());
						}
						if(BeanUtils.isNotEmpty(model.getSex())){
							dbCustomer.setSex(model.getSex());
						}
						if(BeanUtils.isNotEmpty(model.getEmail())){
							dbCustomer.setEmail(model.getEmail());
						}
						if(BeanUtils.isNotEmpty(model.getAddress())){
							dbCustomer.setAddress(model.getAddress());
						}
						
						if(BeanUtils.isNotEmpty(model.getZipCode())){
							dbCustomer.setZipCode(model.getZipCode());
						}
						if(BeanUtils.isNotEmpty(date)){
							dbCustomer.setdateEnter(date);
						}
						
						System.out.println("set customer code");	
						session.save(dbCustomer);
						session.flush();
						session.getTransaction().commit();
						
						map.put("success", true);
						map.put("msg", "Insert success!!");	
					}
				}
				
				
			
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Insert fail");
				e.printStackTrace();
			}
			finally {
				session.close();
			}
		}
		
		
		return map;
	}


	@Override
	public Map<String, Object> updateCustomer(CustomerModel model) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();
		
		if(model.getCustomerCode()==null){
			map.put("success", false);
			map.put("msg", "Customer Code can't be null.");
		}
		else if(model.getFirstName()==null){
			map.put("success", false);
			map.put("msg", "FirstName can't be null.");
		}
		else if(model.getLastName()==null){
			map.put("success", false);
			map.put("msg", "LastName can't be null.");
		}
		else if(model.getPhoneNumber()==null){
			map.put("success", false);
			map.put("msg", "Phone Number can't be null.");
		}
		else if(model.getSex()==null){
			map.put("success", false);
			map.put("msg", "You have to select sex.");
		}
		else{
		
			try{
				DbCustomer dbCustomer = new DbCustomer();
				session.beginTransaction();
				
				Query query = session.createQuery("from DbCustomer where id = :ID");
				query.setParameter("ID", model.getId());
				dbCustomer = (DbCustomer) query.uniqueResult();
				
				if(BeanUtils.isNotEmpty(model.getCustomerCode())){
					dbCustomer.setCustomerCode(model.getCustomerCode());
				}
				if(BeanUtils.isNotEmpty(model.getFirstName())){
					dbCustomer.setFirstName(model.getFirstName());
				}
				if(BeanUtils.isNotEmpty(model.getLastName())){
					dbCustomer.setLastName(model.getLastName());
				}
				if(BeanUtils.isNotEmpty(model.getPhoneNumber())){
					dbCustomer.setPhoneNumber(model.getPhoneNumber());
				}
				if(BeanUtils.isNotEmpty(model.getSex())){
					dbCustomer.setSex(model.getSex());
				}
				if(BeanUtils.isNotEmpty(model.getEmail())){
					dbCustomer.setEmail(model.getEmail());
				}
				if(BeanUtils.isNotEmpty(model.getAddress())){
					dbCustomer.setAddress(model.getAddress());
				}
				
				if(BeanUtils.isNotEmpty(model.getZipCode())){
					dbCustomer.setZipCode(model.getZipCode());
				}
			
					
				session.update(dbCustomer);
				session.flush();
				session.getTransaction().commit();
				map.put("success", true);
				map.put("msg", "Customer Updated!!");
				
				
			
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Update fail!!");
				e.printStackTrace();
			}
			finally {
				session.close();
			}
		}
		
		
		return map;
	}

	
}
