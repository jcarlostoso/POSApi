package com.jtorresDev.posAPI.controller;

import com.jtorresDev.posAPI.abstract_services.ISupplyService;
import com.jtorresDev.posAPI.models.request.SupplyRequest;
import com.jtorresDev.posAPI.models.response.SupplyResponse;
import com.jtorresDev.posAPI.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(path = "supply")
public class SupplyController {
    private final ISupplyService supplyService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Supply");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyResponse> getSupplyById(@PathVariable Long id){
        return ResponseEntity.ok(supplyService.read(id));
    }

    @GetMapping
    public ResponseEntity<Page<SupplyResponse>>getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType
    ){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        var response =this.supplyService.realAll(page, size, sortType);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<SupplyResponse>createSupply(@RequestBody SupplyRequest request){
        return ResponseEntity.ok(this.supplyService.create(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SupplyResponse> deleteSupply(@PathVariable Long id){
        this.supplyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
