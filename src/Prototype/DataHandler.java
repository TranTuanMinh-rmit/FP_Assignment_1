package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataHandler {
    public void readData() throws FileNotFoundException;

    public void writeData() throws IOException;
}