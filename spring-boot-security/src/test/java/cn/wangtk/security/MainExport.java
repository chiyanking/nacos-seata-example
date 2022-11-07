package cn.wangtk.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.List;

public class MainExport {

    public static void main(String[] args) {
        int[] a = new int[10];

        BigExcelWriter writer = ExcelUtil.getBigWriter("./xxx.xlsx");
        for (int i = 0; i < 1048576 / 2; i++) {

            List<?> row1 = CollUtil.newArrayList("aa" + i, "bb" + i, "cc" + i, "dd" + i, DateUtil.date(), 3.22676575765);
            List<?> row2 = CollUtil.newArrayList("aaa" + i, "bbb" + i, "ccc" + i, "dd" + i, DateUtil.date(), 250.7676);

            List<List<?>> rows = CollUtil.newArrayList(row1, row2);
//            if (i % 1000 == 0) {
//                writer.write(rows);
//            }

            // 一次性写出内容，使用默认样式

        }

        // 关闭writer，释放内存
        writer.close();
    }

}
