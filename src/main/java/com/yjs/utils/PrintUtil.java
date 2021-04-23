package com.yjs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import java.awt.print.Book;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * 两种打印方式
 *  - 根据打印机名称打印
 *  - 默认打印机打印
 */
public class PrintUtil {

    private static final Logger log = LoggerFactory.getLogger(PrintUtil.class);

    public static void print(Book book){
        //获取默认打印机
        PrinterJob pjob = PrinterJob.getPrinterJob();
        pjob.setPageable(book);
        try {
            log.info("正常打印");
            pjob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
            log.info("打印异常，请检查配置");
        }
    }

    public static void print(String printName, Book book){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(book);
        HashAttributeSet hs = new HashAttributeSet();
        hs.add(new PrinterName(printName, null));
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, hs);
        if (ps.length > 0){
            try {
                job.setPrintService(ps[0]);
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
                log.info("打印异常，请检查配置");
            }
        }else{
            log.info("无法找到指定打印机");
        }
    }
}
