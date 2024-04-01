package Prototype;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Generator {
    public void getLastIDGenerated() throws FileNotFoundException;
    public void writeLastIDGenerated() throws IOException;
    public void generateID();
    public void generateDate();
}
