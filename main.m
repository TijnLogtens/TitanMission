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
rStart = [-9.9606191276749E-01 -1.4213879018474E-03]; %23rd of march 2019
rEnd = [9.5588235294118 -1.3107093774423E-02]; % add Titans y at the of 6yrs 221 days
TOF = 208310400; % in seconds
dTrueAnomaly = acosd((rStart(1)*rEnd(1) + rStart(2)*rEnd(2))/(norm(rStart)*norm(rEnd)));
r0 = 6578140;

%Generates the semi-major latus and semi-major axis of the transfer orbits
[p, a, i, f, g, df, dg] = pIteration(rStart,rEnd,dTrueAnomaly,TOF,0.1);

%Calculates the Type-1 and Type-2 velocity vectors for the tranfser orbits
[v1, v2] = ShortWayCalc(rStart,rEnd,f,g,df,dg);

%Earths velocity vector at start is vp
vp = [25876.6 13759.5 0];

[v0, angle] = VelocityAngleCalc(v1, vp, rStart, r0);
 v=[v0*cos(angle);v0*sin(angle)]
 disp(angle);
