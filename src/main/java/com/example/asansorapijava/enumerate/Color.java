package com.example.asansorapijava.enumerate;



public enum Color {
    Red(0), Blue(1), Green(2), Yellow(3);

    private final int id;


    Color(int id) {
        this.id = id;
    }

    public static Color getColor(Integer id) {
        if (id == null) {
            return null;
        }

        for (Color color : Color.values()) {
            if (id.equals(color.getID())) {
                return color;
            }
        }

        return null;
    }


    public int getID() {
        return id;
    }
}