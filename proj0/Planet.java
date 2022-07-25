public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel; 
    public double yyVel;
    public double mass; 
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV; 
        yyVel = yV; 
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos; 
        xxVel = p.xxVel; 
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet planetName) {
        double xDistance = planetName.xxPos - this.xxPos; 
        double yDistance = planetName.yyPos - this.yyPos; 
        double xSquared = xDistance * xDistance; 
        double ySquared = yDistance * yDistance;
        double rSqrt = Math.sqrt(xSquared + ySquared);
        return rSqrt;
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return 6.67e-11*this.mass*p.mass*1/(r*r);
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double xDistance = p.xxPos - this.xxPos; 
        double r = this.calcDistance(p);
        return F*xDistance/r;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double yDistance = p.yyPos - this.yyPos; 
        double r = this.calcDistance(p);
        return F*yDistance/r;
    }

 
    public double calcNetForceExertedByX(Planet[] p) {
        double total = 0;
        for (int count = 0; count < p.length; count = count+1){
            Planet current = p[count];
            if (this.equals(current)) {
                continue;
            }
            else {
                total = total + this.calcForceExertedByX(current);
            }
        }
        return total;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double total = 0;
        for (int count = 0; count < p.length; count = count+1){
            Planet current = p[count];
            if (this.equals(current)) {
                continue;
            }
            else {
                total = total + this.calcForceExertedByY(current);
            }
        }
        return total;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX/this.mass; 
        double aY = fY/this.mass; 
        double vX = this.xxVel+dt*aX;
        double vY = this.yyVel+dt*aY;
        double pX = this.xxPos+dt*vX;
        double pY = this.yyPos+dt*vY;
        this.yyPos = pY;
        this.xxPos = pX;
        this.yyVel = vY;
        this.xxVel = vX;
/**testing */
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }

}
