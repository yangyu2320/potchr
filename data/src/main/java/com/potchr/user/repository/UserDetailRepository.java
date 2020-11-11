package com.potchr.user.repository;

import com.potchr.user.entity.User;
import com.potchr.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>, JpaSpecificationExecutor<UserDetail> {

}
