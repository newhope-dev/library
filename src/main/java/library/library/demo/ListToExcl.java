package library.library.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListToExcl {
    public void sheet(){

        //表单明
        String tName="学生信息表";
        //表头行列名
        ArrayList<String> tHeader=new ArrayList<>();
        tHeader.add("编号");

        tHeader.add("姓名");

        tHeader.add("性别(is Man?)");

        tHeader.add("年龄");



        // 表单数据(除表头) - 行

        ArrayList<Object> tValue1 = new ArrayList<Object>();

        tValue1.add(1);

        tValue1.add("Feleon");

        tValue1.add(true);

        tValue1.add("21");

        // 表单数据(除表头) - 行

        ArrayList<Object> tValue2 = new ArrayList<Object>();

        tValue2.add(2);

        tValue2.add("Iravy");

        tValue2.add(false);

        tValue2.add("18");

        // 表单数据(除表头) - 行

        ArrayList<Object> tValue3 = new ArrayList<Object>();

        tValue3.add(3);

        tValue3.add("Helly");

        tValue3.add(false);

        tValue3.add("18");

        ArrayList<ArrayList<Object>> tValue = new ArrayList<ArrayList<Object>>();

        tValue.add(tValue1);

        tValue.add(tValue2);

        tValue.add(tValue3);



        // 表头样式

        Map<String, Short> tHeaderStyle = new HashMap<String, Short>();

        tHeaderStyle.put("color", (short)10);

        tHeaderStyle.put("weight", (short)700);



        // 表数据样式

        Map<String, Short> tValueStyle = new HashMap<String, Short>();

        tValueStyle.put("color", (short)32767);

        tValueStyle.put("weight", (short)400);



        String filePath = "F:/demo.xls";



        try {

//            ExcelUtil.newInstance().exportExcel(tName, tHeader,
//
//                    tValue, tHeaderStyle,
//
//                    tValueStyle, filePath);

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}
