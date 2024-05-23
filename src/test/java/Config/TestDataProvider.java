package Config;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "excelData")
    public Object[][] readExcelData() {
        String filePath = System.getProperty("user.dir")+"\\Data.xlsx";
        String sheetName = "Sheet1";
        return ExcelUtils.getExcelData(filePath, sheetName);
    }
}