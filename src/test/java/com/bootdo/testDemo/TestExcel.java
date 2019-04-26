package com.bootdo.testDemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * jxl读excel
 *
 * @author jianggujin
 *
 */
public class TestExcel
{
    public static void main(String[] args) throws BiffException, IOException
    {
        List<Map<Integer, Object>> datas = getDatas("E:\\2018年所有出差申请-总结.xls");
//        Map<String, Set<String>> cityNameList = getCityNameList(datas);
//        Set<String> city = cityNameList.get("city");
//        Set<String> name = cityNameList.get("name");
//        System.out.println(city);
//        System.out.println(name);
        //按人统计天数
        Map<String, Integer> nameDayMap = new HashMap<String, Integer>();

        //按人统计天数地区
        Map<String, Map<String, Integer>> NamecityDayMap = new HashMap<String, Map<String, Integer>>();
        //按出差地统计人数
        Map<String, Integer> cityMap = new HashMap<String, Integer>();
        ////按出差地统计天数
        Map<String, Integer> cityDayMap = new HashMap<String, Integer>();
        ////按出差地统计人
        Map<String, Set<String>> cityNameMap = new HashMap<String, Set<String>>();
        ////按出差地统计人的天数
        Map<String, Map<String, Integer>> cityNameDayMap = new HashMap<String, Map<String, Integer>>();
        Set<String> nameSet = new HashSet<>();
        for (Map<Integer, Object> map : datas) {
            String city1 = (String)map.get(3);//3表示地区
            String day1 = (String)map.get(4);//4表示出差天数
            String name1 = (String)map.get(5);//5表示出差人
            //按人统计天数
            Integer nameDayNum = nameDayMap.get(name1);
            if (nameDayNum==null){
                nameDayNum = Integer.parseInt(day1);
                nameDayMap.put(name1,nameDayNum);
            }else{
                nameDayNum += Integer.parseInt(day1);
                nameDayMap.put(name1,nameDayNum);
            }
            Map<String, Integer> stringIntegerMap = NamecityDayMap.get(name1);
            if (stringIntegerMap==null){
                stringIntegerMap = new HashMap<String, Integer>();
            }
            Integer dayNum = stringIntegerMap.get(city1);
            if (dayNum==null){
                dayNum = Integer.parseInt(day1);
                stringIntegerMap.put(city1,dayNum);
            }else{
                dayNum += Integer.parseInt(day1);
                stringIntegerMap.put(city1,dayNum);
            }
            NamecityDayMap.put(name1,stringIntegerMap);


            //按出差地统计人数
            Integer cityNameNum = cityMap.get(city1);
            Set<String> nameSetStr = cityNameMap.get(city1);
            if (nameSetStr==null){
                nameSetStr = new HashSet<String>();
            }
            if (cityNameNum==null){
                cityNameNum = 1;
                cityMap.put(city1,cityNameNum);
            }else if (!nameSet.contains(city1+name1)){
                cityNameNum += 1;
                cityMap.put(city1,cityNameNum);
            }
            nameSetStr.add(name1);
            cityNameMap.put(city1,nameSetStr);
            nameSet.add(city1+name1);

            ////按出差地统计天数
            Integer cityDayNum = cityDayMap.get(city1);

            if (cityDayNum==null){
                cityDayNum = Integer.parseInt(day1);
                cityDayMap.put(city1,cityDayNum);
            }else{
                cityDayNum += Integer.parseInt(day1);
                cityDayMap.put(city1,cityDayNum);
            }
            //按出差地统计人的天数
            Map<String, Integer> cityNameDayNumMap = cityNameDayMap.get(city1);
            if (cityNameDayNumMap==null){
                cityNameDayNumMap = new  HashMap<String, Integer>();

            }
            Integer nameCityDayNum = cityNameDayNumMap.get(name1);
            if (nameCityDayNum==null){
                nameCityDayNum = Integer.parseInt(day1);
                cityNameDayNumMap.put(name1,nameCityDayNum);
            }else{
                nameCityDayNum += Integer.parseInt(day1);
                cityNameDayNumMap.put(name1,nameCityDayNum);
            }
            cityNameDayMap.put(city1,cityNameDayNumMap);

        }
        System.out.println("按人统计天数：");
        System.out.println(getKeyStr(nameDayMap));
        System.out.println(getVlueStr(nameDayMap));
        System.out.println(getcityNameDayMap(NamecityDayMap));
        System.out.println("按出差地统计人数：");
        System.out.println(getKeyStr(cityMap));
        System.out.println(getVlueStr(cityMap));
        System.out.println("按出差地统计天数：");
        System.out.println(getKeyStr(cityDayMap));
        System.out.println(getVlueStr(cityDayMap));
        System.out.println("按出差地统计人：");
        System.out.println(cityNameMap.keySet());
        System.out.println(cityNameMap.values());
        System.out.println("按出差地统计人的天数：");
        System.out.println(cityNameDayMap.keySet());
        System.out.println(getcityNameDayMap(cityNameDayMap));



    }
    public static String getcityNameDayMap(Map<String, Map<String, Integer>> map){
        Set<String> set = map.keySet();
        StringBuffer strs = new StringBuffer();
        int i=0;
        for (String str : set){
//            strs.append("\t");
            Map<String, Integer> stringIntegerMap = map.get(str);
            Set<String> strings = stringIntegerMap.keySet();
            for(String ss : strings){
                Integer integer = stringIntegerMap.get(ss);
              //  strs.append(ss);
             //   strs.append("="+integer);
                for (int j=0;j<i;j++){
                    strs.append("\t");
                }
                strs.append(integer);
                strs.append("\n");

            }
            i++;


        }
        return strs.toString();
    }


