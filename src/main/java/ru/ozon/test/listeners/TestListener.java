package ru.ozon.test.listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.ozon.test.config.Screenshots;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result){
        Screenshots.takeScreenshot();
    }
}