package data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Formatter;
import javax.swing.JFileChooser;

/**
 *
 * @utility methods
 */
public class Util {
    
    static Formatter output;
    
    public void createFile(String nomeArquivo)
    {
        try
        {
            output = new Formatter(nomeArquivo);
        }
        catch(SecurityException securityException)
        {
            System.err.println(securityException);
            System.exit(1);
        }
        catch(FileNotFoundException fileNotFoundException)
        {
            System.err.println(fileNotFoundException);
            System.exit(1);
        }
    }
    
    public void closeFile()
    {
        if(output != null)
            output.close();
    }
    
    //Registrar dados em um arquivo sobreescrevendo as informações
    //Usado para criar um colocar informações me um arquivo formatado
    public void registerFile(String registro)
    {
        output.format(registro);
    }                              
    
    //Usado para guardar as linhas do output do teste em execução
    //Escreve em um arquivo de texto a partir do final do arquivo
    public void writeFile(String path, String texto)
    {
	try
        {// O parametro é que indica se deve sobrescrever ou continua no arquivo.
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(texto);
            conexao.newLine();
            conexao.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
	}
    }
    
    //Método para selecionar a pasta onde salvar os arquivos
    public String saveLocation()
    {
        String path;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar em");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().toString();
        } else {
            path = System.getProperty("user.home")+"/Downloads/";
        }
        
        return path;
    }
    
    //Quebra arquivo
    
    
}
