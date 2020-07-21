public class Demo {
    public static int applyTwice(IntFun f, int x) {
        return f.my_func(f.my_func(x));
    }

    public static void main(String[] args) {
        IntFun my_func = new TenX();
        int out = Demo.applyTwice(my_func, 7);
        System.out.println(out);
    }
}
