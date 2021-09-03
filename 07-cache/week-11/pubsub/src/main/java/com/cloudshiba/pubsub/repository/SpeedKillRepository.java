package com.cloudshiba.pubsub.repository;

import com.cloudshiba.pubsub.entity.SpeedKill;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedKillRepository {
    SpeedKill get(long id);

    @Select("select * from speedkill where id = #{id} for update")
    SpeedKill getWithLock(long id);

    @Update("update speedkill set number = #{number} where id = #{id}")
    void updateStock(long id, int number);
}
