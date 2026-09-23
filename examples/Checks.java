import java.util.stream.IntStream;

public final class Checks {

    private Checks() {}

    public static boolean ok(String s) {
        var t = s.replaceAll("\\s", "").toUpperCase();
        return t.matches("[A-Z]{2}\\d{2}[A-Z0-9]{11,30}")
                && (t.substring(4) + t.substring(0, 4)).chars()
                        .reduce(0, (r, c) -> c > 57 ? (r * 100 + c - 55) % 97 : (r * 10 + c - 48) % 97) == 1;
    }

    public static boolean ok(int n) {
        return n > 0 && n < 1_000_000_000
                && IntStream.range(0, 9)
                        .map(i -> (int) (n / Math.pow(10, i) % 10) * (i == 0 ? -1 : i + 1))
                        .sum() % 11 == 0;
    }
}
