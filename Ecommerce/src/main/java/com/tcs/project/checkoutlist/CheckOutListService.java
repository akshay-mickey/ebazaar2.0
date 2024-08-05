package com.tcs.project.checkoutlist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CheckOutListService {
	
	@Autowired
    private CheckOutListRepository checkOutListRepository;

    public List<CheckOutList> getAllCheckOutLists() {
        return checkOutListRepository.findAll();
    }

    public Optional<CheckOutList> getCheckOutListById(Long id) {
        return checkOutListRepository.findById(id);
    }

    public CheckOutList saveCheckOutList(CheckOutList checkOutList) {
        return checkOutListRepository.save(checkOutList);
    }

    public void deleteCheckOutList(Long id) {
        checkOutListRepository.deleteById(id);
    }
}


