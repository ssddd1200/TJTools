package com.yjs.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.yjs.utils.InfoUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyPrinterService {

    private final Path fileLocation;

    private static final String UploadPath = "./uploads";

    private static final Logger log = LoggerFactory.getLogger(MyPrinterService.class);

    private static final InfoUtil info = new InfoUtil();

    @Autowired
    public MyPrinterService(){
        this.fileLocation = Paths.get(UploadPath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印Img图片
     * @param is 文件流
     * @param fs 打印份数
     * @param fx 打印方向
     * @param dsm 单双面
     * @param printerName 打印机名称
     * @return
     */
    public void printImg(InputStream is, int fs, String fx, String dsm, String printerName){
        // 设置打印格式
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;

        //打印参数设置
        PrintRequestAttributeSet aset = setPrintAttrs(fs, fx, dsm);

        // 定位打印服务
        PrintService printService = findPrintService(printerName);
        if(printService==null){
            info.show("错误", "打印失败，未找到名称为" + printerName + "的打印机，请检查。");
            return;
        }

        Doc doc = new SimpleDoc(is, flavor, null);
        DocPrintJob docPrintJob = printService.createPrintJob();
        try {
            docPrintJob.print(doc, aset);
            info.show("提示", "正在打印");
        } catch (PrintException e) {
            e.printStackTrace();
            info.show("错误",e.getMessage());
        }
    }

    /**
     * 按照文件名打印PDF
     * @param filename
     * @param fs
     * @param dsm
     * @param printerName
     */
    public void printPDF(String filename, int fs, String dsm, String printerName){
        Path filePath = this.fileLocation.resolve(filename);
        File file = new File(filePath.toString());
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setJobName(filename);
            PrintService printService = findPrintService(printerName);
            if(printService == null){
                info.show("错误", "打印失败，未找到名称为" + printerName + "的打印机，请检查。");
                return;
            }else{
                printerJob.setPrintService(printService);
            }
            //设置纸张以及缩放
            PDFPrintable pdfPrintable = new PDFPrintable(document, Scaling.ACTUAL_SIZE);
            // 设置多页打印
            Book book = new Book();
            PageFormat pageFormat = new PageFormat();
            //设置打印方向：自动选择
            pageFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
            // 设置纸张
            pageFormat.setPaper(getPaper());
            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
            printerJob.setPageable(book);
            printerJob.setCopies(fs);
            PrintRequestAttributeSet parms = new HashPrintRequestAttributeSet();
            // 单双面，默认单面
            if("0".equals(dsm)){
                parms.add(Sides.ONE_SIDED);
            }else if("1".equals(dsm)){
                parms.add(Sides.DUPLEX);
            }
            printerJob.print(parms);
            info.show("提示", "正在打印");
        } catch (IOException e) {
            e.printStackTrace();
            info.show("错误", "PDF文件打印出错！请检查参数");
        } catch (PrinterException e) {
            e.printStackTrace();
            info.show("错误", "PDF文件打印出错！请检查参数");
        } finally {
            if (document != null){
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 按摩文件流打印PDF
     * @param file
     * @param fs
     * @param fx
     * @param dsm
     * @param printerName
     */
    public void printPDF(MultipartFile file, int fs, String fx, String dsm, String printerName){
        String fileName = file.getOriginalFilename();
        PDDocument document = null;
        try {
            document = PDDocument.load(file.getInputStream());
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            if (fileName.lastIndexOf(".") >= 0){
                printerJob.setJobName(fileName.substring(0, fileName.lastIndexOf(".")));
            }else{
                printerJob.setJobName(fileName);
            }
            PrintService printService = findPrintService(printerName);
            if(printService==null){
                info.show("错误", "打印失败，未找到名称为" + printerName + "的打印机，请检查。");
                return;
            }else{
                printerJob.setPrintService(printService);
            }
            //设置纸张以及缩放
            PDFPrintable pdfPrintable = new PDFPrintable(document, Scaling.ACTUAL_SIZE);
            // 设置多页打印
            Book book = new Book();
            PageFormat pageFormat = new PageFormat();
            //设置打印方向
            if("1".equals(fx)){
                pageFormat.setOrientation(PageFormat.PORTRAIT);
            }else if("0".equals(fx)){
                pageFormat.setOrientation(PageFormat.LANDSCAPE);
            }else if("2".equals(fx)){
                pageFormat.setOrientation(PageFormat.REVERSE_LANDSCAPE);
            }
            // 设置纸张
            pageFormat.setPaper(getPaper());
            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
            printerJob.setPageable(book);
            printerJob.setCopies(fs);
            PrintRequestAttributeSet parms = new HashPrintRequestAttributeSet();
            // 单双面，默认单面
            if("0".equals(dsm)){
                parms.add(Sides.ONE_SIDED);
            }else if("1".equals(dsm)){
                parms.add(Sides.DUPLEX);
            }
            printerJob.print(parms);
            info.show("提示", "正在打印");
        } catch (IOException e) {
            e.printStackTrace();
            info.show("错误", "PDF文件打印出错！请检查参数");
        } catch (PrinterException e) {
            e.printStackTrace();
            info.show("错误", "PDF文件打印出错！请检查参数");
        } finally {
            if (document != null){
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 按照文件路径打印word或者Excel
     * @param filePath word或者excel打印只支持文件路径
     * @param printerName
     */
    public void printWord(String filePath, String printerName){
        // 初始化线程
        ComThread.InitMTA();
        ActiveXComponent word = new ActiveXComponent("Word.Application");
        // 设置打印机名称
        word.setProperty("ActivePrinter", new Variant(printerName));
        // 这里visible控制文档打开后是否可见，若静默答应，第三个参数为false
        Dispatch.put(word, "Visible", new Variant(false));
        //获取文档属性
        Dispatch document = word.getProperty("Documents").toDispatch();
        Dispatch doc = null;
        // 打开文档
        try {
            doc = Dispatch.call(document, "Open", filePath).getDispatch();
            Dispatch.callN(doc, "PrintOut");
            info.show("提示", "正在打印");
        } catch (Exception e) {
            e.printStackTrace();
            info.show("错误", "PDF文件打印出错！请检查参数");
        } finally {
            if (doc != null){
                Dispatch.call(doc, "Close", new Variant(0));
            }
            word.invoke("Quit", new Variant(0));
            // 释放进程
            ComThread.Release();
            ComThread.quitMainSTA();
        }
    }

    /**
     * 按照文件路径打印word或者Excel
     * @param filePath word或者excel打印只支持文件路径
     * @param printerName
     */
    public void printExcel(String filePath, String printerName){
        // 初始化线程
        ComThread.InitMTA();
        ActiveXComponent excel = new ActiveXComponent("Excel.Application");

        // 这里visible控制文档打开后是否可见，若静默答应，第三个参数为false
        Dispatch.put(excel, "Visible", new Variant(false));
        //获取文档属性
        Dispatch workbook = excel.getProperty("Workbooks").toDispatch();

        //Excel 打印参数
        Object[] object = new Object[8];
        object[0] = Variant.VT_MISSING;
        object[1] = Variant.VT_MISSING;
        object[2] = Variant.VT_MISSING;
        object[3] = Variant.VT_FALSE;
        // 打印机名称
        object[4] = new Variant(printerName);
        object[5] = Variant.VT_FALSE;
        object[6] = Variant.VT_MISSING;
        object[7] = Variant.VT_MISSING;
        Dispatch work = null;
        // 打开文档
        try {
            work = Dispatch.call(workbook, "Open", filePath).getDispatch();
            Dispatch.callN(work, "PrintOut", object);
            info.show("提示", "正在打印");
        } catch (Exception e) {
            e.printStackTrace();
            info.show("错误", "PDF文件打印出错！请检查参数");
        } finally {
            if (work != null){
                Dispatch.call(work, "Close", new Variant(false));
            }
            excel.invoke("Quit", new Variant[]{});
            // 释放进程
            ComThread.Release();
            ComThread.quitMainSTA();
        }
    }

    /**
     * Word文件转化PDF
     * @param sp
     * @param ep
     */
    public void wordToPDF(String sp, String ep){
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        Dispatch doc = null;
        try{
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            doc = Dispatch.call(docs, "Open", sp).toDispatch();
            Path path = this.fileLocation.resolve(ep);
            File pdfFile = new File(path.toString());
            if (pdfFile.exists()){
                pdfFile.delete();
            }
            // 17 是pdf格式
            Dispatch.call(doc, "SaveAs", path.toString(), 17);

        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            Dispatch.call(doc, "Close", new Variant(false));
            System.out.println("关闭文档");
            if (app != null){
                app.invoke("Quit", new Variant[]{});
            }

        }
        // 如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();
    }

    /**
     * Excel文件转化PDF
     * @param sp
     * @param ep
     */
    public void excelToPDF(String sp, String ep){
        ActiveXComponent excel = new ActiveXComponent("Excel.Application");
        Dispatch doc = null;
        try{
            excel.setProperty("Visible", new Variant(false));
            // 禁用宏
            excel.setProperty("AutomationSecurity", new Variant(3));
            Dispatch docs = excel.getProperty("Workbooks").toDispatch();
            doc = Dispatch.call(docs, "Open", sp).toDispatch();
            Path path = this.fileLocation.resolve(ep);
            File pdfFile = new File(path.toString());
            if (pdfFile.exists()){
                pdfFile.delete();
            }
            Object[] object = new Object[9];
            // PDF格式
            object[0] = new Variant(0);
            object[1] = path.toString();
            // 0=标准 (生成的PDF图片不会变模糊) 1=最小文件(生成的PDF图片糊的一塌糊涂)
            object[2] = new Variant(0);
            object[3] = Variant.VT_MISSING;
            object[4] = Variant.VT_MISSING;
            object[5] = Variant.VT_MISSING;
            object[6] = Variant.VT_MISSING;
            object[7] = Variant.VT_FALSE;
            object[8] = Variant.VT_MISSING;
            Dispatch.call(doc, "ExportAsFixedFormat", object);

        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            if (doc != null){
                Dispatch.call(doc, "Close", new Variant(false));
            }
            System.out.println("关闭文档");
            if (excel != null){
                excel.invoke("Quit", new Variant[]{});
            }
        }

        ComThread.Release();
    }

    /**
     * 根据打印机名称获取打印机服务
     * @param printerName
     * @return
     */
    private PrintService findPrintService(String printerName){
        PrintService printService = null;
        if (printerName != null && !printerName.isEmpty()){
            // 获取本台电脑上所有的打印机设备
            PrintService[] printServices = PrinterJob.lookupPrintServices();
            if (printServices == null || printServices.length == 0){
                info.show("错误", "打印失败，本机没有可用打印机");
                log.info("打印失败，本机没有可用打印机");
            }else{
                for (int i = 0; i< printServices.length;i++){
                    System.out.println(printServices[i].getName());
                    if (printServices[i].getName().contains(printerName)){
                        printService = printServices[i];
                        break;
                    }
                }
            }
        }else{
            info.show("错误", "指定打印机名称为空");
            log.info("指定打印机名称为空");
        }
        return printService;
    }

    private PrintRequestAttributeSet setPrintAttrs(int fs, String fx, String dsm){
        // 设置打印参数
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        // 打印份数
        aset.add(new Copies(fs));
        // 打印方向 0 PORTRAIT 竖打 1 LANDSCAPE 横打
        if ("0".equals(fx)){
            aset.add(OrientationRequested.PORTRAIT);
        }else if ("1".equals(fx)){
            aset.add(OrientationRequested.LANDSCAPE);
        }
        //aset.add(MediaSize.ISO.A4); //纸张
        // aset.add(Finishings.STAPLE);//装订
        // 单双面，默认单面
        if("0".equals(dsm)){
            aset.add(Sides.ONE_SIDED);
        }else if("1".equals(dsm)){
            aset.add(Sides.DUPLEX);
        }
        return aset;
    }

    private static Paper getPaper(){
        Paper paper = new Paper();
        // 默认设置页面为A4纸
        int width = 595;
        int height = 842;
        // 设置边距
        int marginLeft = 10;
        int marginRight = 10;
        int marginTop = 10;
        int marginBottom = 10;

        paper.setSize(width, height);
        paper.setImageableArea(marginLeft, marginRight, width - (marginLeft + marginRight), height - (marginTop + marginBottom));
        return paper;
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path filePath = this.fileLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath.toString();
    }

    public void deleteAllFile(){
        List<String> fileList = new ArrayList<>();
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(this.fileLocation);
            for (Path p: paths){
                File f = new File(p.toString());
                f.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
