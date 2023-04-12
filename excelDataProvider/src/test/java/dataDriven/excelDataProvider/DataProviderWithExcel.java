package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderWithExcel 
{	
	DataFormatter df= new DataFormatter();
	@Test(dataProvider ="driverTest")
	public void testData(String s1,String s2,String s3)
	{
		System.out.println(s1 +" "+s2 +" "+ s3);
			
	}
		
	@DataProvider(name="driverTest")
	public Object[][] getData() throws IOException
	{
		FileInputStream fis =new FileInputStream("D://SachinWorkspace//excelDataProvider//Resources//excelDriven.xlsx");
		XSSFWorkbook  wb=new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		//System.out.println(rowCount);		
		XSSFRow row = sheet.getRow(0); 
		int lastCol = row.getLastCellNum();
		//System.out.println("lastCol:::"+lastCol);
		Object data[][] = new Object[rowCount-1][lastCol];
		for(int i=0;i<rowCount-1;i++)  //00 01 02   10 11 12    0<3
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<lastCol;j++)
			{
				//XSSFCell cell = row.getCell(j);
				data[i][j]= df.formatCellValue(row.getCell(j));
				//System.out.println();
			}			
		}
		return data;
		
		//XSSFCell cell = row.getCell(0);
		//System.out.println(cell.getStringCellValue());
		 
		//Object[][] data= {{"hello","test1",1},{"hi","test2",2},{"hey","test3",3}};
		//return data;		
	}
}