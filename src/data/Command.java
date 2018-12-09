package data;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Logger;

/**
 *
 * @script for execute commands
 */
public class Command {
    
    Formatter output;
    Util aux = new Util();
    static final Logger log = Logger.getLogger(Command.class.getName());
    
    public void executeCommand(String command, String caminho, boolean isFile, int numberExec) throws IOException
    {
        /**
         * @numberExec é para controlar um loop for ao escrever no arquivo
         */
        if(isFile == true && numberExec == 1)
            aux.createFile(caminho);
        
        final ArrayList<String> commands = new ArrayList<>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(command);
         
        BufferedReader br = null;        
         
        try {                        
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
             
            String line;            
            while((line = br.readLine()) != null) {
                
                if(isFile == true)
                {
                    System.out.println(line);
                    aux.writeFile(caminho, line);
                }
            }
        } catch (IOException ioe) {
            log.severe(ioe.getMessage());
            throw ioe;
        } finally {
            secureClose(br);
        }
    }
    
    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe(ex.getMessage());
        }
    }
    
}
