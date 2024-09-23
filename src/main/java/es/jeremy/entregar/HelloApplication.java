package es.jeremy.entregar;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.control.Tooltip;

/**
 * HelloApplication es una aplicación JavaFX que permite agregar, eliminar y restaurar filas en una tabla
 * que muestra detalles de personas.
 */
public class HelloApplication extends Application {

    private TextField fNameField;  // Campo para el nombre
    private TextField lNameField;  // Campo para el apellido
    private DatePicker dobField;   // Selector de fecha de nacimiento
    private TableView<Person> table; // Tabla para mostrar las personas

    /**
     * Punto de entrada principal para la aplicación.
     *
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    @SuppressWarnings("unchecked")
    /**
     * Método llamado al iniciar la aplicación.
     *
     * @param stage El escenario principal de la aplicación
     */
    public void start(Stage stage) {
        fNameField = new TextField();
        lNameField = new TextField();
        dobField = new DatePicker();
        table = new TableView<>(PersonTableUtil.getPersonList());

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(
                PersonTableUtil.getIdColumn(),
                PersonTableUtil.getFirstNameColumn(),
                PersonTableUtil.getLastNameColumn(),
                PersonTableUtil.getBirthDateColumn()
        );

        GridPane newDataPane = getNewPersonDataPane();

        Button restoreBtn = new Button("Restore Rows");
        restoreBtn.setOnAction(e -> restoreRows());
        Tooltip.install(restoreBtn, new Tooltip("Restore deleted rows"));

        Button deleteBtn = new Button("Delete Selected Rows");
        deleteBtn.setOnAction(e -> deleteSelectedRows());
        Tooltip.install(deleteBtn, new Tooltip("Delete selected rows"));

        VBox root = new VBox(newDataPane, new HBox(restoreBtn, deleteBtn), table);
        root.setSpacing(5);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Adding/Deleting Rows in a TableView");
        stage.show();
    }

    /**
     * Crea un panel de entrada para los detalles de una nueva persona.
     *
     * @return Un GridPane que contiene los campos de entrada y el botón "Agregar".
     */
    public GridPane getNewPersonDataPane() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        pane.addRow(0, new Label("First Name:"), fNameField);
        pane.addRow(1, new Label("Last Name:"), lNameField);
        pane.addRow(2, new Label("Birth Date:"), dobField);

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addPerson());
        Tooltip.install(addBtn, new Tooltip("Add a new person"));

        pane.add(addBtn, 2, 0);
        return pane;
    }

    /**
     * Elimina las filas seleccionadas en la tabla.
     */
    public void deleteSelectedRows() {
        if (table.getSelectionModel().isEmpty()) {
            System.out.println("Please select a row to delete.");
            return;
        }
        ObservableList<Integer> list = table.getSelectionModel().getSelectedIndices();
        Integer[] selectedIndices = list.toArray(new Integer[0]);
        Arrays.sort(selectedIndices);
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            table.getItems().remove((int) selectedIndices[i]);
        }
    }

    /**
     * Restaura todas las filas en la tabla a su estado original.
     */
    public void restoreRows() {
        table.getItems().clear();
        table.getItems().addAll(PersonTableUtil.getPersonList());
    }

    /**
     * Crea una nueva instancia de Person utilizando los datos de entrada.
     *
     * @return Un objeto Person con los datos ingresados.
     */
    public Person getPerson() {
        return new Person(fNameField.getText(), lNameField.getText(), dobField.getValue());
    }

    /**
     * Agrega una nueva persona a la tabla.
     * Si los campos están vacíos, muestra un mensaje de advertencia.
     */
    public void addPerson() {
        String firstName = fNameField.getText();
        String lastName = lNameField.getText();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Please fill in both First Name and Last Name.");
            return; // No agregar si están vacíos
        }

        Person p = getPerson();
        table.getItems().add(p);
        clearFields();
    }

    /**
     * Limpia los campos de entrada.
     */
    public void clearFields() {
        fNameField.setText(null);
        lNameField.setText(null);
        dobField.setValue(null);
    }
}

