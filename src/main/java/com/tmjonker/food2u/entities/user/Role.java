package com.tmjonker.food2u.entities.user;

public enum Role {
    USER {
        @Override
        public String toString() {
            return "USER";
        }
    },

    ADMIN {
        @Override
        public String toString() {
            return "ADMIN";
        }
    }
}
