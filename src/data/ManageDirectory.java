package data;

import java.io.IOException;

public class ManageDirectory {
    
    Command exec = new Command();
    
    public void createDirectory(String path) throws IOException, InterruptedException
    {
        exec.executeCommand("mkdir "+path, null, false, 1);
    }
    
}