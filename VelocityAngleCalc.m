function [v0, angle] = VelocityAngleCalc(vs,vp,rStart,r0)
%Method to compute the injection velocity of the spacecraft based on
%the previously calculated departure velocity.

%vs = the departure velocity vector as acalculated by the ShortWayCalc method
%vp = the planet's velocity vector at launch
%vsp = the relative velocity, the difference between the orbital velocity
%of the planet and the spacecraft's heliocentric velocity.
vsp = [0 0 0];
%v0  is the injection velocity of the spacecraft when launched from earth

%angle is the zenith angle of the departure asymptote with respect to the
%hyperbolic excess velocity
%M = mass of the earth in kg
%G = the gravitational constant

G = 6.67408E-11;
M = 5.972E24;

vsp(1) = vs(1) - vp(1);
vsp(2) = vs(2) - vp(2);
%vsp(3) = vs(3) - vp(3);

v0 = sqrt(norm(vsp)^2+(2*G*M)/r0);

r = norm(rStart);
vinf = norm(vsp);
%+rStart(3).*vsp(3)
angle = acosd((rStart(1).*vsp(1)+rStart(2).*vsp(2))/(r*vinf));
end
