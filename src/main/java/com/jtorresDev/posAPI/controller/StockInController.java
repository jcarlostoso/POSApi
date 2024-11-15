package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.IStockInService;
import com.jtorresDev.posAPI.models.request.StockInRequest;
import com.jtorresDev.posAPI.models.response.StockInResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.Objects;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping(path = "stockin")
public class StockInController {

    private final IStockInService stockInService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello StockIn");

    }

    @GetMapping("/{id}")
    public ResponseEntity<StockInResponse>getStockIn(@PathVariable Long id){
        return ResponseEntity.ok(stockInService.read(id));
    }

    @GetMapping
    public ResponseEntity<Page<StockInResponse>>getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = this.stockInService.realAll(page, size, sortType);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/getbydate")
    public ResponseEntity<Set<StockInResponse>>getStockInByDay(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate){
        var response = this.stockInService.getByDate(startDate,endDate);

        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @GetMapping("/getbydatepage")
    public ResponseEntity<Page<StockInResponse>>getStockInByDaypage(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
    @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;

        PageRequest pageRequest = null;
        switch (sortType) {
            case NONE -> pageRequest = PageRequest.of(page, size);
            case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by("date").ascending());
            case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by("date").descending());
        }

        var response = this.stockInService.getByDatePage(startDate,endDate,pageRequest);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }


    @PostMapping
    public ResponseEntity<StockInResponse> createStockIn(@RequestBody StockInRequest request){
        return ResponseEntity.ok(this.stockInService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StockInResponse> deleteStockIn(@PathVariable Long id){

        this.stockInService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
