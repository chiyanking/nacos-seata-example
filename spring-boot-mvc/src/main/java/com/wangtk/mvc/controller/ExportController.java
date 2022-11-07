package com.wangtk.mvc.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wangtk.mvc.dto.PeopleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExportController {

    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = null;

        String fileName = "导出文档";
        String sheetName = "表哥1";
        response.setContentType("application/octet-stream");
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        for (int page = 1; page <= 50; page++) {

            if (page == 1) {
                // 这里 需要指定写用哪个class去写
                excelWriter = EasyExcel.write(response.getOutputStream(), PeopleVO.class).build();
                // 这里注意 如果同一个sheet只要创建一次
                writeSheet = EasyExcel.writerSheet(sheetName).build();
            }
            excelWriter.write(getData(), writeSheet);
            response.getOutputStream().flush();
        }
        if (excelWriter != null) {
            excelWriter.finish();
        }
    }

    List<PeopleVO> getData() {
        List list = new ArrayList();

        String a = "getDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetDatagetData";
        for (int i = 0; i < 10000; i++) {
            PeopleVO.PeopleVOBuilder builder = new PeopleVO().builder();
            list.add(builder.policeNo(a + "policeNo" + i)
                    .idx(i)
                    .bzDwCodeName(a + "bzDwCodeName" + i)
                    .assessTypeName(a + "assessTypeName" + i)
                    .genderName(a + "genderName" + i)
                    .name(a + "name" + i).build());

        }
        return list;
    }

}
