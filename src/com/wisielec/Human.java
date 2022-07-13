package com.wisielec;

public class Human {

    private static final String HUMAN_1 = "      _";
    private static final String HUMAN_2 = "     __";
    private static final String HUMAN_3 = "    |  ";
    private static final String HUMAN_4 = "   o   ";
    private static final String HUMAN_5 = "    \\  ";
    private static final String HUMAN_6 = "   /\\  ";
    private static final String HUMAN_7 = "    |  ";
    private static final String HUMAN_8 = "   ||  ";

    public static void printHuman(int health) {
        switch (health) {
            case 7:
                System.out.println(HUMAN_1);
                System.out.println("\n \n \n \n");
                break;
            case 6:
                System.out.println(HUMAN_2);
                System.out.println("\n \n \n \n");
                break;
            case 5:
                System.out.println(HUMAN_2);
                System.out.println(HUMAN_3);
                System.out.println("\n \n \n");
                break;
            case 4:
                System.out.println(HUMAN_2);
                System.out.println(HUMAN_3);
                System.out.println(HUMAN_4);
                System.out.println("\n \n");
                break;
            case 3:
                System.out.println(HUMAN_2);
                System.out.println(HUMAN_3);
                System.out.println(HUMAN_4);
                System.out.println(HUMAN_5);
                System.out.println("\n");
                break;
            case 2:
                System.out.println(HUMAN_2);
                System.out.println(HUMAN_3);
                System.out.println(HUMAN_4);
                System.out.println(HUMAN_6);
                System.out.println("\n");
                break;
            case 1:
                System.out.println(HUMAN_2);
                System.out.println(HUMAN_3);
                System.out.println(HUMAN_4);
                System.out.println(HUMAN_6);
                System.out.println(HUMAN_7);
                break;
            case 0:
                System.out.println(HUMAN_2);
                System.out.println(HUMAN_3);
                System.out.println(HUMAN_4);
                System.out.println(HUMAN_5);
                System.out.println(HUMAN_8);
                break;
        }

    }

}
