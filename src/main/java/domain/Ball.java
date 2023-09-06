package domain;

import jakarta.persistence.Entity;

@Entity
public class Ball extends Toy{
    private enum Color {
        RED, BLUE
    }

    private Color color;

    public Ball() {
    }

    public Ball(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Ball [id=" + id + ", color=" + color + "]";
    }
}
