package homework.lesson2;

import static java.lang.Math.sqrt;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("The side of triangle should be non-negative!");
        }
        if (a + b > c || a + c > b || c + b > a) {
            throw new IllegalArgumentException("The triangle does not exist!");
        }

    }

    double perimetr() {
        return this.a + this.b + this.c;
    }

    double square() {
        double halfPer = this.perimetr() / 2;
        double result = sqrt(halfPer * (halfPer - this.a) * (halfPer - this.b) * (halfPer - this.c));
        return result;
    }


}
