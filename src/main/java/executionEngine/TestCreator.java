package executionEngine;

import java.io.PrintWriter;

import org.apache.log4j.xml.DOMConfigurator;


import config.Constants;
import utility.ExcelUtils;
import utility.Log;



public class TestCreator {
	public static void main(String[] args) throws Exception {
		ExcelUtils.setExcelFile(Constants.Path_TestData);
		DOMConfigurator.configure("log4j.xml");

		TestCreator startEngine = new TestCreator();
		startEngine.createTestCase();

	}

	private void createTestCase() throws Exception {
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
			boolean bResult = true;

			String sTestCaseID=ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
			String sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);


			PrintWriter stepWriter = new PrintWriter("src\\test\\java\\Test"+sTestCaseID+".java", "UTF-8");

			stepWriter.println("import java.io.IOException;");
			stepWriter.println("import org.junit.Before;");
			stepWriter.println("import org.junit.Test;");
			stepWriter.println("import config.ActionKeywords;");
			stepWriter.println("public class Test"+sTestCaseID+" {");
			stepWriter.println("ActionKeywords keywords;");
			stepWriter.println("@Before");
			stepWriter.println("public void init() throws IOException");
			stepWriter.println("{");
			stepWriter.println("keywords=new ActionKeywords();");
			stepWriter.println("}");

			if (sRunMode.equals("Yes")){
				Log.startTestCase(sTestCaseID);
				int iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				int iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				bResult=true;
				String actionKeyword,pageObject,data;

				stepWriter.println(" @Test");
				stepWriter.println("public void " + sTestCaseID.replace(" ", "_") + "() throws Throwable {");
				for (;iTestStep<=iTestLastStep;iTestStep++)
				{
					actionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
					pageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
					data = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
					createStep(stepWriter,actionKeyword,pageObject,data);

					if(bResult==false){
						ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
						Log.endTestCase(sTestCaseID);
						break;
					}						


				}
				stepWriter.println("}");
			}
			stepWriter.println("}");
			stepWriter.close();						
		}
	}

	private boolean createStep(PrintWriter stepDefinitions, String sActionKeyword, String sPageObject, String sData) {
		String strAction = "";
		if(sActionKeyword.equalsIgnoreCase("navigate"))
		{
			stepDefinitions.println("keywords.navigate(\"" +sData+"\");");
		}
		else if(sActionKeyword.equalsIgnoreCase("openBrowser"))
		{
			stepDefinitions.println("keywords.openBrowser(\"" +sData+"\");");
		}
		else if(sActionKeyword.equalsIgnoreCase("click"))
		{
			strAction = "keywords.click(\"" +sPageObject;
			if(sData!= null && !sData.isEmpty())
				strAction += "\", \"" + sData +"\");";
			else
				strAction += "\");";
			stepDefinitions.println(strAction);
		}
		else if(sActionKeyword.equalsIgnoreCase("input"))
		{
			stepDefinitions.println("keywords.input(\"" +sPageObject+"\",\""+sData+"\");");
		}
		else if(sActionKeyword.equalsIgnoreCase("checkElementExists"))
		{
			strAction = "keywords.checkElementExists(\"" +sPageObject;
			if(sData!= null && !sData.isEmpty())
				strAction += "\", \"" + sData +"\");";
			else
				strAction += "\");";
			stepDefinitions.println(strAction);			
		}
		return true;
	}	
}
