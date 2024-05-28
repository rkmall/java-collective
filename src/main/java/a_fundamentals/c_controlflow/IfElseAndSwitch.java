package a_fundamentals.c_controlflow;

public class IfElseAndSwitch {

    public static void main(String[] args) {

//        ifElseEx(2100);
//        ifElseEx(1600);
//        ifElseEx(1400);

        //switchEx(4);
}

    public static void ifElseEx(int sales)
    {
        int target = 1000;
        int bonus = 0;
        String performance;

        if (sales >= 2 * target){
            performance = Performance.EXCELLENT.name();
            bonus = 100;
        }else if (sales >= 1.5 * target) {
            performance = Performance.GOOD.name();
            bonus = 50;
        }else if (sales >= target){
            performance = Performance.SATISFACTORY.name();
            bonus = 25;
        }else{
            performance = Performance.UNSATISFACTORY.name();
            bonus = 0;
        }
        System.out.println("Performance: " + performance + ", Bonus: " + bonus);
    }

    public static void switchEx(int x)
    {
        switch (x) {
            case 1: case 2: case 3: case 4: case 5: case 6: case 7:    // fallthrough from case1 to case7
                System.out.println("x ranges from 1 to 7, x = " + x);
                break;
            case 8:
                System.out.println("x = 8");
                break;
            case 9:
                System.out.println("x = 9");
                break;
            default:
                System.out.println("x is out of range");
                break;
        }
    }

    enum Performance {
        EXCELLENT,
        GOOD,
        SATISFACTORY,
        UNSATISFACTORY
    }
}
