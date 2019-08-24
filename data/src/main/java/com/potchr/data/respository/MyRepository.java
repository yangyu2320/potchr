//package com.potchr.data.respository;
//
//import com.potchr.data.params.Params;
//import com.sun.xml.internal.bind.v2.model.core.ID;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
//import org.springframework.data.repository.NoRepositoryBean;
//
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
// * <p>创建日期：2019/8/13 19:49</p>
// */
//@NoRepositoryBean
//public interface MyRepository<T, ID> extends JpaRepositoryImplementation<T,ID>, JpaSpecificationExecutor<T>
//{
//	List<T> findByParams(Params params);
//}
