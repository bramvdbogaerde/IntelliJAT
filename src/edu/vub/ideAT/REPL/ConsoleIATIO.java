package edu.vub.ideAT.REPL;

import edu.vub.at.IATIO;
import edu.vub.ideAT.ui.ATREPL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by flo on 06/09/16.
 */
public class ConsoleIATIO extends IATIO {
    private PrintStream     output;
    private BufferedReader  input;

    public ConsoleIATIO(ATREPL repl){
        input   = new BufferedReader(new InputStreamReader(repl.getInput()));
        output  = new PrintStream(repl.getOuput());
    }

    @Override
    public void print(String s) {
        output.print(s);
        output.flush();
    }

    @Override
    public void print(int i) {
        output.print(i);
        output.flush();
    }

    @Override
    public void print(double v) {
        output.print(v);
        output.flush();
    }

    @Override
    public void print(boolean b) {
        output.print(b);
        output.flush();
    }

    @Override
    public void println(String s) {
        output.println(s);
    }

    @Override
    public void println(int i) {
        output.println(i);
    }

    @Override
    public void println(double v) {
        output.println(v);
    }

    @Override
    public void println(boolean b) {
        output.println(b);
    }

    @Override
    public void println() {
        output.println();
    }

    @Override
    public String readln(String prompt) throws IOException {
        output.print(prompt);
        return readln();
    }

    @Override
    public String readln() throws IOException {
       /* StringBuffer inputBuffer = new StringBuffer();
        String line = input.readLine();
        if (line != null){
            inputBuffer.append(line);
            while (inputBuffer.length() > 0 && inputBuffer.charAt(inputBuffer.length() - 1) == '\\') {
                inputBuffer.setCharAt(inputBuffer.length() -1, '\n');
                inputBuffer.append(input.readLine());
            }
            String read = inputBuffer.toString();
            if (!IAT._QUIET_ARG_) {
                output.println(read);
            }
            return read;
        } else{
            return null;
        }*/
       //Issues with the way lines are read from console. Quick fixed it by directly listening for enter in console text area and piping to AT instance
       return null;
    }

    @Override
    public PrintStream getOutput() {
        return output;
    }
}
