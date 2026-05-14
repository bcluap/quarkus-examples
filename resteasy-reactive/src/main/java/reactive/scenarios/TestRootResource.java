package reactive.scenarios;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
@Path("/X")
@Transactional
@RunOnVirtualThread
public class TestRootResource {

    private static final Logger log = LoggerFactory.getLogger(TestRootResource.class);

    @Inject
    DataSource ds;

    @GET
    public String getIsolation() throws SQLException {
        try (Connection c = ds.getConnection()) {
            return c.getTransactionIsolation() + "";
        }
    }
}
