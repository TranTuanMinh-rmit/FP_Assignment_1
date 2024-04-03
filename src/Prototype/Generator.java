package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Generator {
    public void getLastIDGenerated() throws FileNotFoundException;
    public void writeLastIDGenerated() throws IOException;
    public void generateID();
}
