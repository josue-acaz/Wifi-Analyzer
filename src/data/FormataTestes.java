package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormataTestes {
    //Construtor que permite invocar métodos de manipulação de arquivos
    static Formatter output;
    Command cm = new Command();
    
    static Util aux = new Util();
    
    public void quantidadeUsuarios(String file, String directory) throws IOException
    {
        //O número de usuários é a quantidade de hosts conecatdos à rede atual
        //expressão regular que localiza quantidade de usuários
        Pattern s = Pattern.compile("(\\d{1,1000000})(\\shosts\\sup)");
        
        aux.createFile(directory+"quant_usuarios_f.txt");
        
        //Arquivo encontrado é processado e formatado
        try(FileReader arq = new FileReader(file); BufferedReader lerArq = new BufferedReader(arq)) {
            
            String linha = lerArq.readLine();
            
            while(linha != null)
            {
                Matcher matcher = s.matcher(linha);
                
                //A cada correspondência o registro é colocado no arquivo
                while(matcher.find())
                {
                    aux.registerFile(matcher.group(1));
                }
                
                linha = lerArq.readLine();
            }
            
            //Fecha o arquivo quando todas as linhas são lidas
            aux.closeFile();
        }
        
        //Não é possível abrir o arquivo
        //O método lança a execeção e termina o programa
        catch(IOException e)
        {
            System.err.printf(e.getMessage());
        }
    }
    
    public void potenciaDoSinal(String file, String directory) throws IOException
    {
        Pattern s = Pattern.compile("(-\\d{1,2})(\\sdBm)");
        aux.createFile(directory+"/potencia_sinal_f.csv");
        
        try(FileReader arq = new FileReader(file);
            
            BufferedReader lerArq = new BufferedReader(arq)) {
            
            String linha = lerArq.readLine();
            
            while(linha != null)
            {
                Matcher matcher = s.matcher(linha);
                while(matcher.find())
                {
                    aux.registerFile(matcher.group(1)+";\n");
                }
                
                linha = lerArq.readLine();
            }
        }
        
        catch(IOException e)
        {
            System.err.printf(e.getMessage());
        }
        
        aux.closeFile();
    }
    
    //COLETA DE DADOS COM IPERF
    public void larguraDeBanda(String file, String directory, String tag, int timeTest) throws IOException
    {
        Pattern s = Pattern.compile("(\\d{1,10}(\\.\\d{1,3})?)(\\sMbits/sec)");
        aux.createFile(directory+"largura_banda_"+tag+"_f.csv");
        
        int time = 0;
        
        try(FileReader arq = new FileReader(file); BufferedReader lerArq = new BufferedReader(arq)) {
            
            String linha = lerArq.readLine();
            
            while(linha != null)
            {
                Matcher matcher = s.matcher(linha);
                while(matcher.find())
                {
                    time++;
                    if(time<timeTest+1){ aux.registerFile(String.valueOf(time)+";"+matcher.group(1)+"\n"); }
                    
                }
                
                linha = lerArq.readLine();
            }
        }
        
        catch(IOException e)
        {
            System.err.printf(e.getMessage());
        }
        
        aux.closeFile();
    }
    
    public void jitter(String file, String directory, int timeTest) throws IOException
    {
        Pattern s = Pattern.compile("((\\d{1,2}\\.?)(\\d{1,3}))(\\sms)");
        aux.createFile(directory+"jitter_f.csv");
        
        int time = 0;
        
        try(FileReader arq = new FileReader(file); BufferedReader lerArq = new BufferedReader(arq)) {
            
            String linha = lerArq.readLine();
            
            while(linha != null)
            {
                Matcher matcher = s.matcher(linha);
                while(matcher.find())
                {
                    time++;
                    if(time<timeTest+1){aux.registerFile(String.valueOf(time)+";"+matcher.group(1)+"\n");}
                }
                
                linha = lerArq.readLine();
            }
        }
        
        catch(IOException e)
        {
            System.err.printf(e.getMessage());
        }
        
        aux.closeFile();
    }
    
    public void perdaDePacote(String file, String directory, int timeTest) throws IOException
    {
        Pattern s = Pattern.compile("(\\d{1,5})/\\d{1,10}"); // Exemplo.: 0/154
        aux.createFile(directory+"perda_pacotes_f.csv");
        
        int time = 0;
        
        try(FileReader arq = new FileReader(file); BufferedReader lerArq = new BufferedReader(arq)) {
            
            String linha = lerArq.readLine();
            
            while(linha != null)
            {
                Matcher matcher = s.matcher(linha);
                while(matcher.find())
                {
                    time++;
                    if(time<timeTest+1){aux.registerFile(String.valueOf(time)+";"+matcher.group(1)+"\n");}
                }
                
                linha = lerArq.readLine();
            }
        }
        
        catch(IOException e)
        {
            System.err.printf(e.getMessage());
        }
        
        aux.closeFile();
    }
    
    public void atraso(String file, String directory) throws IOException
    {
        //pesquisa o RTT AVG
        Pattern s = Pattern.compile("(time=)(\\d{1,5}(\\.\\d{1,3})?)(\\sms)"); // Exemplo: time=4.13 ms
        
        aux.createFile(directory+"atraso_f.csv");
        int time = 0;
        
        try(FileReader arq = new FileReader(file); BufferedReader lerArq = new BufferedReader(arq)) {
            
            String linha = lerArq.readLine();
            
            while(linha != null)
            {
                Matcher matcher = s.matcher(linha);
                while(matcher.find())
                {
                    time++;
                    aux.registerFile(time+";"+matcher.group(2)+"\n"); //Retorna a média RTT
                }
                
                linha = lerArq.readLine();
            }
        }
        
        catch(IOException e)
        {
            System.err.printf(e.getMessage());
        }
        
        aux.closeFile();
    }
}
