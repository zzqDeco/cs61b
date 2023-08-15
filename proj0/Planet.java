public class Planet {
    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;
    public static double G=6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public double calcDistance(Planet b) {
        return Math.sqrt((b.xxPos-xxPos)*(b.xxPos-xxPos)+(b.yyPos-yyPos)*(b.yyPos-yyPos));
    }

    public double calcForceExertedBy(Planet b) {
        return G*mass*b.mass/calcDistance(b)/calcDistance(b);
    }

    public Planet(Planet p) {
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
}