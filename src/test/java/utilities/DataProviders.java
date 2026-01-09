package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider1
	
	@DataProvider(name="LoginData")
		public String [][] getData() throws IOException
		{
		String path = System.getProperty("user.dir")+"\\testData\\LoginDataFile.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path); //Creating an object for ExcelUtility
	
		int rownum=xlutil.getRowCount("Sheet1");
		int colnum = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[rownum][colnum];//created for 2 dimensional array to store excel data
		for(int i=1; i<=rownum ;i++)
		{
			for(int j=0; j<colnum; j++)
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i,j);
			}
		}
		return logindata;
	}

}