    public static String  getKeyStr(Map<String, Integer> map){
        Set<String> set = map.keySet();
        StringBuffer strs = new StringBuffer();
        for (String str : set){
//            strs.append("\t");
            strs.append(str);
            strs.append("\t");
        }
        return strs.toString();
    }
    public static String  getVlueStr(Map<String, Integer> map){
        Collection<Integer> values = map.values();
        StringBuffer strs = new StringBuffer();
        for (Integer str : values){
//            strs.append("\t");
            strs.append(str);
            strs.append("\t");
        }
        return strs.toString();
    }

    /**
     *  统计地区,人
     */
    public static Map<String,Set<String>> getCityNameList(List<Map<Integer, Object>> datas){
        Map<String,Set<String>> cityNameList = new HashMap<String,Set<String>>();
        Set<String> citySet = new HashSet<>();
        Set<String> nameSet = new HashSet<>();
//        citySet.add();
        for (Map<Integer, Object> dataMap : datas) {
            String city = (String)dataMap.get(3);//3表示地区
            citySet.add(city);
//            String day = (String)dataMap.get("4");//4表示出差天数
            String name = (String)dataMap.get(5);//5表示出差人
            nameSet.add(name);
        }
        cityNameList.put("city",citySet);
        cityNameList.put("name",nameSet);
        return cityNameList;
    }

    /**
     * 获取excel信息
     */
    public static List<Map<Integer,Object>> getDatas(String filePath) throws IOException, BiffException {
        File xlsFile = new File(filePath);
        // 获得工作簿对象
        Workbook workbook = Workbook.getWorkbook(xlsFile);
        // 获得所有工作表
        Sheet[] sheets = workbook.getSheets();
        // 遍历工作表
        //将数据放到List中
        List<Map<Integer,Object>> datas = new ArrayList<Map<Integer, Object>>();
        Map<Integer,Object> map = new HashMap<Integer, Object>();
        if (sheets != null)
        {
            for (Sheet sheet : sheets)
            {
                // 获得行数
                int rows = sheet.getRows();
                // 获得列数
                int cols = sheet.getColumns();
                // 读取数据,避过第一行标题
                for (int row = 1; row < rows; row++)
                {
//                    获取每一行的数据
                    for (int col = 0; col < cols; col++)
                    {
                        map.put(col,sheet.getCell(col, row).getContents().trim());
                    }
//                   将每一行的数据放到list中
                    datas.add(map);
                    map = new HashMap<Integer, Object>();
                }
                break;
            }

        }
        workbook.close();
        return datas;
    }
}
