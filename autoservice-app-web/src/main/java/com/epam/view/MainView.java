package com.epam.view;

import com.epam.endpoints.AutoOrder;
import com.epam.jersey.client.AutoOrderClient;
import com.epam.view.components.AutoOrderEditor;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

    private final AutoOrderClient autoOrderClient = new AutoOrderClient();
    private final Grid<AutoOrder> gridAutoOrder = new Grid<>(AutoOrder.class);
    private final TextField filter = new TextField("", "Type id to filter");

    private final AutoOrderEditor editor = new AutoOrderEditor(autoOrderClient);

    @Autowired
    public MainView() {
//        gridAutoOrder.setHeight("300px");
        gridAutoOrder.setColumns("orderId", "orderDate", "ownerName");
//        gridAutoOrder.getColumnByKey("orderId").setWidth("120px").setFlexGrow(0);

        filter.focus();
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        Button searchBttn = new Button("Search", VaadinIcon.SEARCH.create());
        searchBttn.addClickListener(e -> showAutoOrders(filter.getValue()));
        filter.addKeyPressListener(Key.ENTER, e -> showAutoOrders(filter.getValue()));

        Button addNewBttn = new Button("Add new", VaadinIcon.PLUS.create());
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.add(filter, searchBttn, addNewBttn);

        this.add(toolbar, editor, gridAutoOrder);

        gridAutoOrder.asSingleSelect().addValueChangeListener(e -> editor.editOrder(e.getValue()));

        editor.setChangeHandler(() -> editor.editOrder(new AutoOrder()));
        addNewBttn.addClickListener(e -> editor.editOrder(new AutoOrder()));
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            showAutoOrders(filter.getValue());
        });
        showAutoOrders(null);
    }

    private void showAutoOrders(String id) {
        if (id == null || StringUtils.isEmpty(id)) {
            gridAutoOrder.setItems(autoOrderClient.findAll());
        } else {
            gridAutoOrder.setItems(autoOrderClient.findById(id));
        }

    }
}
