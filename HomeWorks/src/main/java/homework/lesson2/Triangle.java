package homework.lesson2;

import java.util.Objects;

import static java.lang.Math.sqrt;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("The side of triangle should be non-negative!");
        }
        if (a + b < c || a + c < b || c + b < a) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (this.a + this.b + this.c) == (triangle.a + triangle.b + triangle.c);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
