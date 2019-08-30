package com.potchr.data.ccode.service.impl;

import com.potchr.data.ccode.entity.ImportedCustomer;
import com.potchr.data.ccode.repository.CustomerRepository;
import com.potchr.data.ccode.service.CustomerImportService;
import com.potchr.util.StringByteUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/20 12:05</p>
 */
@Service
public class CustomerImportServiceImpl implements CustomerImportService {

    @Autowired
    private CustomerRepository customerRepository;

    private SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();

    @Override
    public int importCustomers(String fileRoot) {
        File file = new File(fileRoot);
        Arrays.stream(file.listFiles()).parallel().forEach(provinceFile -> {
            File[] files = provinceFile.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.equals("匹配数据") || name.startsWith("匹配数据");
                }
            });
            if (files == null || files.length == 0) {
                return;
            }
            Arrays.stream(files).parallel().forEach(acceptFile -> {
                File[] importFiles = acceptFile.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return !name.startsWith(".");
                    }
                });
                if (importFiles == null || importFiles.length == 0) {
                    return;
                }
                Arrays.stream(importFiles).parallel().forEach(importFile -> {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(importFile);
                        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                        HSSFSheet sheetAt = workbook.getSheetAt(0);
                        List<ImportedCustomer> importedCustomers = new ArrayList<>();
                        for (Row row : sheetAt) {
                            if (row.getRowNum() == 0) {
                                continue;
                            }
                            ImportedCustomer importedCustomer = new ImportedCustomer();
                            importedCustomer.setCustomerId((Long) keyGenerator.generateKey());
                            String customerName = getCellValue(row, 0).toString();
                            importedCustomer.setCustomerName(StringUtils.stripToNull(customerName));
                            importedCustomer.setStatus(getCellValue(row, 1).toString());
                            importedCustomer.setLegalPerson(getCellValue(row, 2).toString());
                            importedCustomer.setRegisteredCapitalDesc(getCellValue(row, 3).toString());
                            importedCustomer.setFoundDate(getCellValue(row, 4).toString());
                            importedCustomer.setProvince(getCellValue(row, 5).toString());
                            importedCustomer.setCity(getCellValue(row, 6).toString());
                            importedCustomer.setPhoneNumber(getCellValue(row, 7).toString());
                            String morePhoneNo = getCellValue(row, 8).toString();
                            morePhoneNo = StringUtils.strip(morePhoneNo);
                            if (morePhoneNo != null && morePhoneNo.length() > 255) {
                                morePhoneNo = morePhoneNo.substring(0, 255);
                            }
                            importedCustomer.setMorePhoneNumber(morePhoneNo);
                            importedCustomer.setEmail(getCellValue(row, 9).toString());
                            importedCustomer.setCreditCode(getCellValue(row, 10).toString());
                            importedCustomer.setTaxCode(getCellValue(row, 11).toString());
                            String regcode = getCellValue(row, 12).toString();
                            importedCustomer.setRegcode(StringUtils.stripToNull(regcode));
                            importedCustomer.setOrgCode(getCellValue(row, 13).toString());
                            importedCustomer.setEnterpriceType(getCellValue(row, 14).toString());
                            importedCustomer.setIndustry(getCellValue(row, 15).toString());
                            String homepage = getCellValue(row, 16).toString();
                            if (homepage != null && homepage.length() > 255) {
                                homepage = homepage.substring(0, 255);
                            }
                            importedCustomer.setHomePage(homepage);
                            importedCustomer.setAddress(getCellValue(row, 17).toString());
                            importedCustomer.setBusinessScope(getCellValue(row, 18).toString());
                            importedCustomer.setCustomsCode(getCellValue(row, 19).toString());
                            importedCustomers.add(importedCustomer);
                        }
                        List<ImportedCustomer> saveCustomers = new ArrayList<>();
                        for (ImportedCustomer importedCustomer : importedCustomers) {
                            saveCustomers.add(importedCustomer);
                            if (saveCustomers.size() == 1000) {
                                customerRepository.saveAll(saveCustomers);
                                saveCustomers.clear();
                            }
                        }
                        customerRepository.saveAll(saveCustomers);
                        System.out.println("完成一个");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
        });
        System.out.println("都完了");
        return 0;
    }

    private Object getCellValue(Row row, int colIdx) {
        Cell cell = row.getCell(colIdx);
        if (cell == null) {
            return null;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case _NONE:
            case BLANK:
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return new BigDecimal(cell.getNumericCellValue());
            case BOOLEAN:
            case FORMULA:
            case ERROR:
        }
        return null;
    }
}
