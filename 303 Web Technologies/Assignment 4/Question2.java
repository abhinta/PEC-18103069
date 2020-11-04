class Question2
{
    public static void main(String[] args)
    {
        int n = -1;
        byte b = (byte)n; //-1
        char c = (char)b; //although size(char) > size(byte)...typecasting is required since, char is unsigned
        int N = c;
        System.out.println("Initially no: " + n);
        System.out.println("To Byte: " + b);
        System.out.println("To Character: " + c);
        System.out.println("Finally: " + N);
    }
}