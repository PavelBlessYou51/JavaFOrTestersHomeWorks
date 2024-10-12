package homework.lesson2;

import static java.lang.Math.sqrt;

public record Triangle(double a, double b, double c) {

    double perimetr() {
        return this.a + this.b + this.c;
    }

    double square() {
        double halfPer = this.perimetr() / 2;
        double result = sqrt(halfPer * (halfPer - this.a) * (halfPer - this.b) * (halfPer - this.c));
        return result;
    }


}
