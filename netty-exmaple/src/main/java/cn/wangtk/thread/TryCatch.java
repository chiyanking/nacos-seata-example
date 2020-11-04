package cn.wangtk.thread;

public class TryCatch {
    public static void main(String[] args) {


        System.out.println(getName(" 123"));
        System.out.println(getName(null));
    }


    static int getName(String name) {
        try {
            return name.length();
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("finally");
        }


    }
}
