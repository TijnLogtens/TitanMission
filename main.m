%Template class for the calculation a Heliocentric transfer orbit.

%Set up constants for Heliocentric transfer orbit calculation, relevant to
%the specific problem

%Constants: rStart,rEnd,dTrueAnomaly,TOF
%rStart = Position vector of probe at start 
%rEnd = Position vector of probe at interception
%dTrueAnomaly = the true anomaly of the tranfer orbit
%TOF = desired time of flight
%r0 = the distance between the sun and earth at launch

%Set the trial values for the problem
format long;
rStart = [0.93806827564191 -0.35196801802964];
rEnd = [(1392256569682.65/149.597870E9) (240404432241.831/149.597870E9)];
TOF = 235440000;
dTrueAnomaly = acosd((rStart(1)*rEnd(1) + rStart(2)*rEnd(2))/(norm(rStart)*norm(rEnd)));
r0 = 6578140;
%Generates the semi-major latus and semi-major axis of the transfer orbits
[p, a, i, f, g, df, dg] = pIteration(rStart,rEnd,dTrueAnomaly,TOF,0.1);

%Calculates the Type-1 and Type-2 velocity vectors for the tranfser orbits
[v1, v2] = ShortWayCalc(rStart,rEnd,f,g,df,dg);

%Earth's velocity vector at start is vp
vp = [-6.271192280390987E1 -2.988491242814953E4];

[v0, magv0] = VelocityAngleCalc(v1, vp, rStart, r0);
disp(v0); disp(magv0);