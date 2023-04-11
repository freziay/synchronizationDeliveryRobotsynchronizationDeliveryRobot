import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static void main(String[] args) {
        int countRoute = 0;// колличесево маршрутов
        int max = 0;
        int maxCharacter = 0;
        while (countRoute < 1000) {
            new Thread(() -> {
                String str = generateRoute("RLRFR", 100);
                String[] words = str.split("");
                int count = 1;// ключ
                for (String word : words) {
                    if (word.equals("R")) {
                        count++;
                    }
                }
                addMap(count);
            }).start();
            countRoute++;
        }
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");
            if (max < entry.getValue()) {
                max = entry.getValue();
                maxCharacter = entry.getKey();
            }
        }
        System.out.println("самое частое количество повторений = " + maxCharacter + " (встретилось " + max + " раз)");
    }
    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
    public static synchronized void addMap(int count) {
        if (!sizeToFreq.containsKey(count)) {
            sizeToFreq.put(count, 1);
        } else {
            sizeToFreq.put(count, sizeToFreq.get(count) + 1);
        }
    }
}
