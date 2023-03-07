package com.dsi.ebankback.controllers;

import com.dsi.ebankback.dtos.AccountHistoryDto;
import com.dsi.ebankback.dtos.OperationsDto;
import com.dsi.ebankback.exceptions.BankAccountNotFoundException;
import com.dsi.ebankback.services.OperationAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class OperationsController {

    private OperationAccountService operationService;


    @GetMapping("operations")
    public List<OperationsDto> getOperations() {
        return operationService.operationsAccount();
    }

    @GetMapping("operation/{ribAccount}")
    public List<OperationsDto> getAccountByRib(@PathVariable(name = "ribAccount") String rib) {
        return operationService.operationByAccount(rib);
    }

    @GetMapping("account/{rib}/pageOperation")
    public AccountHistoryDto getHistory(@PathVariable(name ="rib")String id,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "2") int sizePage) throws BankAccountNotFoundException {
        return operationService.getAccountHistory(id, page, sizePage);
    }
}
