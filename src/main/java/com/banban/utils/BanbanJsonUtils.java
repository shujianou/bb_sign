package com.banban.utils;

/**
 * Created by Vim 2019/4/18 12:12
 *
 * @author Vim
 */
public class BanbanJsonUtils {

    public static void main(String[] args) {
        String jsonStr = "%7B%22commonParam%22%3A%7B%22appKey%22%3A%22bbl40c2n1uq5zcvcvz%22%2C%22appVersion%22%3A%221.0.1%22%2C%22channel%22%3Anull%2C%22language%22%3A%22zh%22%2C%22longOrgId%22%3A1%2C%22orgId%22%3A%220b446420687fff4201689d831ca90013%22%2C%22originatorId%22%3A121100%2C%22page%22%3A1%2C%22pagesize%22%3A10%2C%22propertyId%22%3A1%2C%22sign%22%3A%227C736FED11308F6FC2401C05A0A9540D%22%2C%22token%22%3A%227c69aaf7629a26d97148c9eaf985ffb8%22%7D%2C%22object%22%3A%7B%22companyId%22%3A%220b446420687fff4201689d831ca90013%22%2C%22typeId%22%3A1%2C%22userId%22%3A121100%7D%7D";

        jsonStr = jsonStr.replaceAll("%7B", "{")
                .replaceAll("%22", "\"")
                .replaceAll("%3A", ":")
                .replaceAll("%2C", ",")
                .replaceAll("%7D", "}");

        System.out.println(jsonStr);
    }
}
