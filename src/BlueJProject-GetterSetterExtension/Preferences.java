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
    private BlueJ bluej;
    public static final String PROFILE_LABEL = "param_name_suffix";
    public static final String PROFILE_LABEL2 = "doc_language";

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
    }

    public void loadValues()
    {
        // Load the property value from the BlueJ properties file,
        // default to "Neu"
        paramNameTextfield.setText(bluej.getExtensionPropertyString(PROFILE_LABEL, "Neu"));
        docLanguageCombobox.setValue(bluej.getExtensionPropertyString(PROFILE_LABEL2, "German"));
    }
}