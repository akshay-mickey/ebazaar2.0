package com.EBazaar.UserInfo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Integer>{

	List<UserInfo> findByUserName(String name);

	UserInfo findByPhoneNumber(Long phoneNumber);





	

}
