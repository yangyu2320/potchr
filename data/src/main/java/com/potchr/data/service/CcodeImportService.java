package com.potchr.data.service;

import com.google.common.collect.Lists;
import com.potchr.data.snm.dao.CcodeDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CcodeImportService {

    @Autowired
    private CcodeDAO ccodeDAO;

    @Autowired
    @Qualifier("customerJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("snmJdbcTemplate")
    private JdbcTemplate snmJdbcTemplate;
    @Autowired
    @Qualifier("snmrNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate snmNamedJdbcTemplate;

    public int countRepeat() {
        List<Map<String, Object>> allCcodes = ccodeDAO.findAll();
        int count = 0;
        for (Map<String, Object> allCcode : allCcodes) {
            String cname = (String) allCcode.get("cname");
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT customer_id FROM precise_customer WHERE customer_name = '" + cname + "'");
            if (maps != null && maps.size() > 0) {
                count++;
            }
        }
        return count;
    }

    public void importCcode() {
        Map<String, Object>[] allCcodes = ccodeDAO.findAll().toArray(new LinkedCaseInsensitiveMap[0]);
        int code = 904842;
        //1317
        for (int i = 857; i < 1317; i++) {
            int past = 1000 * i;
            String sql = "SELECT * FROM precise_customer WHERE customer_name IS NOT NULL ORDER BY customer_id LIMIT " + past + ",1000";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            List<Object[]> params = new ArrayList<>();
            for (Map<String, Object> map : maps) {
                Map<String, Object> ccode = findCcode(allCcodes, map);
                if (ccode == null) {
                    Object[] param = ccodeConvert(makeInnerCode(++code), map);
                    params.add(param);
                }
            }
            snmJdbcTemplate.batchUpdate(insertSql, params);
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Lists.newArrayList("a", "b", "c").stream().collect(Collectors.joining(",", "(", ")")));
    }

    public void updateCcode() {
        List<Map<String, Object>> allCcodes = ccodeDAO.findAll();
        String nameFilter = allCcodes.stream().map(ccodeMap -> "'" + ccodeMap.get("cname") + "'").collect(Collectors.joining(",", "(", ")"));
        Map<String, Object>[] customers = jdbcTemplate.queryForList("SELECT * FROM precise_customer WHERE customer_name IN " + nameFilter + " ORDER BY customer_name").toArray(new LinkedCaseInsensitiveMap[0]);
        int code = 52036;
        //1317
        List<Object[]> params = new ArrayList<>();
        for (int i = 0; i < allCcodes.size(); i++) {
            Map<String,Object> allCcode = allCcodes.get(i);
            Map<String, Object> ccodex = findCcodex(customers, allCcode);
            if (ccodex != null)
            {
                Object[] param = ccodeUpdateConvert((String) allCcode.get("ccode"), ccodex);
                params.add(param);
            }
            if (i != 0 && i%1000 == 0)
            {
                if (params.size() > 0) {
                    snmJdbcTemplate.batchUpdate(updateSql, params);
                    params.clear();
                }
                System.out.println(i + "/" + allCcodes.size());
            }
        }
        if (params.size() > 0) {
            snmJdbcTemplate.batchUpdate(updateSql, params);
        }
        System.out.println("完成");
    }

    private Map<String, Object> findCcodex(Map<String, Object>[] customers, Map<String, Object> ccode) {
        int index = Arrays.binarySearch(customers, ccode, (o1, o2) -> {
            String cname = (String) o1.get("customer_name");
            String customerName = (String) o2.get("cname");
            return cname.compareTo(customerName);
        });
        if (index >= 0) {
            return customers[index];
        }
        return null;
    }

    private Map<String, Object> findCcode(Map<String, Object>[] allCcodes, Map<String, Object> ccode) {
        int index = Arrays.binarySearch(allCcodes, ccode, (o1, o2) -> {
            String cname = (String) o1.get("cname");
            String customerName = (String) o2.get("customer_name");
            return cname.compareTo(customerName);
        });
        if (index >= 0) {
            return allCcodes[index];
        }
        return null;
    }

    private String updateSql =
            "UPDATE ccode SET nature=?, " +
                    "taxno=?, " +
                    "ncode=?, " +
                    "legalperson=?, " +
                    "foundingtime=?, " +
                    "homepage=?, " +
                    "address=?," +
                    "telphone=?, " +
                    "email=?, " +
                    "busiscope=?," +
                    "taxcode=?, " +
                    "regcode=?, " +
                    "custcode=?, " +
                    "entercode=?, " +
                    "capitaldesc=?, " +
                    "moretelphone=?, " +
                    "industrydesc=?, " +
                    "busistatus=?, " +
                    "isimport=? " +
                    "WHERE ccode=?";

    private Object[] ccodeUpdateConvert(String ccodeVal, Map<String, Object> ccode) {
        //nature, taxno, ncode, legalperson, foundingtime, homepage, address, telphone, email, busiscope
        //taxcode, regcode, custcode, entercode, capitaldesc, moretelphone, industrydesc, busistatus, isimport
        Object[] newCcode = new Object[20];
        newCcode[0] = ccode.get("enterprice_type");
        newCcode[1] = ccode.get("credit_code");
        newCcode[2] = ccode.get("ncode");
        newCcode[3] = ccode.get("legal_person");
        newCcode[4] = ccode.get("found_date_date");
        newCcode[5] = ccode.get("home_page");
        newCcode[6] = ccode.get("address");
        newCcode[7] = ccode.get("phone_number");
        newCcode[8] = ccode.get("email");
        newCcode[9] = ccode.get("business_scope");
        newCcode[10] = ccode.get("tax_code");
        newCcode[11] = ccode.get("regcode");
        newCcode[12] = ccode.get("customs_code");
        newCcode[13] = ccode.get("org_code");
        newCcode[14] = ccode.get("registered_capital_desc");
        newCcode[15] = ccode.get("more_phone_number");
        newCcode[16] = ccode.get("industry");
        newCcode[17] = ccode.get("status");
        newCcode[18] = 2;
        newCcode[19] = ccodeVal;
        return newCcode;
    }

    private String insertSql =
            "INSERT INTO ccode (ccode, cname, nature, type, taxno, ncode, legalperson, foundingtime, homepage, address, telphone, status, sheetcode, vprepare, predate, modifier, modifydate, cuicode, email, busiscope, taxcode, regcode, custcode, entercode, capitaldesc, moretelphone, industrydesc, busistatus, isimport) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

    private String makeInnerCode(int code) {
        String codeVal = code + "";
        return "C" + StringUtils.repeat('0', 9 - codeVal.length()) + codeVal;
    }

    //C000052036
    private Object[] ccodeConvert(String ccodeVal, Map<String, Object> ccode) {
        //ccode, cname, nature, type, taxno, ncode, legalperson, foundingtime, homepage, address
        //telphone, status, sheetcode, vprepare, predate, modifier, modifydate, cuicode, email, busiscope
        //taxcode, regcode, custcode, entercode, capitaldesc, moretelphone, industrydesc, busistatus, isimport
        Object[] newCcode = new Object[29];
        newCcode[0] = ccodeVal;
        newCcode[1] = ccode.get("customer_name");
        newCcode[2] = ccode.get("enterprice_type");
        newCcode[3] = "10";
        newCcode[4] = ccode.get("credit_code");
        newCcode[5] = ccode.get("ncode");
        newCcode[6] = ccode.get("legal_person");
        newCcode[7] = ccode.get("found_date_date");
        newCcode[8] = ccode.get("home_page");
        newCcode[9] = ccode.get("address");
        newCcode[10] = ccode.get("phone_number");
        newCcode[11] = "10";
        newCcode[12] = "SNM-CRM.CcodePub";
        newCcode[13] = "U000000000";
        newCcode[14] = new Date();
        newCcode[15] = "U000000000";
        newCcode[16] = new Date();
        newCcode[17] = "C000000001";
        newCcode[18] = ccode.get("email");
        newCcode[19] = ccode.get("business_scope");
        newCcode[20] = ccode.get("tax_code");
        newCcode[21] = ccode.get("regcode");
        newCcode[22] = ccode.get("customs_code");
        newCcode[23] = ccode.get("org_code");
        newCcode[24] = ccode.get("registered_capital_desc");
        newCcode[25] = ccode.get("more_phone_number");
        newCcode[26] = ccode.get("industry");
        newCcode[27] = ccode.get("status");
        newCcode[28] = 1;
        return newCcode;
    }
}
