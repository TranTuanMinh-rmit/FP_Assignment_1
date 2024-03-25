package Prototype;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataHandler {
    public void readData() throws FileNotFoundException;
    public void writeData() throws IOException;
}
