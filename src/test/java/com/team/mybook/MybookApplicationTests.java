package com.team.mybook;

import com.team.mybook.controller.BookControllerTest;
import com.team.mybook.controller.UserControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		BookControllerTest.class,
		UserControllerTest.class
})
public class MybookApplicationTests {
}
