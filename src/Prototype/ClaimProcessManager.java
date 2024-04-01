package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


public interface ClaimProcessManager {
    public void add() throws InterruptedException;
    public void update();
    public void delete();
    public void getOne();
    public void getAll();
}
