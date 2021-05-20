package com.sergeik.design;

public class DesignParkingSystem {

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        assert parkingSystem.addCar(1); // return true because there is 1 available slot for a big car
        assert parkingSystem.addCar(2); // return true because there is 1 available slot for a medium car
        assert !parkingSystem.addCar(3); // return false because there is no available slot for a small car
        assert !parkingSystem.addCar(1); // return false because there is no available slot for a big car. It is already occupied.
    }

    static class ParkingSystem {

        private int big;
        private int medium;
        private int small;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            switch (carType) {
                case 1:
                    if (big <= 0)
                        return false;
                    big--;
                    break;
                case 2:
                    if (medium <= 0)
                        return false;
                    medium--;
                    break;
                case 3:
                    if (small <= 0)
                        return false;
                    small--;
                    break;
            }
            return true;
        }
    }

}
