package movier;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class db_test {
	@Inject
	private DataSource dataSource;
	@Test
	public void test() throws SQLException {
		try {
			Connection connection = dataSource.getConnection();
			log.info(connection.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
