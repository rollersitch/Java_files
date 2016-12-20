public class StringComparison {
  /**
   * @param args
   */
  public static void main(String[] args) {
    long start=System.currentTimeMillis();
    testString();
    long end=System.currentTimeMillis();
    System.out.println("Tempo di esecuzione testString() "+(end-start)+" millis.");
    
    start=System.currentTimeMillis();
    testStringBuffer();
    end=System.currentTimeMillis();
    System.out.println("Tempo di esecuzione testStringBuffer() "+(end-start)+" millis.");
    
    start=System.currentTimeMillis();
    testStringBuilder();
    end=System.currentTimeMillis();
    System.out.println("Tempo di esecuzione testStringBuilder() "+(end-start)+" millis.");
  }

  private static void testString() {
    String x = "";
    for(int i=0;i<15000;i++){
      //operazione di append
      x+=i;
    }
  }
 private static void testStringBuffer() {
    StringBuffer x = new StringBuffer("");
    for(int i=0;i<15000;i++){
      //operazione di append
      x.append(i);
    }
  }
  
  private static void testStringBuilder() {
    StringBuilder x = new StringBuilder("");
    for(int i=0;i<15000;i++){
      //operazione di append
      x.append(i);
    }
  }
} 
  
