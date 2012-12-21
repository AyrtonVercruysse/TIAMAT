
public class Application {
  public int go = 10;
   
  public void start() {
    System.out.println("Starting");
    stop(go);
  }
 
  public void stop(int i) {
    System.out.println("Stopping");
  }
 
  public void exec() throws Exception {
    throw new Exception("An exception");
  }
 
  public String toString() {
    return "Application go: " + go;
  }
 
  public static void main(String[] arg) {
    Application t = new Application();
    System.out.println(t);
    t.start();
    t.stop(25);
    try { t.exec(); }
    catch(Exception e) { System.out.println(e); }
  }
}
