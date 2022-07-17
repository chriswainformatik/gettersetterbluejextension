import java.lang.reflect.*;

import bluej.extensions2.BlueJ;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/**
 * Use API java.lang.reflect to generate (only if no exist)
 * 1) All GET/SET methods
 * 2) GET or SET method of one propertie 
 * 
 * @author (Milton Jes&uacute;s Vera Contreras - miltonjesusvc@ufps.edu.co) 
 * @version 0.0000000000000001 --> Math.sin(Math.PI-Double.MIN_VALUE) --> :)
 */
public class CodeGenerator
{
    private BlueJ bluej;

    /**Default Constructor*/
    public CodeGenerator()
    {
    }

    public CodeGenerator(BlueJ bluej) {
        this.bluej = bluej;
    }

    /**
     * Generate all GET/SET methods of theClass
     * @param theClass the java.lang.reflect Class
     * @return source code GET/SET methods 
     * 
     */
    public String generateGetterSetter(Class theClass)
    {
        Field [] fields = theClass.getDeclaredFields();
        StringBuffer code = new StringBuffer();

        for(int i=0; i<fields.length;i++)
        {
            code.append(this.generateGetter(theClass,fields[i]));
            if(!Modifier.isFinal(fields[i].getModifiers()))
             code.append(this.generateSetter(theClass,fields[i]));
        }//end for fields

        return code.toString();
    }//fin generateGetterSetter

    /**
     * Generate GET methods of theClass and field
     * @param theClass the java.lang.reflect Class
     * @param field the java.lang.reflect Filed
     * @return source code GET method to theClass and field
     * 
     */
    private String generateGetter(Class theClass, Field field)
    {
        Method method;
        StringBuffer code = new StringBuffer();
        String nameField=null, nameMethod=null, nameType=null;
        nameField = field.getName().substring(0,1).toUpperCase() + (field.getName().length() > 1 ? field.getName().substring(1):"");
        nameMethod = "get"+nameField;
        if(field.getGenericType() instanceof ParameterizedType)
            nameType = field.getGenericType().toString();
        else
            nameType = field.getType().getSimpleName();
        try{
            method = theClass.getDeclaredMethod(nameMethod, new Class[]{});
        }
        catch(Exception e){
            String docLanguage = bluej.getExtensionPropertyString("doc_language", "German");
            String documentation = "";
            if (docLanguage.equals("German"))
                documentation = "Getter-Methode fuer: ";
            else if (docLanguage.equals("English"))
                documentation = "Getter method for: ";
            code.append("\n");
            code.append("    /**\n * " + docLanguage + field.getName() + "\n*/\n");
            code.append("    public "+nameType+ " " + nameMethod+"(){\n");
            code.append("        return "+field.getName()+";\n");
            code.append("    }\n\n");
        }//end catch not found getter
        return code.toString();
    }//end generateGetter

    /**
     * Generate GET methods of theClass and propertie
     * @param theClass the java.lang.reflect Class
     * @param propertie an propertie name
     * @return source code GET method to theClass and propertie
     * 
     */
    public String generateGetter(Class theClass, String propertie)
    {
        try{
            return generateGetter(theClass, theClass.getDeclaredField(propertie));
        }catch(Exception e){
            GetterSetterExtension.showError(e);
            return "";
        }
    }//end generateGetter

    /**
     * Generate SET methods of theClass and propertie
     * @param theClass the java.lang.reflect Class
     * @param propertie an propertie name
     * @return source code SET method to theClass and propertie
     * 
     */
    public String generateSetter(Class theClass, String propertie)
    {
        try{
            return generateSetter(theClass, theClass.getDeclaredField(propertie));
        }catch(Exception e){
            GetterSetterExtension.showError(e);           
            return "";
        }

    }//end generateSetter   

    /**
     * Generate SET methods of theClass and field
     * @param theClass the java.lang.reflect Class
     * @param field the java.lang.reflect Field
     * @return source code SET method to theClass and field
     * 
     */
    private String generateSetter(Class theClass, Field field)
    {
        Method method;
        StringBuffer code = new StringBuffer();
        String nameField=null, nameMethod=null, nameType=null;
        nameField = field.getName().substring(0,1).toUpperCase() + (field.getName().length() > 1 ? field.getName().substring(1):"");
        nameMethod = "set"+nameField;
        if(field.getGenericType() instanceof ParameterizedType)
            nameType = field.getGenericType().toString();
        else
            nameType = field.getType().getSimpleName();
        try{
            method = theClass.getDeclaredMethod(nameMethod, new Class[]{field.getType()});
        }
        catch(Exception e){
            String paramNameSuffix = bluej.getExtensionPropertyString("param_name_suffix", "Neu");
            code.append("\n");
            code.append("    /**\n * Setter Method for: " + field.getName()+"\n *\n * @param "+ field.getName() + paramNameSuffix + " New value for attribute "+field.getName()+"\n */\n");
            code.append("    public void"+ " " + nameMethod+"("+nameType+" "+ field.getName() + paramNameSuffix + "){\n");
            code.append("        "+field.getName()+" = " + field.getName() + paramNameSuffix + ";\n");
            code.append("    }\n\n");
        }//end catch not found setter
        return code.toString();
    }//end generateSetter   

}//fin class CodeGenerator
