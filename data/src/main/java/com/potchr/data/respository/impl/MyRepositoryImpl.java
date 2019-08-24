//package com.potchr.data.respository.impl;
//
//import com.potchr.data.params.Params;
//import com.potchr.data.respository.MyRepository;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.ReflectionUtils;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
///**
// * <p>标题：</p>
// * <p>功能：</p>
// * <p>
// * 其他说明：
// * </p>
// * <p>作者：yangy</p>
// * <p>审核：</p>
// * <p>重构：</p>
// * <p>创建日期：2019/8/13 19:53</p>
// */
//@Repository
//@Transactional(readOnly = true)
//public class MyRepositoryImpl<T, ID> extends SimpleJpaRepository<T,ID> implements MyRepository<T,ID>
//{
//	public MyRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager)
//	{
//		super(entityInformation, entityManager);
//	}
//
//	public MyRepositoryImpl(Class<T> domainClass, EntityManager em)
//	{
//		super(domainClass, em);
//	}
//
//	@Override
//	public List<T> findByParams(Params params)
//	{
//		Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(params.getClass());
//		return findAll(new Specification<T>()
//		{
//			@Override
//			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
//			{
//				List<Predicate> predicates = new ArrayList<>();
//				for (Method method : allDeclaredMethods)
//				{
//					String methodName = method.getName();
//					if (methodName.length() > 3 && methodName.startsWith("get") && method.getGenericParameterTypes() == null && method.getReturnType() != Void.class)
//					{
//						String fieldName = methodName.substring(3);
//						fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
//						Predicate filter = null;
//						Field field = ReflectionUtils.findField(params.getClass(), fieldName);
//						if (field == null)
//							continue;
//						Object object = ReflectionUtils.invokeMethod(method, params);
//						filter = criteriaBuilder.like(root.get(fieldName), object.toString());
//						filter = root.get(fieldName).in(new Object());
//						if (filter != null)
//						{
//							predicates.add(filter);
//						}
//					}
//				}
//				if (predicates != null && !predicates.isEmpty())
//				{
//					return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//				}
//				return null;
//			}
//		});
//	}
//}
