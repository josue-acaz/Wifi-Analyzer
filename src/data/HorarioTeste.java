package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HorarioTeste {
    Calendar c = Calendar.getInstance();
    Command cmd = new Command();
    Util aux = new Util();
    
    public void dateTime(String path, String nomeArquivo)
    {
        aux.createFile(path+nomeArquivo);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date();
	aux.writeFile(path+nomeArquivo, dateFormat.format(date));
    }
}
