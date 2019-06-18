import com.epam.console.ConsoleDataProvider;
import com.epam.console.ConsoleManager;
import com.epam.console.IConsoleManager;
import com.epam.controller.DerbyDataBaseController;
import com.epam.controller.FilterController;
import com.epam.data.RequestToFindOrders;
import com.epam.data.repo.AutoOrderRepository;
import com.epam.domain.AutoOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class TDDApplication {

    public static void main(String... args)
        throws ClassNotFoundException, SQLException, IllegalAccessException {
        IConsoleManager consoleManager = new ConsoleManager();
        new RequestToFindOrders();
        ConsoleDataProvider consoleDataProvider = new ConsoleDataProvider(consoleManager);
        new FilterController(consoleDataProvider);
        DerbyDataBaseController derbyDataBaseController = new DerbyDataBaseController("db_autoservice");
        AutoOrderRepository orderRepository = new AutoOrderRepository(derbyDataBaseController);
        orderRepository.createTable(new AutoOrder());
        List<AutoOrder> orderList = new ArrayList<>();
        orderList.add(new AutoOrder().withId(1).withDate(new Date()).withOwnerName("Alex"));
        orderList.add(new AutoOrder().withId(2).withDate(new Date()).withOwnerName("Peter"));
        orderList.add(new AutoOrder().withId(3).withDate(new Date()).withOwnerName("Morphey"));
        for (AutoOrder order : orderList) {
            orderRepository.add(order);
        }


    }
}
