public class Duration {

    private final int hours;
    private final int minutes;
    private final int seconds;

    private Duration(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    // Factory method to create a Duration object from total seconds
    public static Duration of(int totalSeconds) {
        if (totalSeconds < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return new Duration(hours, minutes, seconds);
    }

    // Factory method to create a Duration object from hours, minutes, and seconds
    public static Duration of(int hours, int minutes, int seconds) {
        if (hours < 0 || minutes < 0 || seconds < 0) {
            throw new IllegalArgumentException("Duration components cannot be negative");
        }
        return new Duration(hours, minutes, seconds);
    }

    // Method to add two Duration objects together
    public Duration add(Duration other) {
        int totalHours = this.hours + other.hours;
        int totalMinutes = this.minutes + other.minutes;
        int totalSeconds = this.seconds + other.seconds;

        if (totalSeconds >= 60) {
            totalMinutes += totalSeconds / 60;
            totalSeconds %= 60;
        }

        if (totalMinutes >= 60) {
            totalHours += totalMinutes / 60;
            totalMinutes %= 60;
        }

        return new Duration(totalHours, totalMinutes, totalSeconds);
    }

    // Method to get the total number of seconds in the Duration
    public int seconds() {
        return (this.hours * 3600) + (this.minutes * 60) + this.seconds;
    }

    // Override toString to display the duration in H:MM:SS format
    @Override
    public String toString() {
        return String.format("%d:%02d:%02d", this.hours, this.minutes, this.seconds);
    }
}

