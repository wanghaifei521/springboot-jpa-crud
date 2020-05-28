package com.qf.service.impl;

import com.qf.domain.Items;
import com.qf.respository.ItemsRepository;
import com.qf.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 54110 on 2019/11/21.
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public void saveItems(Items items) {
        itemsRepository.save(items);
    }

    @Override
    public void deleteItems(Integer id) {
        itemsRepository.deleteById(id);
    }

    @Override
    public void updateItems(Items items) {
        itemsRepository.saveAndFlush(items);
    }

    @Override
    public List<Items> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public Items findById(Integer id) {

        Optional<Items> byId =  itemsRepository.findById(id);
        Items items=null;
        if (byId.isPresent()){
            items = byId.get();
        }

        return items;
    }
}
