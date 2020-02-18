package com.lcn29.kit.util;

public class ExcelUtil {

    /**
     * 用map方式创建excel，可动态生成excel
     *
     * @param fileName            下载文件名
     * @param isIndex             是否带索引
     * @param entity              表头样式设置
     * @param excelExportEntities 表头对应map
     * @param dataSet             数据集
     * @param response
     */
/*    public static void exportMapExcel(String fileName, boolean isIndex, ExportParams entity, List<ExcelExportEntity> excelExportEntities,
                                      Collection<Map> dataSet, HttpServletResponse response) {
        entity.setAddIndex(isIndex);
        Workbook workbook = ExcelExportUtil.exportExcel(entity, excelExportEntities, dataSet);
        downloadExcel(fileName, response, workbook);
    }*/

  /*  public static ExcelExportEntity getDateExcelEntity(String name, String key, int orderNum) {
        ExcelExportEntity excelExportEntity = new ExcelExportEntity(name, key);
        excelExportEntity.setFormat("yyyy-MM-dd");
        excelExportEntity.setOrderNum(orderNum);
        return excelExportEntity;
    }
*/
/*    public static ExcelExportEntity getExcelEntity(String name, String key, int orderNum) {
        ExcelExportEntity excelExportEntity = new ExcelExportEntity(name, key);
        excelExportEntity.setOrderNum(orderNum);
        return excelExportEntity;
    }*/

    /**
     * @param isIndex   是否带索引
     * @param fileName  下载文件名
     * @param entity    表头样式设置
     * @param pojoClass 表头对应实体类
     * @param dataSet   数据集
     * @return
     */
/*    public static void excelExport(String fileName, boolean isIndex, ExportParams entity, Class<?> pojoClass,
                                   Collection<?> dataSet, HttpServletResponse response) {
        entity.setAddIndex(isIndex);
        Workbook workbook = ExcelExportUtil.exportExcel(entity, pojoClass, dataSet);
        downloadExcel(fileName, response, workbook);
    }*/

    /**
     * excel下载
     *
     * @param fileName
     * @param response
     * @param workbook
     */
/*
    private static void downloadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    /**
     * excel文件导入
     *
     * @param filePath
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
/*    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/

    /**
     * excel导入
     *
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
/*
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) throws Exception {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        defaultExport(list, fileName, response);
    }

    public static void exportExcel(String fileName, ExportParams exportParams, List<ExcelExportEntity> entities, List<Map<String, Object>> list, HttpServletResponse response) {
        defaultExport(fileName, exportParams, entities, list, response);
    }

    private static void defaultExport(String fileName, ExportParams exportParams, List<ExcelExportEntity> entities, List<Map<String, Object>> list, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entities, list);
        if (workbook != null) ;
        downloadExcel(fileName, response, workbook);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) ;
        downloadExcel(fileName, response, workbook);
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) ;
        downloadExcel(fileName, response, workbook);
    }
*/

/*    public static void DynamicExcel(String fileName, String title, String sheet, List<ExportField> exportField, HttpServletResponse response, List<?> list) {
        //生成动态列
        List<ExcelExportEntity> entities = Lists.newArrayList();
        dynamicNewAddExcel(exportField, entities);
        //组装数据entities
        List<Map<String, Object>> maps = dynamicListDataByKey(exportField, list);
        ExcelUtil.exportExcel(fileName, new ExportParams(title, sheet), entities, maps, response);
    }

    public static void dynamicNewAddExcel(List<ExportField> ef, List<ExcelExportEntity> entities) {
        //单元格的excel 表头
        ef.forEach(item -> {
            //需要合并单元格的表头
            List<ExportField> children = item.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                ExcelExportEntity parent = new ExcelExportEntity(item.getFiledChineseName(), item.getEntityAttrName());
                List<ExcelExportEntity> entitiesChildren = Lists.newArrayList();
                children.forEach(e -> {
                    entitiesChildren.add(new ExcelExportEntity(e.getFiledChineseName(), e.getEntityAttrName(), 30));
                });
                parent.setNeedMerge(true);
                parent.setList(entitiesChildren);
                entities.add(parent);
            } else {
                entities.add(new ExcelExportEntity(item.getFiledChineseName(), item.getEntityAttrName(), 30));
            }
        });
    }

    public static List<Map<String, Object>> dynamicListDataByKey(List<ExportField> ef, List<?> statisData) {
        //最终的数据
        List<Map<String, Object>> datas = new ArrayList<>();
        Map map;
        for (Object t : statisData) {
            map = new HashMap();
            for (int j = 0; j < ef.size(); j++) {
                List<ExportField> children = ef.get(j).getChildren();
                if (!CollectionUtils.isEmpty(children)) {
                    //遍历需要合并单元格的子列
                    traversechildren(map, t, children, ef.get(j).getEntityAttrName());
                } else if (StringUtils.isNotBlank(ef.get(j).getEntityAttrName())) {
                    map.put(ef.get(j).getEntityAttrName(), getFieldValueByName(ef.get(j).getEntityAttrName(), t));
                }
            }
            datas.add(map);
        }
        return datas;
    }

    private static void traversechildren(Map map, Object t, List<ExportField> children, String entityAttrName) {
        ArrayList<Map<String, Object>> childrenMaps = Lists.newArrayList();
        Map<String, Object> childrenMap = new HashMap();
        for (int k = 0; k < children.size(); k++) {
            if (StringUtils.isNotBlank(children.get(k).getEntityAttrName())) {
                childrenMap.put(children.get(k).getEntityAttrName(), getFieldValueByName(children.get(k).getEntityAttrName(), t));
            }
        }
        childrenMaps.add(childrenMap);
        map.put(entityAttrName, childrenMaps);
    }

    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
            return null;
        }
    }*/
}
