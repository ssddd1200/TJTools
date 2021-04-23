package com.yjs.controller;

import com.yjs.service.MyPrinterService;
import com.yjs.utils.InfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/print")
public class FilePrintController {

    @Autowired
    private MyPrinterService service;

    @PostMapping(value = "/printimg")
    public void wendangPrint(@RequestParam(value = "file1") MultipartFile file, @RequestParam(value = "fenshu") int fs,
                             @RequestParam(value = "fangxiang") String fx, @RequestParam(value = "dsm") String dsm,
                             @RequestParam(value = "printername") String printerName){
        InputStream fis = null;
        try {
            fis = file.getInputStream();
            service.printImg(fis, fs, fx, dsm, printerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "printpdf")
    public void printPDF(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "fenshu") int fs,
                         @RequestParam(value = "fangxiang") String fx, @RequestParam(value = "dsm") String dsm,
                         @RequestParam(value = "printername") String printerName){
        service.printPDF(file, fs, fx, dsm, printerName);
    }

    @PostMapping(value = "printoffice1")
    public void printOffice1(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "printername") String printerName){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        String officePath = service.storeFile(file);
        if (suffix.toLowerCase().contentEquals("doc") || suffix.toLowerCase().contentEquals("docx")){
            service.printWord(officePath, printerName);
        }else if (suffix.toLowerCase().contentEquals("xls") || suffix.toLowerCase().contentEquals("xlsx")){
            service.printExcel(officePath, printerName);
        }else {
            InfoUtil infoUtil = new InfoUtil();
            infoUtil.show("错误","文件格式错误");
        }
        // 清理缓存文件
        service.deleteAllFile();
    }

    @PostMapping(value = "printoffice2")
    public void printOffice2(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "printername") String printerName){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        String pdfName = fileName.substring(0, fileName.lastIndexOf("."))+".pdf";
        String officePath = service.storeFile(file);
        if (suffix.toLowerCase().contentEquals("doc") || suffix.toLowerCase().contentEquals("docx")){
            service.wordToPDF(officePath, pdfName);
        }else if (suffix.toLowerCase().contentEquals("xls") || suffix.toLowerCase().contentEquals("xlsx")){
            service.excelToPDF(officePath, pdfName);
        }else {
            InfoUtil infoUtil = new InfoUtil();
            infoUtil.show("错误","文件格式错误");
            return ;
        }
        service.printPDF(pdfName, 1, "0", printerName);
        // 清理缓存文件
        service.deleteAllFile();
    }
}
