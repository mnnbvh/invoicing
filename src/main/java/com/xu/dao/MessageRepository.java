package com.xu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.xu.bean.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	@Query("select count(id) from Message where flag = '未查看'")
	public Long findConts();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value ="update message set flag='已查看' where id=?1",nativeQuery=true)
	public void updateMessageById(Long id);

}
