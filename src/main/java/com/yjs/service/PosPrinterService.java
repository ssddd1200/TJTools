package com.yjs.service;

import com.yjs.entity.PosRequestPO;
import com.yjs.entity.PosSingleTemplate;
import com.yjs.entity.PosWholeTemplate;
import com.yjs.utils.InfoUtil;
import com.yjs.utils.PrintUtil;
import org.krysalis.barcode4j.impl.codabar.CodabarBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PosPrinterService {

    private static final InfoUtil info = new InfoUtil();

    public void PosPrint(PosRequestPO requestPO){
        String[] database = requestPO.getDatabase().split(",");
        PosWholeTemplate whole = requestPO.getTemplate();
        List<PosSingleTemplate> single = whole.getSingles();

        int count =  database.length / single.size();
        System.out.println("count = "+ count);
        if (PrinterJob.lookupPrintServices().length > 0){
            for (int i = 0; i < count; i++) {
                // POS机打印格式书写
                PageFormat pageFormat = new PageFormat();
                pageFormat.setOrientation(PageFormat.PORTRAIT);

                Paper paper = new Paper();
                paper.setImageableArea(0,0, whole.getWidth(), whole.getHeight());
                pageFormat.setPaper(paper);

                Book book = new Book();
                int fi = i;
                book.append(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        Graphics2D graphics2D = (Graphics2D) graphics;
                        int x = whole.getDx();
                        int y = whole.getDy();
                        for (int j = 0; j < single.size();j++){
                            PosSingleTemplate t = single.get(j);
                            switch (t.getType()){
                                case "code128":
                                    byte[] bytes = drawCode128(database[fi * single.size() + j]);
                                    BufferedImage image= null;
                                    try {
                                        image = ImageIO.read(new ByteArrayInputStream(bytes));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    graphics.drawImage(image, x + t.getX(), y + t.getY(), t.getWidth(), t.getHeight(),null);
                                    break;
                                case "codebar":
                                    byte[] b = drawCodeBar(database[fi * single.size() + j]);
                                    BufferedImage img = null;
                                    try {
                                        img = ImageIO.read(new ByteArrayInputStream(b));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    graphics.drawImage(img, x + t.getX(), y + t.getY(),t.getWidth(), t.getHeight(), null);
                                    break;
                                case "text":
                                    Font font = new Font(t.getFontName(),t.getBold() == 1? Font.BOLD:Font.PLAIN, t.getFontSize());
                                    graphics2D.setFont(font);
                                    int newX = startX(graphics2D, database[fi * single.size() + j], t.getX(), t.getWidth(), t.getAlign());
                                    drawString(graphics2D, database[fi * single.size() + j], x + newX, y + t.getY(), t.getWidth(), t.getHeight());
                                    break;
                            }
                        }

                        return PAGE_EXISTS;
                    }
                }, pageFormat);
                PrintUtil.print(requestPO.getPrintName(), book);
            }
            info.show("提示", "正在打印");
        } else {
            info.show("错误","没有发现打印服务");
        }
    }

    private static byte[] drawCode128(String msg){
        Code128Bean code128Bean = new Code128Bean();
        // 分辨率
        final int dpi = 1024;
        // 设置两边留白
        code128Bean.doQuietZone(true);
        // 设置条形码宽度
//        code128Bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
        // 设置图片类型
        String format = "image/png";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        code128Bean.generateBarcode(canvas, msg);

        try {
            canvas.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    private static byte[] drawCodeBar(String msg){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CodabarBean bean = new CodabarBean();

        final int dpi = 512;
        bean.doQuietZone(true);
//        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi);

        String format = "image/png";

        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        bean.generateBarcode(canvas, msg);

        try {
            canvas.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    private static int startX(Graphics2D graphics2D, String text, int x, int lineWidth, String align){
        int x2 = 0;
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int strWidth = fontMetrics.stringWidth(text);
        int blackWidth = lineWidth - strWidth;
        switch (align.toLowerCase()){
            case "left":
                x2 = x;
                break;
            case "right":
                x2 = x + blackWidth;
                break;
            case "center":
                x2 = x + blackWidth / 2;
                break;
        }
        return x2;
    }

    private static int drawString(Graphics2D graphics2D, String text, int x, int y, int lineWidth, int lineHeight){
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        if (fontMetrics.stringWidth(text) < lineWidth){
            graphics2D.drawString(text, x, y);
            return y;
        } else {
            char[] chars = text.toCharArray();
            int charsWidth = 0;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < chars.length; i++){
                if ((charsWidth + fontMetrics.charWidth(chars[i]))>lineWidth){
                    graphics2D.drawString(sb.toString(), x, y);
                    sb.setLength(0);
                    y = y + lineHeight;
                    charsWidth = fontMetrics.charWidth(chars[i]);
                    sb.append(chars[i]);
                } else {
                    charsWidth = charsWidth + fontMetrics.charWidth(chars[i]);
                    sb.append(chars[i]);
                }
            }
            if (sb.length() > 0){
                graphics2D.drawString(sb.toString(), x, y);
                y = y + lineHeight;
            }
            return y - lineHeight;
        }
    }
}
