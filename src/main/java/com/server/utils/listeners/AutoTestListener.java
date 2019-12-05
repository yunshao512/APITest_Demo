package com.server.utils.listeners;


import com.server.api.common.ReporterLogger;
import com.server.utils.assertUtil.AssertMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.*;

public class AutoTestListener extends TestListenerAdapter {

	private static ReporterLogger LOGGET = new ReporterLogger();
	@Override
	public void onTestStart(ITestResult result) {
		AssertMethod.flag = true;
		AssertMethod.errors.clear();
//		log(String.format("[method: %s]",result.getName())+ "--Test method Start\n");
	}


	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		TestngRetry.resetRetryCount();
//		log(String.format("[method: %s]",tr.getName())+ "--Test method Success\n");
		handleAssertion(tr);
		super.onTestSuccess(tr);
	}

	public void onTestFailure(ITestResult tr) {
		saveResult(tr);
//		log(String.format("[method: %s]",tr.getName())+ "--Test method Failure\n");
		handleAssertion(tr);
		super.onTestFailure(tr);
	}

	public void onTestSkipped(ITestResult tr) {
		saveResult(tr);
//		log(String.format("[method: %s]",tr.getName())+ "--Test method Start\n");
		handleAssertion(tr);
		super.onTestSkipped(tr);
	}

	private void saveResult(ITestResult tr) {
		Throwable throwable = tr.getThrowable();
		if (null == throwable) {
			return;
		}
		// String imgPath = WebdriverUtil.captureEntirePageScreenshot();
		// log.error("用例执行错误截图：" + imgPath);
		// Reporter.setCurrentTestResult(tr);
		// Reporter.log("path path path path");
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);

		// List of test results which we will delete later
		ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
		// collect all id's from passed test
		Set<Integer> passedTestIds = new HashSet<Integer>();
		for (ITestResult passedTest : testContext.getPassedTests()
				.getAllResults()) {
			// logger.info("PassedTests = " + passedTest.getName());
			passedTestIds.add(getId(passedTest));
		}

		Set<Integer> failedTestIds = new HashSet<Integer>();
		for (ITestResult failedTest : testContext.getFailedTests()
				.getAllResults()) {
			// logger.info("failedTest = " + failedTest.getName());
			// id = class + method + dataprovider
			int failedTestId = getId(failedTest);

			// if we saw this test as a failed test before we mark as to be
			// deleted
			// or delete this failed test if there is at least one passed
			// version
			if (failedTestIds.contains(failedTestId)
					|| passedTestIds.contains(failedTestId)) {
				testsToBeRemoved.add(failedTest);
			} else {
				failedTestIds.add(failedTestId);
			}
		}

		// finally delete all tests that are marked
		for (Iterator<ITestResult> iterator =

             testContext.getFailedTests().getAllResults().iterator(); iterator
				.hasNext();) {
			ITestResult testResult = iterator.next();

			if (testsToBeRemoved.contains(testResult)) {
				// logger.info("Remove repeat Fail Test: " +
				// testResult.getName());
				iterator.remove();
			}
		}

	}

	private int getId(ITestResult result) {
		int id = result.getTestClass().getName().hashCode();
		id = id + result.getMethod().getMethodName().hashCode();
		id = id
				+ (result.getParameters() != null ? Arrays.hashCode(result
						.getParameters()) : 0);
		return id;
	}


	private int index = 0;

	private void handleAssertion(ITestResult tr){
		if(!AssertMethod.flag){
			Throwable throwable = tr.getThrowable();
			if(throwable==null){
				throwable = new Throwable();
			}
			StackTraceElement[] traces = throwable.getStackTrace();
			StackTraceElement[] alltrace = new StackTraceElement[0];
			for (Error e : AssertMethod.errors) {
				StackTraceElement[] errorTraces = e.getStackTrace();
				StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
				StackTraceElement[] message = new StackTraceElement[]{new StackTraceElement("message : "+e.getMessage()+" in method : ", tr.getMethod().getMethodName(), tr.getTestClass().getRealClass().getSimpleName(), index)};
				index = 0;
				alltrace = this.merge(alltrace, message);
				alltrace = this.merge(alltrace, et);
			}
			if(traces!=null){
				traces = this.getKeyStackTrace(tr, traces);
				alltrace = this.merge(alltrace, traces);
			}
			throwable.setStackTrace(alltrace);
			tr.setThrowable(throwable);
			AssertMethod.flag = true;
			AssertMethod.errors.clear();
			tr.setStatus(ITestResult.FAILURE);
		}
	}

	private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
		List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){
				ets.add(stackTraceElement);
				index = stackTraceElement.getLineNumber();
			}
		}
		StackTraceElement[] et = new StackTraceElement[ets.size()];
		for (int i = 0; i < et.length; i++) {
			et[i] = ets.get(i);
		}
		return et;
	}

	private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){
		StackTraceElement[] ste = new StackTraceElement[traces1.length+traces2.length];
		for (int i = 0; i < traces1.length; i++) {
			ste[i] = traces1[i];
		}
		for (int i = 0; i < traces2.length; i++) {
			ste[traces1.length+i] = traces2[i];
		}
		return ste;
	}
}
