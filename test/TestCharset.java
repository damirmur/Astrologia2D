
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class TestCharset {

    private static void print(final Charset set, final CharSequence sb) {
        byte[] array = new byte[4];
        set.newEncoder()
                .encode(CharBuffer.wrap(sb), ByteBuffer.wrap(array), true);
        final String buildedString = new String(array, set);
        System.out.println(set + " " + Arrays.toString(array) + " " + buildedString + "<<>>" + buildedString.length());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char c='È';//917943
        String sch=Character.toString(c);
//        final List<Charset> charsets = Arrays.asList(StandardCharsets.ISO_8859_1, StandardCharsets.US_ASCII, StandardCharsets.UTF_16, StandardCharsets.UTF_8);
        final List<Charset> charsets = Arrays.asList(StandardCharsets.UTF_16, StandardCharsets.UTF_8);
        charsets.forEach(a -> print(a, sch));
        System.out.println("getBytes");
        System.out.println(Arrays.toString(sch.getBytes()));
        charsets.forEach(a -> System.out.println(a + " " + Arrays.toString(sch.toString().getBytes(a))));
    }

}
