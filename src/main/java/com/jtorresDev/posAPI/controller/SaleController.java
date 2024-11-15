package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.ISaleService;
import com.jtorresDev.posAPI.models.request.SaleRequest;
import com.jtorresDev.posAPI.models.response.SaleResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping(path = "sale")
public class SaleController {
    private final ISaleService saleService;


    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Sale");
    }
    @GetMapping("/salesByDateRange")
    public ResponseEntity<Page<SaleResponse>>salesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false)SortType sortType
            ){
        PageRequest pagable =PageRequest.of(page,size);
            var response = this.saleService.salesByDateRange(startDate,endDate,pagable,sortType);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<SaleResponse>createSale(@RequestBody SaleRequest request){
        System.out.println("request de Controller");
        System.out.println(request);
        return ResponseEntity.ok(this.saleService.create(request));
    }
}
