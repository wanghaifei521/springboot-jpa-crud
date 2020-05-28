package com.qf.service;

import com.qf.domain.Items;

import java.util.List;

/**
 * Created by 54110 on 2019/11/21.
 */
public interface ItemsService {

    void saveItems(Items items);

    void deleteItems(Integer id);

    void updateItems(Items items);

    List<Items> findAll();

    Items findById(Integer id);
}
