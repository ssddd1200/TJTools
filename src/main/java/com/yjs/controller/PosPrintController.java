package com.yjs.controller;

import com.yjs.entity.PosRequestPO;
import com.yjs.service.PosPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/print")
public class PosPrintController {

    @Autowired
    private PosPrinterService service;

    @PostMapping(value = "/posprint")
    public void posPrint(@RequestBody PosRequestPO posRequestPO){
        service.PosPrint(posRequestPO);
    }
}
