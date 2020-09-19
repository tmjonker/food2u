package com.tmjonker.food2u.entities.restaurant;

public enum Category {

    ITALIAN {
        @Override
        public String toString() {
            return "Italian";
        }
    },

    CHINESE {
        @Override
        public String toString() {
            return "Chinese";
        }
    },

    MEXICAN {
        @Override
        public String toString() {
            return "Mexican";
        }
    }
}
