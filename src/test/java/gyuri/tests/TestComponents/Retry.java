package gyuri.tests.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count = 0;
	int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count < maxRetryCount) {
			count++;
            System.out.println("Retrying test case: " + result.getName() + " due to: " + result.getThrowable().getMessage());
            return true;
		}
		return false;
	}

}
