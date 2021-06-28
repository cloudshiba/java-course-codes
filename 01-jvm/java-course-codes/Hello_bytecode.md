Compiled from "Hello.java"
public class com.cloudshiba.javacoursecodes.bytecode.Hello {
  public com.cloudshiba.javacoursecodes.bytecode.Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1
       1: istore_1
       2: ldc2_w        #2                  // double 2.0d
       5: dstore_2
       6: ldc2_w        #4                  // long 3l
       9: lstore        4
      11: iconst_4
      12: istore        6
      14: bipush        85
      16: istore        7
      18: ldc           #6                  // String
      20: invokevirtual #7                  // Method java/lang/String.length:()I
      23: bipush        10
      25: if_icmpge     58
      28: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
      31: new           #9                  // class java/lang/StringBuilder
      34: dup
      35: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
      38: ldc           #11                 // String 错误用法: num2 + num3 =
      40: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      43: dload_2
      44: invokevirtual #13                 // Method java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
      47: lload         4
      49: invokevirtual #14                 // Method java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
      52: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      55: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      58: iload         7
      60: iload_1
      61: if_icmple     72
      64: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
      67: ldc           #17                 // String 比較結果： char1 > num1
      69: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      72: iconst_0
      73: istore        8
      75: iload         8
      77: iload_1
      78: if_icmpge     115
      81: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
      84: new           #9                  // class java/lang/StringBuilder
      87: dup
      88: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
      91: ldc           #18                 // String num1 * num4 =
      93: invokevirtual #12                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      96: iload_1
      97: iload         6
      99: imul
     100: invokevirtual #19                 // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
     103: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     106: invokevirtual #16                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     109: iinc          8, 1
     112: goto          75
     115: return
}
