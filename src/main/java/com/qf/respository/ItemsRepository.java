package com.qf.respository;

import com.qf.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 54110 on 2019/11/21.
 */
public interface ItemsRepository extends JpaRepository<Items,Integer> {
}
