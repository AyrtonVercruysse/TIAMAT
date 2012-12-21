public class ApplicationTest {
  public static void main(String[] arg) {
    Application t = new Application();
    t.name = "YourApplication";
    for(String s: t)
      System.out.println(s);
  }
}
