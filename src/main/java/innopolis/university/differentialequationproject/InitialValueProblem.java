package innopolis.university.differentialequationproject;

public class InitialValueProblem {
    double X0;
    double Y0;

    public InitialValueProblem(double x0, double XY) {
        this.X0 = x0;
        this.Y0 = XY;
    }

    public double getX0() {
        return X0;
    }

    public void setX0(double x0) {
        X0 = x0;
    }

    public double getY0() {
        return Y0;
    }

    public void setY0(double y0) {
        this.Y0 = y0;
    }
}
