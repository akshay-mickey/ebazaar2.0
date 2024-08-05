package com.tcs.project.checkoutlist;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkoutlist")
public class CheckOutListController {

	
	    @Autowired
	    private CheckOutListService checkOutListService;

	    @GetMapping
	    public List<CheckOutList> getAllCheckOutLists() {
	        return checkOutListService.getAllCheckOutLists();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CheckOutList> getCheckOutListById(@PathVariable Long id) {
	        Optional<CheckOutList> checkOutList = checkOutListService.getCheckOutListById(id);
	        return checkOutList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public CheckOutList createCheckOutList(@RequestBody CheckOutList checkOutList) {
	        return checkOutListService.saveCheckOutList(checkOutList);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CheckOutList> updateCheckOutList(@PathVariable Long id, @RequestBody CheckOutList checkOutList) {
	        if (!checkOutListService.getCheckOutListById(id).isPresent()) {
	            return ResponseEntity.notFound().build();
	        }
	        checkOutList.setId(id);
	        return ResponseEntity.ok(checkOutListService.saveCheckOutList(checkOutList));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCheckOutList(@PathVariable Long id) {
	        if (!checkOutListService.getCheckOutListById(id).isPresent()) {
	            return ResponseEntity.notFound().build();
	        }
	        checkOutListService.deleteCheckOutList(id);
	        return ResponseEntity.noContent().build();
	    }
	}

	

