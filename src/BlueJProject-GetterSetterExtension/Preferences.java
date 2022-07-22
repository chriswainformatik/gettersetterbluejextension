import bluej.extensions2.BlueJ;
import bluej.extensions2.PreferenceGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

/***
 * Wrapper to BlueJ Extension Preferences Panel 
 */
class Preferences implements PreferenceGenerator
{
    private Pane myPane;
    private TextField paramNameTextfield;
    private ComboBox docLanguageCombobox;
    private ComboBox methodLanguageCombobox;
    private BlueJ bluej;
    public static final String PROFILE_LABEL = "param_name_suffix";
    public static final String PROFILE_LABEL2 = "doc_language";
    public static final String PROFILE_LABEL3 = "method_language";

    // Construct the panel, and initialise it from any stored values
    public Preferences(BlueJ bluej)
    {
        this.bluej = bluej;
        myPane = new Pane();
        VBox vboxContainer = new VBox();
        vboxContainer.getChildren().add(new Label("Milton Jesús Vera Contreras miltonjesusvc@ufps.edu.co | miljeveco@gmail.com"));
        vboxContainer.getChildren().add(new Label("forked and edited by: Christopher Wagner christopher.wagner@klg.muenchen.musin.de"));

        HBox hboxContainer = new HBox();
        Label paramNameLabel = new Label("Suffix for parameter names: ");
        paramNameLabel.setPadding(new Insets(0, 15, 0, 0));
        paramNameTextfield = new TextField("Neu");
        hboxContainer.getChildren().add(paramNameLabel);
        hboxContainer.getChildren().add(paramNameTextfield);
        hboxContainer.setPadding(new Insets(5, 0, 5, 0));
        hboxContainer.setAlignment(Pos.CENTER_LEFT);
        vboxContainer.getChildren().add(hboxContainer);

        HBox hboxContainer2 = new HBox();
        Label docLanguageLabel = new Label("Language of documentation text: ");
        docLanguageLabel.setPadding(new Insets(0, 15, 0, 0));
        docLanguageCombobox = new ComboBox();
        docLanguageCombobox.getItems().add("German");
        docLanguageCombobox.getItems().add("English");
        docLanguageCombobox.setValue("German");
        hboxContainer2.getChildren().add(docLanguageLabel);
        hboxContainer2.getChildren().add(docLanguageCombobox);
        hboxContainer2.setPadding(new Insets(5, 0, 5, 0));
        hboxContainer2.setAlignment(Pos.CENTER_LEFT);
        vboxContainer.getChildren().add(hboxContainer2);

        HBox hboxContainer3 = new HBox();
        Label methodLanguageLabel = new Label("Language of method name: ");
        methodLanguageLabel.setPadding(new Insets(0, 15, 0, 0));
        methodLanguageCombobox = new ComboBox();
        methodLanguageCombobox.getItems().add("German");
        methodLanguageCombobox.getItems().add("English");
        methodLanguageCombobox.setValue("German");
        hboxContainer3.getChildren().add(methodLanguageLabel);
        hboxContainer3.getChildren().add(methodLanguageCombobox);
        hboxContainer3.setPadding(new Insets(5, 0, 5, 0));
        hboxContainer3.setAlignment(Pos.CENTER_LEFT);
        vboxContainer.getChildren().add(hboxContainer3);

        myPane.getChildren().add(vboxContainer);
        // Load the default value
        loadValues();
    }

    public Pane getWindow()
    {
        return myPane;
    }

    public void saveValues()
    {
        // Save the preference value in the BlueJ properties file
        bluej.setExtensionPropertyString(PROFILE_LABEL, paramNameTextfield.getText());
        bluej.setExtensionPropertyString(PROFILE_LABEL2, docLanguageCombobox.getValue().toString());
        bluej.setExtensionPropertyString(PROFILE_LABEL2, methodLanguageCombobox.getValue().toString());
    }

    public void loadValues()
    {
        // Load the property value from the BlueJ properties file,
        // default to "Neu"
        paramNameTextfield.setText(bluej.getExtensionPropertyString(PROFILE_LABEL, "Neu"));
        docLanguageCombobox.setValue(bluej.getExtensionPropertyString(PROFILE_LABEL2, "German"));
        methodLanguageCombobox.setValue(bluej.getExtensionPropertyString(PROFILE_LABEL2, "German"));
    }
}