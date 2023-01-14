package me.iskak.calculator.suite;


import me.iskak.calculator.test.SearchTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({SearchTest.class})
public class SmokeSuite {
}
