public class Settings {
    public static String playerName = "";
    public static String playerName2 = "";
    private static int deltaTime = 200;
    public static int level = 1;

    public static int getDeltaTime() {
        switch (level) {
            case 0:
                return 200;
            case 1:
                return 150;
            case 2:
                return 100;
            case 3:
                return 50;
        }
        return 300;
    }
}

